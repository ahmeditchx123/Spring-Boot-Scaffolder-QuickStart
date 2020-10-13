package com.group.blog.blog.services;

import com.group.blog.blog.entities.Blog;
import com.group.blog.blog.repositories.BlogRepository;
import com.group.blog.blog.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepo;
    @Autowired
    private PersonRepository personRepo;
    // create a new blog by a person
    public Blog createBlog(Long personId,Blog blog){
        return personRepo.findById(personId).map(person -> {
            blog.setPerson(person);
            return blogRepo.save(blog);
        }).orElseThrow(() -> new ResourceNotFoundException("Person Id <" + personId + "> not found"));
    }
    // retrieve all blogs
    public List<Blog> getAll(){
        return blogRepo.findAll();
    }
    // retrieve a blog by its id
    public Object getBlogById( Long id){
        return blogRepo.findById(id);
    }
    // update blog  fields
    public Blog updateBlog(Long personId,Long blogId, Blog bl){
        if(!personRepo.existsById(personId)) {
            throw new ResourceNotFoundException("PersonId <" + personId + "> not found");
        }
        return blogRepo.findById(blogId).map(blog -> {
            blog.setBlogTitle(bl.getBlogTitle());
            blog.setBlogContent(bl.getBlogContent());
            return blogRepo.save(blog);
        }).orElseThrow(() -> new ResourceNotFoundException("Blog Id <" + blogId + ">not found"));
    }
    // delete a blog
    public ResponseEntity<?> deleteBlog(Long personId, Long blogId){
        return blogRepo.findByIdAndPersonId(blogId, personId).map(blog -> {
            blogRepo.deleteById(blogId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Blog not found with id <" + blogId + " and personId " + personId));
    }
    // retrieve all blogs of a person
    public Set<Blog> getAllBlogsByPersonId(Long personId) {
        return blogRepo.findByPersonId(personId);
    }
}
