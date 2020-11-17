package com.addressbook.service;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.model.Contact;
import com.addressbook.repository.ContactRepository;

@RestController
@Path("/contacts")
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/view/{id}")
	public Response getcontact(@PathParam("id") String id) 
	{
		Contact contact = contactRepository.getContact(id);
		if(contact == null) 
		{
			Map<String, String> message = new HashMap<>();
			message.put("error", "No contact with the given id");
			return Response.status(Response.Status.NOT_FOUND).entity(message).build();
		}
		return Response.status(Response.Status.OK).entity(contact).build();
	}
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/delete/{id}")
	public Response deleteContact(@PathParam("id") String id) {
		Contact deletecontact = contactRepository.deleteContact(id);
		if(deletecontact == null) {
			Map<String, String> message = new HashMap<>();
			message.put("error", "No contact with the given id");
			return Response.status(Response.Status.NOT_FOUND).entity(message).build();
		}
		return Response.status(Response.Status.OK).entity(deletecontact).build();
	

	}
		
	
	
	@PUT
	@Path("/update/{id}")
	@Produces("application/json")
	    public Response updateContact(@PathParam("id")String id, Contact contact)
	    {
	        Contact temp = contactRepository.updateContact(id,contact);
	        if(contact == null)
	        {
	            return Response.status(404).build();
	        }
	        else
	        {
	        temp.setFirstName(contact.getFirstName());
	        temp.setLastName(contact.getLastName());
	        temp.setEmail(contact.getEmail());
	        temp.setId(id);
	        return Response.status(200).entity(temp).build();
	        }
	    }
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addContact(Contact contact) 
	{
		Contact createdContact = contactRepository.addContact(contact);
		return Response.status(Response.Status.CREATED).entity(createdContact).build();
	}
			
}
