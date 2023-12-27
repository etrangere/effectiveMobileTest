package com.em.test_em.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.em.test_em._DTO.CommentsDTO;
import com.em.test_em.beans.Comments;
import com.em.test_em.repositories.CommentsRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ModelMapper mapper;

    // get all comments
    @Override
    public List<CommentsDTO> getAllComments() {
        List<Comments> comments = commentsRepository.findAll();
        return mapToDTOList(comments);
    }

    // get comment by id
    @Override
    public Optional<CommentsDTO> getCommentsById(Long id) {
        Optional<Comments> commentsOptional = commentsRepository.findById(id);
        return commentsOptional.map(this::mapToDTO);
    }

    // create comment
    @Override
    public CommentsDTO createComment(CommentsDTO commentsDTO) {
        Comments comments = mapToEntity(commentsDTO);
        return mapToDTO(commentsRepository.save(comments));
    }

    @Override
    public List<CommentsDTO> getCommentsByTask(Long taskId) {
        List<Comments> comments = commentsRepository.findByTaskId(taskId);
        return mapToDTOList(comments);
    }
    
    // update comment
    @Override
    public CommentsDTO updateComment(CommentsDTO commentsDTO) {
        if (!commentsRepository.existsById(commentsDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find the comment to update");
        }
        Comments comments = mapToEntity(commentsDTO);
        return mapToDTO(commentsRepository.save(comments));
    }

    // delete comment
    @Override
    public void deleteComment(Long id) {
        if (!commentsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find comment to delete");
        }
        commentsRepository.deleteById(id);
        if (commentsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error deleting comment");
        }
    }

    private CommentsDTO mapToDTO(Comments comments) {
        return mapper.map(comments, CommentsDTO.class);
    }

    private List<CommentsDTO> mapToDTOList(List<Comments> comments) {
        return comments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private Comments mapToEntity(CommentsDTO commentsDTO) {
        return mapper.map(commentsDTO, Comments.class);
    }
}
