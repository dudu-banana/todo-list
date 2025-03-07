package com.example.simple_todo_list.todolist.dto;

import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import lombok.Getter;

@Getter
public class ToDoResponse {
    private final long id;
    private final String title;
    private final String content;

    public ToDoResponse(ToDoEntity toDoEntity) {
        this.id = toDoEntity.getId();
        this.title = toDoEntity.getTitle();
        this.content = toDoEntity.getContent();
    }
}