package com.em.test_em.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.em.test_em.beans.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findByTaskId(Long taskId);
    
}
