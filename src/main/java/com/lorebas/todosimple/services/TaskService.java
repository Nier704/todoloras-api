package com.lorebas.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorebas.todosimple.models.Task;
import com.lorebas.todosimple.models.User;
import com.lorebas.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

   @Autowired
   private TaskRepository taskRepository;

   // ja utilizando o service que montei
   private UserService userService;

   public Task findById(Long id){
      Optional<Task> task = this.taskRepository.findById(id);

      return task.orElseThrow(() -> new RuntimeException(
         "Task not finded! Task Id: " + id + ", Type: " + Task.class.getName()
      ));
   }

   @Transactional
   public Task create(Task obj){
      User user = this.userService.findById(obj.getUser().getId());
      obj.setId(null); // garantindo que seja uma task nova.
      obj.setUser(user);
      obj = taskRepository.save(obj);
      return obj;
   }

   @Transactional
   public Task update(Task obj){
      Task newObj = findById(obj.getId());
      newObj.setDescription(obj.getDescription());
      return this.taskRepository.save(newObj);
   }

   public void delete(Long id){
      Task obj = findById(id);
      try {                            // nesse caso, faco o try catch por que a task pode estar vinculada em alguma outra parte do codigo
         taskRepository.deleteById(obj.getId());   // ou seja, pode acabar danificando o back do site.
      } catch (Exception e){
         throw new RuntimeException("Nao eh possivel deletar, pois ha entidades relacionadas!");
      }
   }
}
