package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.example.demo.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	@Value("&{default.sende")
	private String sender;
	
	@Override
	public void sendOrderConfimationEmail(Pedido obj) {
		
		SimpleMailMessage ms = prepareSimpleMailMassageFromPedido(obj);
		sendEmail(ms);
	}

	protected SimpleMailMessage prepareSimpleMailMassageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
