package com.em.test_em.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.em.test_em.beans.Comments;

public interface CommentsRepository extends JpaRepository<Comments,Long>{
    List<Comments> findByTaskId(Long taskId);
    
}
