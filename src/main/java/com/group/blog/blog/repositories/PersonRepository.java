package com.group.blog.blog.repositories;

import com.group.blog.blog.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    @Override
    List<Person> findAll();

    @Override
    Optional<Person> findById(Long id);

    @Override
    void deleteById(Long aLong);


    Optional<Person> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
