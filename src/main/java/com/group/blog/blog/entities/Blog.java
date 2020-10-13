package com.group.blog.blog.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(max = 100)
    @Column
    private String blogTitle;
    // defined as a NotNull column to accept Large Object (Lob)
    @NotNull
    @Lob
    @Column
    private String blogContent;

    // table related in a many to one relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    // set person_id as a foreign key in blog table
    @JsonIgnoreProperties(value = {"blogs"})
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;

    // one to many relationship relating blog to comments
    @JsonIgnoreProperties(value = {"blog"})
    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Comment> comments;
    // many to many relationship relating blogs to tags
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    @JsonIgnoreProperties(value = {"blogs"})
    @JoinTable(name = "blog_tags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();
    public Blog() {
    }

    public Blog(Long id, @NotNull @Size(max = 100) String blogTitle, @NotNull String blogContent, Person person, Set<Comment> comments) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.person = person;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }
    public String getBlogContent() {
        return blogContent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
