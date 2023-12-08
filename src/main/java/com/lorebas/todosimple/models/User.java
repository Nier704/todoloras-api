package com.lorebas.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
   public static final String TABLE_NAME = "user";
   private interface CreateUser {}
   private interface UpdateUser {}

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true)
   private Long id;
   
   @Column(name = "username", length = 100, nullable = false, unique = true)
   @NotNull(groups = {CreateUser.class, UpdateUser.class})
   @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
   @Size(groups = {CreateUser.class, UpdateUser.class}, min = 2, max = 100)
   private String username;

   @JsonProperty(access = Access.WRITE_ONLY)
   @Column(name = "password", length = 12, nullable = false)
   @NotNull(groups = {CreateUser.class, UpdateUser.class})
   @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
   @Size(groups = {CreateUser.class, UpdateUser.class}, min = 4, max = 12)
   private String password;

   //private List<Task> tasks = new ArrayList<Task>();


   public User(){

   }

   public User(String username, String password) {
      this.username = username;
      this.password = password;
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
