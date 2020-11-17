package com.addressbook.repository;

import java.util.HashMap;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.addressbook.model.Contact;

@Repository
public class ContactRepository {
	private static Map<String, Contact> contactsMap = new HashMap<>();
	
	public Contact addContact(Contact contact)
	{
		contact.setId(Integer.toString(new Random().nextInt()));
		contactsMap.put(contact.getId(), contact);
		return contact;
	}

	public Contact getContact(String id) 
	{
		return contactsMap.get(id);
	}

	public Contact deleteContact(String id) 
	{
		return contactsMap.remove(id);
	}

	public Contact updateContact(String id,Contact contact) 
	{				
		
		return contactsMap.put(id,contact);
	 
				
	}



}
