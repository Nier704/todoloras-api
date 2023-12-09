package com.lorebas.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
   private interface CreateUser {}
   private interface UpdateUser {}

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id", unique = true)
   private Long id;
   
   @Column(name = "user_name", length = 100, nullable = false, unique = true)
   @NotNull(groups = {CreateUser.class, UpdateUser.class})
   @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
   @Size(groups = {CreateUser.class, UpdateUser.class}, min = 2, max = 100)
   private String username;

   @JsonProperty(access = Access.WRITE_ONLY)
   @Column(name = "user_password", length = 12, nullable = false)
   @NotNull(groups = {CreateUser.class, UpdateUser.class})
   @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
   @Size(groups = {CreateUser.class, UpdateUser.class}, min = 4, max = 12)
   private String password;

   @OneToMany(mappedBy = "user")  // Um usuario pode ter muitas tasks // o "user" eh o nome da variavel -
                                  // que armazenamos o User no Task.java
   private List<Task> tasks = new ArrayList<Task>();

   public User(){

   }

   public User(String username, String password) {
      this.username = username;
      this.password = password;
   }

   public List<Task> getTasks() {
      return this.tasks;
   }

   public void setTasks(List<Task> tasks) {
      this.tasks = tasks;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
    public boolean equals(Object o) {
      if (o == this){
         return true;
      }
      if (o == null){
         return false;
      }
      if (!(o instanceof User)){
         return false;
      }

      return false;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (this.id == null ? 0 : this.id.hashCode());

      return result;
   }
   

}
