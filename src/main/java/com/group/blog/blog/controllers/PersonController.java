package com.group.blog.blog.controllers;

import com.group.blog.blog.entities.Person;
import com.group.blog.blog.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.group.blog.blog.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("app/persons")
public class PersonController {
    @Autowired
    PersonService perService;

    @Autowired
    PersonRepository personRepo;

    @PostMapping("createPerson")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createPerson(@RequestBody Person pers){
        perService.createPerson(pers);
    }

    @GetMapping("getAll_persons")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Person> getAll(){
        return perService.getAll();
    }

    @GetMapping("/getPerson_byId/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Object getPersonById(@PathVariable("id") Long id){
        return perService.getPersonById(id);
    }

    @PutMapping("updatePerson/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Person updatePerson(@PathVariable Long id,@RequestBody Person pers){
        return perService.updatePerson(id,pers);
    }
    // Deleting person is granted only to Admin role
    @DeleteMapping("deletePerson_byId/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePerson(@PathVariable Long id){
        perService.deletePerson(id);
    }

}
