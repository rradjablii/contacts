package com.rradjabli.contacts.controller;

import com.rradjabli.contacts.model.Contact;
import com.rradjabli.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<ArrayList<Contact>> contacts(){
        ArrayList<Contact> contacts=contactService.getContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        Contact contact1=contactService.addContact(contact);
        return new ResponseEntity<>(contact1,HttpStatus.OK);
    }


}
