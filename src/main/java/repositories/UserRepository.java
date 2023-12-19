package repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import beans.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
