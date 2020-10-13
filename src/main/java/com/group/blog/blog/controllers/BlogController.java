package com.group.blog.blog.controllers;

import com.group.blog.blog.entities.Blog;
import com.group.blog.blog.security.services.UserDetailsImpl;
import com.group.blog.blog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("app/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/create_blog")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Blog createBlog(@AuthenticationPrincipal UserDetailsImpl person, @RequestBody Blog blog){
        Long personId=person.getId();
        return blogService.createBlog(personId,blog);
    }

    @GetMapping("getAll_blogs")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Blog> getAll(){
        return blogService.getAll();
    }

    @GetMapping("/getBlog_byId/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Object getBlogById(@PathVariable("id") Long id){
        return blogService.getBlogById(id);
    }

    @PutMapping("person/{personId}/updateBlog/{blogId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Blog updateBlog(@PathVariable(value ="blogId") Long blogId,@AuthenticationPrincipal UserDetailsImpl person,@RequestBody Blog blog){
        Long personId=person.getId();
        return blogService.updateBlog(blogId,personId,blog);
    }
    //  @AuthenticationPrincipal is used to get the current authentificated user
    @DeleteMapping("/comments/{blogId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetailsImpl person,
                                           @PathVariable (value = "blogId") Long blogId)
    {
        Long personId=person.getId();
        return blogService.deleteBlog(personId,blogId);
    }

    @GetMapping("/myBlogs")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Set<Blog> getAllCommentsByPersonId(@AuthenticationPrincipal UserDetailsImpl person) {
        Long personId=person.getId();
        return blogService.getAllBlogsByPersonId(personId);
    }
}
