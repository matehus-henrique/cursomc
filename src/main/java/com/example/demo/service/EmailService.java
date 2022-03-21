package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;

import com.example.demo.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfimationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}


