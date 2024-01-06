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
        CommentDTO expectedCommentById = new CommentDTO();
        expectedCommentById.setId(1L);

        // Mock the repository method
        when(commentServiceImplMock.getCommentById(1L)).thenReturn(Optional.of(expectedCommentById));

        // Call the service method to get the task by ID
        Optional<CommentDTO> result = commentServiceImplMock.getCommentById(1L);

        // Now, you can perform assertions on the result if needed
        assertTrue(result.isPresent());
        assertEquals(expectedCommentById, result.get());
    }

    @Test
    public void testGetCommentByTask() {
        Long taskId = 1L;

        // Create some sample comments
        CommentDTO comment1 = new CommentDTO();
        comment1.setId(1L);

        CommentDTO comment2 = new CommentDTO();
        comment2.setId(2L);

        // Mock this service to return these comments
        when(commentServiceImplMock.getCommentByTask(taskId)).thenReturn(Arrays.asList(comment1, comment2));

        // Call the getCommentByTask method in impl
        List<CommentDTO> result = commentServiceImplMock.getCommentByTask(taskId);

        // Verify that the result matches the expected comments
        assertEquals(2, result.size());
        assertEquals(comment1, result.get(0));
        assertEquals(comment2, result.get(1));
    }

    @Test
    public void testGetCommentByIdAndTaskId() {
        Long taskId = 1L;
        Long commentId = 1L;

        // create a sample comment
        CommentDTO expectedComment = new CommentDTO();
        expectedComment.setId(commentId);

        // mock this service to return the comment
        when(commentServiceImplMock.getCommentByIdAndTaskId(taskId, commentId)).thenReturn(expectedComment);

        // call the getCommentByIdAndTaskId method in impl
        CommentDTO result = commentServiceImplMock.getCommentByIdAndTaskId(taskId, commentId);

        // assert
        assertEquals(expectedComment, result);
    }

    @Test
    public void testCreateCommentForTask() {
        // Specify the task ID and create a sample CommentDTO
        Long taskId = 1L;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setComment("Test comment");

        // Mock the repository method
        when(commentServiceImplMock.createCommentForTask(taskId, commentDTO)).thenReturn(commentDTO);

        // Call the service method to create a comment for the task
        CommentDTO result = commentServiceImplMock.createCommentForTask(taskId, commentDTO);

        // Now, you can perform assertions on the result if needed
        assertNotNull(result);
        assertEquals(commentDTO.getId(), result.getId());
        assertEquals(commentDTO.getComment(), result.getComment());
    }

    @Test
    public void testGetAllCommentsForTask() {
        // Specify the task ID and create some sample CommentDTOs
        Long taskId = 1L;
        CommentDTO comment1 = new CommentDTO();
        comment1.setId(1L);
        comment1.setComment("Comment 1");

        CommentDTO comment2 = new CommentDTO();
        comment2.setId(2L);
        comment2.setComment("Comment 2");

        List<CommentDTO> expectedComments = Arrays.asList(comment1, comment2);

        // Mock repository to return the list of comments
        when(commentServiceImplMock.getAllCommentsForTask(taskId)).thenReturn(expectedComments);

        // Call service to get all comments for the task
        List<CommentDTO> result = commentServiceImplMock.getAllCommentsForTask(taskId);

        // Now, you can perform assertions on the result if needed
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
        // specify the task ID and comment ID to be deleted
        Long taskId = 1L;
        Long commentId = 1L;

        // mock the repository method
        Mockito.doNothing().when(commentServiceImplMock).deleteCommentForTask(taskId, commentId);

        // call the service method to delete a comment for a task
        commentServiceImplMock.deleteCommentForTask(taskId, commentId);

        // verify that the repository method was called with the correct arguments
        Mockito.verify(commentServiceImplMock).deleteCommentForTask(taskId, commentId);
    }
    
    @Test
    public void testUpdateCommentForTask() {
        //Specify the task ID and comment ID 
        Long taskId = 1L;
        Long commentId = 1L;

        // Create a sample CommentDTO to update for the comment
        CommentDTO updatedCommentDTO = new CommentDTO();
        updatedCommentDTO.setId(commentId);
        updatedCommentDTO.setComment("Updated comment text");

        //Mock the repository method
        Mockito.when(commentServiceImplMock.updateCommentForTask(taskId,commentId,updatedCommentDTO))
               .thenReturn(updatedCommentDTO);

        //call method to update a comment for a task
        CommentDTO result = commentServiceImplMock.updateCommentForTask(taskId, commentId, updatedCommentDTO);

        // assert
        assertEquals(updatedCommentDTO, result);
    }
}

 