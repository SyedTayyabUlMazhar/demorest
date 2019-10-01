package com.telusko.demorest;

import java.util.ArrayList;

import java.util.List;

public class MessageService
{

	public List<Message> getAllMessages()
	{
		Message m1 = new Message(1L, "Hellow world", "Kasik");
		Message m2 = new Message(2L, "Hellow Jersey", "Kasik");
		
		List<Message> list = new ArrayList<Message>();
		list.add(m1);
		list.add(m2);
		
		return list;
	}
}
