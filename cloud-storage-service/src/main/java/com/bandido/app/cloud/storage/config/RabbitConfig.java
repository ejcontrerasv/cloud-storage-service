package com.bandido.app.cloud.storage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import com.bandido.app.cloud.storage.receiver.RabbitReceiver;


public class RabbitConfig  implements RabbitListenerConfigurer{
	
	@Autowired
	private RabbitProperties properties;
	
	@Bean
	public TopicExchange eventExchange() {
		return new TopicExchange(properties.getExchange());
	}
	
	@Bean
	public Queue queue() {
		return new Queue(properties.getQueue());
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange eventExchange) {
		return BindingBuilder
				.bind(queue)
				.to(eventExchange)
				.with(properties.getQueue());
	}
	
	@Bean
	public RabbitReceiver rabbitReceiver() {
		return new RabbitReceiver();
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
	
	@Bean
	public MessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
		messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
		return messageHandlerMethodFactory;
	}
	
	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

}
