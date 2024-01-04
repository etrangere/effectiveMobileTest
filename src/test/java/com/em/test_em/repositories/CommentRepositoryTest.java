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
        // Arrange
        CommentRepository commentRepository = mock(CommentRepository.class);
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "Comment 1"));
        comments.add(new Comment(2L, "Comment 2"));

        // Mock the behavior of the repository
        when(commentRepository.findByTaskId(1L)).thenReturn(comments);

        // Act
        List<Comment> result = commentRepository.findByTaskId(1L);

        // Assert
        assertEquals(2, result.size());
        // Add more assertions based on your requirements
    }

    @Test
    public void testExistsByIdAndTaskId() {
        // Arrange
        CommentRepository commentRepository = mock(CommentRepository.class);

        // Mock the behavior of the repository
        when(commentRepository.existsByIdAndTaskId(1L, 1L)).thenReturn(true);
        when(commentRepository.existsByIdAndTaskId(2L, 1L)).thenReturn(false);

        // Act
        boolean result1 = commentRepository.existsByIdAndTaskId(1L, 1L);
        boolean result2 = commentRepository.existsByIdAndTaskId(2L, 1L);

        // Assert
        assertTrue(result1);
        assertFalse(result2);
        // Add more assertions based on your requirements
    
    }
}
