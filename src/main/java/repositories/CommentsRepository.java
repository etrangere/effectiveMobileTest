package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import beans.Comments;

public interface CommentsRepository extends JpaRepository<Comments,Long>{

}
