package com.group.blog.blog.services;

import com.group.blog.blog.entities.Tag;
import com.group.blog.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepo;

    public void createTag(Tag tag){
        tagRepo.save(tag);
    }

    public List<Tag> getAll(){
        return tagRepo.findAll();
    }

    public Object getTagById( Long id){
        return tagRepo.findById(id);
    }

    public Tag updateTag(Long id, Tag t){
        return tagRepo.findById(id).map(tag -> {
            tag.setTagName(t.getTagName());
            return tagRepo.save(tag);
        }).orElseThrow(() -> new ResourceNotFoundException("Tag ID <" + id + "> not found"));
    }
    public ResponseEntity<?> deleteTag(Long id){
        return tagRepo.findById(id).map(tag -> {
            tagRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Tag Id <" + id + "> not found"));
    }
}
