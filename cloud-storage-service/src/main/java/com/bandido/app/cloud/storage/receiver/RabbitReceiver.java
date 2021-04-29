package com.bandido.app.cloud.storage.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.bandido.app.cloud.storage.config.RabbitProperties;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RabbitReceiver {
	
	@SuppressWarnings("unused")
	@Autowired
	private RabbitProperties properties;
	
	@RabbitListener(queues = "#{properties.getQueue()}")
	public void receive(String mensaje) {
		log.info("Se guarda documento {} en Cloud Storage", mensaje);	
	}

}
