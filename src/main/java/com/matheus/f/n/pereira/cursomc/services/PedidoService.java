package com.matheus.f.n.pereira.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheus.f.n.pereira.cursomc.entities.ItemPedido;
import com.matheus.f.n.pereira.cursomc.entities.PagamentoComBoleto;
import com.matheus.f.n.pereira.cursomc.entities.Pedido;
import com.matheus.f.n.pereira.cursomc.entities.enums.EstadoPagamento;
import com.matheus.f.n.pereira.cursomc.repositories.ItemPedidoRepository;
import com.matheus.f.n.pereira.cursomc.repositories.PagamentoRepository;
import com.matheus.f.n.pereira.cursomc.repositories.PedidoRepository;
import com.matheus.f.n.pereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(Pedido.class.getSimpleName(), id));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
