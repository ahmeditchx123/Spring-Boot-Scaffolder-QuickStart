package com.group.blog.blog.services;

import com.group.blog.blog.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.group.blog.blog.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepo;

    public void createPerson( Person pers){
        personRepo.save(pers);
    }
    public List<Person> getAll(){
        return personRepo.findAll();
    }
    public Object getPersonById( Long id){
        return personRepo.findById(id);
    }
    public Person updatePerson(Long id, Person pers){
        return personRepo.findById(id).map(person -> {
            person.setUserName(pers.getUserName());
            person.setPassword(pers.getPassword());
            person.setEmail(pers.getEmail());
            return personRepo.save(person);
        }).orElseThrow(() -> new ResourceNotFoundException("Person ID <" + id + "> not found"));
    }
    public ResponseEntity<?> deletePerson(Long id){
        return personRepo.findById(id).map(person -> {
            personRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Person Id <" + id + "> not found"));
    }
}
