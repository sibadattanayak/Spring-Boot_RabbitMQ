package com.techchefs.springbootrabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.techchefs.springbootrabbitmq.service.MessageReceiver;
import com.techchefs.springbootrabbitmq.service.MessageReceiver2;

@Configuration
public class RabbitMQConfiguration {

	final static String queueName = "message_queue1";
	final static String queueName2 = "message_queue2";
	final static String exchangeName = "message_queue";

	@Bean
	Queue queue1() {
		return new Queue(queueName, false);
	}

//	@Bean
//	Queue queue2() {
//		return new Queue(queueName2, false);
//	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}
//	@Bean
//	Binding binding2(Queue queue2, TopicExchange exchange) {
//		return BindingBuilder.bind(queue2).to(exchange).with(queueName2);
//	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		System.out.println("host = " + connectionFactory.getHost());
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

//	@Bean
//	SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,
//			MessageListenerAdapter listenerAdapter2) {
//		System.out.println("host = " + connectionFactory.getHost());
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
//		container.setMessageListener(listenerAdapter2);
//		return container;
//	}

	@Bean
	MessageReceiver receiver() {
		return new MessageReceiver();
	}

//	@Bean
//	MessageReceiver2 receiver2() {
//		return new MessageReceiver2();
//	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMsg");
	}

//	@Bean
//	MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMsg");
//	}

}
