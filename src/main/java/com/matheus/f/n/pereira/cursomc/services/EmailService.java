package com.matheus.f.n.pereira.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.matheus.f.n.pereira.cursomc.entities.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
