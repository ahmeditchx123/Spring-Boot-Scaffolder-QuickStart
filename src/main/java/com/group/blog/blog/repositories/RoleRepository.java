package com.group.blog.blog.repositories;

import java.util.Optional;

import com.group.blog.blog.entities.ERole;
import com.group.blog.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}