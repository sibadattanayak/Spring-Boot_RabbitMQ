package com.techchefs.springbootrabbitmq.service;

public class MessageReceiver2 {
	
	public void receiveMsg(String message) {
		
		System.out.println("Message Received by receiver 2 ---> " + message);
	}
}
