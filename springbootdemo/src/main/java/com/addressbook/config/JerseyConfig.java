package com.addressbook.config;

import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.context.annotation.Configuration;

import com.addressbook.service.ContactService;
import org.springframework.stereotype.Component;
@Configuration
@Component
public class JerseyConfig extends ResourceConfig

{

	public JerseyConfig() 
	{
	
		register(ContactService.class);
	}
}
