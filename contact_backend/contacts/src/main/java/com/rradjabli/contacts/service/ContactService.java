package com.rradjabli.contacts.service;

import com.rradjabli.contacts.jpa.ContactsJPA;
import com.rradjabli.contacts.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactsJPA repo;

    public ArrayList<Contact> getContacts(){
        return repo.findAll();
    }

    public Contact addContact(Contact contact){
        if(validation(contact)){
            return repo.save(contact);
        }else{
            System.out.println("Already used phone number");
            return null;
        }
    }

    private boolean validation(Contact contact){
        boolean validated=true;
        ArrayList<Contact> contacts = repo.findAll();
        System.out.println(contacts);
        for(Contact c:contacts){
            if(c.getPhone().equals(contact.getPhone()))validated=false;
        }
        return validated;
    }

}
