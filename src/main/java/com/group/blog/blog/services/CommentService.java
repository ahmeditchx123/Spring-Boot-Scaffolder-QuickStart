package com.group.blog.blog.services;

import com.group.blog.blog.entities.Blog;
import com.group.blog.blog.entities.Comment;
import com.group.blog.blog.repositories.BlogRepository;
import com.group.blog.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentService {
    @Autowired
    CommentRepository comRepo;
    @Autowired
    BlogRepository blogRepo;
    // Create
    public Comment createComment(Long blogId,Comment com){
        return blogRepo.findById(blogId).map(blog -> {
            com.setBlog(blog);
            return comRepo.save(com);
        }).orElseThrow(() -> new ResourceNotFoundException("Blog Id <" + blogId + "> not found"));
    }

    // Find All
    public List<Comment> getAll(){
        return comRepo.findAll();
    }

    // Find One by id
    public Object getComById( Long id){
        return comRepo.findById(id);
    }

    // Update a comment
    public Comment updateComment(Long comId,Long blogId,Comment com){
        if(!blogRepo.existsById(blogId)) {
            throw new ResourceNotFoundException("BlogId <" + blogId + "> not found");
        }
        return comRepo.findById(comId).map(comment -> {
            comment.setComText(com.getComText());
            return comRepo.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Blog Id <" + blogId + ">not found"));
    }

    // delete a blog
    public ResponseEntity<?> deleteComment(Long blogId, Long comId){
        return comRepo.findByIdAndBlogId(comId, blogId).map(comment -> {
            comRepo.deleteById(comId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id <" + comId + " and blogId " + blogId));
    }

    // retrieve all comments of a blog
    public Set<Comment> getAllCommentsByBlogId(Long blogId) {
        return comRepo.findByBlogId(blogId);
    }
}
