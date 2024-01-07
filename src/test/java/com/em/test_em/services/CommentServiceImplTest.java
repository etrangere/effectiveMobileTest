package com.em.test_em.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import com.em.test_em._DTO.CommentDTO;


@ExtendWith(MockitoExtension.class)
//to keep database fresh for each test 
@Transactional
public class CommentServiceImplTest {


    @Mock
    private CommentServiceImpl commentServiceImplMock;

    @Test
    public void testgetCommentById() {
        
     // creating object and setting attribute
        CommentDTO expectedCommentById = new CommentDTO();
        expectedCommentById.setId(1L);

     // repository method and objective of test
        when(commentServiceImplMock.getCommentById(1L)).thenReturn(Optional.of(expectedCommentById));

     // what to be checked or verified
        Optional<CommentDTO> result = commentServiceImplMock.getCommentById(1L);

     // assert to check
        assertTrue(result.isPresent());
        assertEquals(expectedCommentById, result.get());
    }

    @Test
    public void testGetCommentByTask() {
        
     // creating object and setting attribute
        Long taskId = 1L;

        CommentDTO comment1 = new CommentDTO();
        comment1.setId(1L);

        CommentDTO comment2 = new CommentDTO();
        comment2.setId(2L);

     // repository method and objective of test
        when(commentServiceImplMock.getCommentByTask(taskId)).thenReturn(Arrays.asList(comment1, comment2));

     // what to be checked or verified
        List<CommentDTO> result = commentServiceImplMock.getCommentByTask(taskId);

     // assert to check
        assertEquals(2, result.size());
        assertEquals(comment1, result.get(0));
        assertEquals(comment2, result.get(1));
    }

    @Test
    public void testGetCommentByIdAndTaskId() {
        
     // creating object and setting attribute
        Long taskId = 1L;
        Long commentId = 1L;

        // create a sample comment
        CommentDTO expectedComment = new CommentDTO();
        expectedComment.setId(commentId);

     // repository method and objective of test
        when(commentServiceImplMock.getCommentByIdAndTaskId(taskId, commentId)).thenReturn(expectedComment);

     // what to be checked or verified
        CommentDTO result = commentServiceImplMock.getCommentByIdAndTaskId(taskId, commentId);

     // assert to check
        assertEquals(expectedComment, result);
    }

    @Test
    public void testCreateCommentForTask() {
        
     // creating object and setting attribute
        Long taskId = 1L;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setComment("Test comment");

     // repository method and objective of test
        when(commentServiceImplMock.createCommentForTask(taskId, commentDTO)).thenReturn(commentDTO);

     // what to be checked or verified
        CommentDTO result = commentServiceImplMock.createCommentForTask(taskId, commentDTO);

     // assert to check
        assertNotNull(result);
        assertEquals(commentDTO.getId(), result.getId());
        assertEquals(commentDTO.getComment(), result.getComment());
    }

    @Test
    public void testGetAllCommentsForTask() {
        
     // creating object and setting attribute
        Long taskId = 1L;
        CommentDTO comment1 = new CommentDTO();
        comment1.setId(1L);
        comment1.setComment("Comment 1");

        CommentDTO comment2 = new CommentDTO();
        comment2.setId(2L);
        comment2.setComment("Comment 2");

        List<CommentDTO> expectedComments = Arrays.asList(comment1, comment2);

     // repository method and objective of test
        when(commentServiceImplMock.getAllCommentsForTask(taskId)).thenReturn(expectedComments);

     // what to be checked or verified
        List<CommentDTO> result = commentServiceImplMock.getAllCommentsForTask(taskId);

     // assert to check
        assertNotNull(result);
        assertEquals(expectedComments.size(), result.size());

        // during each iteration check each comment in the list
        for (int i = 0; i < expectedComments.size(); i++) {
            CommentDTO expectedComment = expectedComments.get(i);
            CommentDTO actualComment = result.get(i);

            assertEquals(expectedComment.getId(), actualComment.getId());
            assertEquals(expectedComment.getComment(), actualComment.getComment());
           
        }
    }

    @Test
    public void testDeleteCommentForTask() {
        
     // creating object and setting attribute
        Long taskId = 1L;
        Long commentId = 1L;

     // repository method and objective of test
        Mockito.doNothing().when(commentServiceImplMock).deleteCommentForTask(taskId, commentId);

     // what to be checked or verified
        commentServiceImplMock.deleteCommentForTask(taskId, commentId);

        // verify that the repository method was called with the correct arguments
        Mockito.verify(commentServiceImplMock).deleteCommentForTask(taskId, commentId);
    }
    
    @Test
    public void testUpdateCommentForTask() {
        
     // creating object and setting attribute
        Long taskId = 1L;
        Long commentId = 1L;

        // Create a sample CommentDTO to update for the comment
        CommentDTO updatedCommentDTO = new CommentDTO();
        updatedCommentDTO.setId(commentId);
        updatedCommentDTO.setComment("Updated comment text");

     // repository method and objective of test
        Mockito.when(commentServiceImplMock.updateCommentForTask(taskId,commentId,updatedCommentDTO))
               .thenReturn(updatedCommentDTO);

     // what to be checked or verified
        CommentDTO result = commentServiceImplMock.updateCommentForTask(taskId, commentId, updatedCommentDTO);

        // assert to check
        assertEquals(updatedCommentDTO, result);
    }
}

 