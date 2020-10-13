package com.group.blog.blog.repositories;

import com.group.blog.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Override
    List<Comment> findAll();

    @Override
    Optional<Comment> findById(Long id);

    @Override
    void deleteById(Long id);

    Set<Comment> findByBlogId(Long id);

    Optional<Comment> findByIdAndBlogId(Long omId,Long blogId);
}
