package com.matheus.f.n.pereira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheus.f.n.pereira.cursomc.entities.Categoria;
import com.matheus.f.n.pereira.cursomc.entities.Produto;
import com.matheus.f.n.pereira.cursomc.repositories.CategoriaRepository;
import com.matheus.f.n.pereira.cursomc.repositories.ProdutoRepository;
import com.matheus.f.n.pereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException(Produto.class.getSimpleName(), id));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		 List<Categoria> categorias = categoriaRepository.findAllById(ids);
		 return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
