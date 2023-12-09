package com.lorebas.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorebas.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
