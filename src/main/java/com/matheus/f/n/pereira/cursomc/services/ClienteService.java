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

import com.matheus.f.n.pereira.cursomc.dto.ClienteDTO;
import com.matheus.f.n.pereira.cursomc.entities.Cliente;
import com.matheus.f.n.pereira.cursomc.repositories.ClienteRepository;
import com.matheus.f.n.pereira.cursomc.services.exceptions.DatabaseException;
import com.matheus.f.n.pereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(Cliente.class.getSimpleName(), id));
	}

	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}

	public Cliente update(Integer id, Cliente obj) {
		try {
			Cliente entity = repository.getOne(id);
			updatedata(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(Cliente.class.getSimpleName(), id);
		}

	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(Cliente.class.getSimpleName(), id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(
					"Não é possível excluir um " + Cliente.class.getSimpleName() + " que possui Produtos");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void updatedata(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
	}
}
