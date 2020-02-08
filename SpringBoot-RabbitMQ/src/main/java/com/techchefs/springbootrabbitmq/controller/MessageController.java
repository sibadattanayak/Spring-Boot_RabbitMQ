package com.techchefs.springbootrabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techchefs.springbootrabbitmq.service.MessageProducer;

@Controller
@RequestMapping(value = "/api")
public class MessageController {

	@Autowired
	private MessageProducer messageProducer;

	@RequestMapping(value = "/send")
	public void repeatMessage() {
		for(int i=1;i<=10;i++) {
			System.err.println(i);
			sendMessage();
		}
	}
	
	public String sendMessage() {

		try {
			messageProducer.msgProducer();
		}catch(

	InterruptedException e)
	{
			e.printStackTrace();
		}return"Messange Sent";
}

}
