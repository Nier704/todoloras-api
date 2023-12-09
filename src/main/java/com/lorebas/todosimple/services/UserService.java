package com.lorebas.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorebas.todosimple.models.User;
import com.lorebas.todosimple.repositories.TaskRepository;
import com.lorebas.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

   @Autowired //para fazer o instaciamento de interfaces
   private UserRepository userRepository;

   @Autowired
   private TaskRepository taskRepository;

   public User findById(Long id){
      Optional<User> user = this.userRepository.findById(id);

      return user.orElseThrow(() -> new RuntimeException(
         "User not finded! User ID: " + id + ", Tipo: " + User.class.getName()
      ));
   }

   @Transactional // recomendado usar em CREATE e UPDATE para forcar a ida de todas as informacoes e nao faltar nada.
   public User create(User obj){
      obj.setId(null); // para ter certeza que vai ser um novo usuario.
      obj = this.userRepository.save(obj);
      this.taskRepository.saveAll(obj.getTasks());
      return obj;
   }

   @Transactional
   public User updateUser(User obj){
      User newObj = this.findById(obj.getId());
      newObj.setPassword(obj.getPassword());
      return this.userRepository.save(newObj);
   }

   public void deleteUser(Long id){
      User newObj = this.findById(id);
      try {
         this.userRepository.delete(newObj);
      } catch (Exception e){
         throw new RuntimeException("Nao eh possivel excluir pois ha entidades relacionadas");
      }
   }

}
