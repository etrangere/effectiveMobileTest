package com.em.test_em.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.em.test_em.beans.Comment;


@DataJpaTest
public class CommentRepositoryTest {

    @Test
    public void testFindByTaskId() {
        
        // mock the repository behavior
        CommentRepository commentRepository = mock(CommentRepository.class);
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "Comment 1"));
        comments.add(new Comment(2L, "Comment 2"));

        // repository method
        when(commentRepository.findByTaskId(1L)).thenReturn(comments);

        // action
        List<Comment> result = commentRepository.findByTaskId(1L);

        // assert
        assertEquals(2, result.size());
       
    }

    @Test
    public void testExistsByIdAndTaskId() {
      
        CommentRepository commentRepository = mock(CommentRepository.class);

        // mock the repository behavior
        when(commentRepository.existsByIdAndTaskId(1L, 1L)).thenReturn(true);
        when(commentRepository.existsByIdAndTaskId(2L, 1L)).thenReturn(false);

        // action
        boolean result1 = commentRepository.existsByIdAndTaskId(1L, 1L);
        boolean result2 = commentRepository.existsByIdAndTaskId(2L, 1L);

        // assert
        assertTrue(result1);
        assertFalse(result2);
       
    
    }
}
