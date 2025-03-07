package com.example.simple_todo_list.todolist.repository;

import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {
}
