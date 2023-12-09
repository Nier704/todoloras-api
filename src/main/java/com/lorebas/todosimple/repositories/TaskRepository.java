package com.lorebas.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lorebas.todosimple.models.Task;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
   // QUERYS

   // method 1
   List<Task> findByUser_Id(Long id);

   // method 2
   @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
   List<Task> findByUserId_2(@Param("id") Long id);

   // method 3
   @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
   List<Task> findByUserId_3(@Param("id") Long id);
}
