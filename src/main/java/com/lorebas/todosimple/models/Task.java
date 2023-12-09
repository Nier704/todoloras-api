package com.lorebas.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true)
   public Long id;

   @ManyToOne  // varias TASKS sao de um usuario
   @JoinColumn(name = "user_id", nullable = false, updatable = false) // vai se referenciar ao ID do usuario
   private User user;
   
   @Column(name = "description", length = 255, nullable = false)
   @NotNull
   @NotEmpty
   @Size(min = 1, max = 255)
   public String description;


   public Task() {
   }


   public Task(Long id, User user, String description) {
      this.id = id;
      this.user = user;
      this.description = description;
   }


   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(user, task.user) && Objects.equals(description, task.description);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, user, description);
   }
   

}
