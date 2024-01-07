package com.em.test_em.repositories;

import com.em.test_em.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for handling User entities. */
public interface UserRepository extends JpaRepository<User, Long> {}
