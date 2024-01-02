package com.em.test_em._DTO;

import java.util.Objects;

public class CommentDTO {
    
    private long id;
    
    private String comment;
    
    public CommentDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentDTO(long id, String comment) {
        super();
        this.id = id;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDTO [id=" + id + ", comment=" + comment + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommentDTO other = (CommentDTO) obj;
        return Objects.equals(comment, other.comment) && id == other.id;
    }
    
    
}
