package com.techchefs.springbootrabbitmq.service;

public class MessageReceiver {
	
	public void receiveMsg(String message) {
		
		System.out.println("Message Received by receiver 1 ---> " + message);
	}

}
