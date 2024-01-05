package com.em.test_em.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
//to keep database fresh for each test 
@Transactional
public class CommentServiceImplTest {

}
/*
 *  Optional<CommentDTO> getCommentById(Long id);

    List<CommentDTO> getCommentByTask(Long taskId);
    
    CommentDTO getCommentByIdAndTaskId(Long task_id,Long comment_id);

    CommentDTO createCommentForTask(Long task_id,CommentDTO commentDTO);
    
    List<CommentDTO> getAllCommentsForTask(Long task_id);
    
    void deleteCommentForTask(Long task_id,Long comment_id);
    
    CommentDTO updateCommentForTask(Long task_id,Long comment_id,CommentDTO updatedCommentDTO);
 * */
 