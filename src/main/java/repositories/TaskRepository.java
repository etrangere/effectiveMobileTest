package repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import beans.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

}
