package com.em.test_em._DTO;

public class CommentsDTO {
    
    private long id;
    
    private String comments;

    public CommentsDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentsDTO(long id, String comments) {
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

    @Override
    public String toString() {
        return "CommentsDTO [id=" + id + ", comments=" + comments + "]";
    }

    
}
