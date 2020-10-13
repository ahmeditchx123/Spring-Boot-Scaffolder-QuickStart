package com.group.blog.blog.repositories;

import com.group.blog.blog.entities.Blog;
import com.group.blog.blog.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Override
    List<Blog> findAll();

    @Override
    Optional<Blog> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    Set<Blog> findByPersonId(Long personId);

    Optional<Blog> findByIdAndPersonId(Long id, Long postId);

}
