package com.matheus.f.n.pereira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.f.n.pereira.cursomc.entities.Pedido;
import com.matheus.f.n.pereira.cursomc.repositories.PedidoRepository;
import com.matheus.f.n.pereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Na nossa base de dados não existe nenhum "
						+ Pedido.class.getSimpleName() + " com o Id: " + id));
	}
}
