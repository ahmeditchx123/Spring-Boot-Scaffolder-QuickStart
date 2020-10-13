package com.group.blog.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column
    @Lob
    private String comText;
    // table related in a many to one relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // set blog_id as a foreign key in comment table
    @JsonIgnoreProperties(value = {"comments"})
    @JoinColumn(name = "blog_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Blog blog;

    public Comment() {
    }

    public Comment(Long id, String comText, Blog blog) {
        this.id = id;
        this.comText = comText;
        this.blog = blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Blog getBlog() {
        return blog;
    }

    public Long getId() {
        return id;
    }

    public String getComText() {
        return comText;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComText(String comText) {
        this.comText = comText;
    }
}
