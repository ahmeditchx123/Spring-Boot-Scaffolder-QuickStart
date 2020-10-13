package com.group.blog.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "person",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName"),
                @UniqueConstraint(columnNames = "email")
        })
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(max = 30)
    @Column
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column
    private String password;

    @NotBlank
    @Size(max = 50)
    @Column
    @Email
    private String email;
    // One to many between person and  blog
    @JsonIgnoreProperties(value = {"person"})
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Blog> blogs;

    // many to many between person and role
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Person() {
    }

    public Person(@NotNull String username,String email,String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
