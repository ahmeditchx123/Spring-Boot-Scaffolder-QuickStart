package com.group.blog.blog.controllers;


import com.group.blog.blog.entities.Comment;
import com.group.blog.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/comments")
public class CommentController {
    @Autowired
    CommentService comService;

    @PostMapping("/blog/{blogId}/create_comment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Comment createComment(@PathVariable (value = "blogId") Long blogId, @RequestBody Comment comment){
        return comService.createComment(blogId,comment);
    }

    @GetMapping("getAll_comments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Comment> getAll(){
        return comService.getAll();
    }

    @GetMapping("/getComment_byId/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Object getBlogById(@PathVariable("id") Long id){
        return comService.getComById(id);
    }

    @PutMapping("blogs/{blogId}/updateComment/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Comment updateComment(@PathVariable(value ="commentId") Long comId,@PathVariable(value ="blogId") Long blogId,@RequestBody Comment com){
        return comService.updateComment(comId,blogId,com);
    }

    @DeleteMapping("/blogs/{blogId}/comments/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteComment(@PathVariable(value= "blogId") Long blogId,@PathVariable(value= "commentId") Long comId){
        comService.deleteComment(blogId,comId);
    }
}
