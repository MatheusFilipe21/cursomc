package com.matheus.f.n.pereira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.f.n.pereira.cursomc.entities.Categoria;
import com.matheus.f.n.pereira.cursomc.repositories.CategoriaRepository;
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
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Na nossa base de dados não existe nenhuma "
						+ Categoria.class.getSimpleName() + " com o Id: " + id));
	}
}
