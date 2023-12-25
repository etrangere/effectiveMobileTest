package com.em.test_em._DTO;

public class CommentDTO {
    
    private long id;
    
    private String comments;

    public CommentDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentDTO(long id, String comments) {
        super();
        this.id = id;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    
}
