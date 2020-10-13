package com.group.blog.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String tagName;

    @JsonIgnoreProperties(value = {"tags"})
    @ManyToMany(fetch = FetchType.LAZY,
             cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
             },
             mappedBy = "tags")
    private Set<Blog> blogs = new HashSet<>();
    public Tag() {

    }
    public Tag(String name) {
        this.tagName = name;
    }

    public Long getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }
}
