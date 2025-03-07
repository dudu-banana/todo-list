package com.example.simple_todo_list.todolist.dto;

import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddToDoRequest {

    private String title;
    private String content;

    public ToDoEntity toDoEntity() {
        return ToDoEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
}
