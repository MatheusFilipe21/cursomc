package com.matheus.f.n.pereira.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheus.f.n.pereira.cursomc.entities.Categoria;
import com.matheus.f.n.pereira.cursomc.repositories.CategoriaRepository;
import com.matheus.f.n.pereira.cursomc.services.exceptions.DatabaseException;
import com.matheus.f.n.pereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(Categoria.class.getSimpleName(), id));
	}

	public Categoria insert(Categoria obj) {
		return repository.save(obj);
	}

	public Categoria update(Integer id, Categoria obj) {
		try {
			Categoria entity = repository.getOne(id);
			updatedata(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(Categoria.class.getSimpleName(), id);
		}

	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(Categoria.class.getSimpleName(), id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir uma " + Categoria.class.getSimpleName() + " que possui Produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		 return repository.findAll(pageRequest);
	}

	private void updatedata(Categoria entity, Categoria obj) {
		entity.setNome(obj.getNome());
	}
}
