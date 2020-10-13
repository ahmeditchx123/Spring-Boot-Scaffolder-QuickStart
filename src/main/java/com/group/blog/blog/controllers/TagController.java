package com.group.blog.blog.controllers;

import com.group.blog.blog.entities.Person;
import com.group.blog.blog.entities.Tag;
import com.group.blog.blog.services.PersonService;
import com.group.blog.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("createTag")
    public void createPerson(@RequestBody Tag tag){
        tagService.createTag(tag);
    }

    @GetMapping("getAll_tags")
    public List<Tag> getAll(){
        return tagService.getAll();
    }

    @GetMapping("/getTag_byId/{id}")
    public Object getTagById(@PathVariable("id") Long id){
        return tagService.getTagById(id);
    }

    @PutMapping("updateTag/{id}")
    public Tag updateTag(@PathVariable Long id,@RequestBody Tag tag){
        return tagService.updateTag(id,tag);
    }

    @DeleteMapping("deleteTag_byId/{id}")
    public void deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
    }
}
