package com.example.simple_todo_list.view.dto;

import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ToDoViewResponse {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ToDoViewResponse(ToDoEntity toDoEntity) {
        this.id = toDoEntity.getId();
        this.title = toDoEntity.getTitle();
        this.content = toDoEntity.getContent();
        this.createdAt = toDoEntity.getCreatedAt();
    }

}
