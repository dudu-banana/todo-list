package com.example.simple_todo_list.view.dto;

import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import lombok.Getter;

@Getter
public class ToDoListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ToDoListViewResponse(ToDoEntity toDoEntity) {
        this.id = toDoEntity.getId();
        this.title = toDoEntity.getTitle();
        this.content = toDoEntity.getContent();
    }
}
