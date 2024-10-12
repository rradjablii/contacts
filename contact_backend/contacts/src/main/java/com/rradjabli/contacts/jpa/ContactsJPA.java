package com.rradjabli.contacts.jpa;

import com.rradjabli.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ContactsJPA extends JpaRepository<Contact,Long> {
    ArrayList<Contact> findAll();
}
