package com.techchefs.springbootrabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
	final static String queueName = "message_queue1";
	// final static String queueName2 = "message_queue-2";

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void msgProducer() throws InterruptedException {

		rabbitTemplate.convertAndSend(queueName, "Message From TECHCHEFS 1");
		// rabbitTemplate.convertAndSend(queueName2, "Message From TECHCHEFS 2");

		// System.out.println("Message has been sent successfully to Queue");
	}
}
