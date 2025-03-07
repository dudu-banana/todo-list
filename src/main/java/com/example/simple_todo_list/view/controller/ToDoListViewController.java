package com.example.simple_todo_list.view.controller;

import com.example.simple_todo_list.service.ToDoService;
import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import com.example.simple_todo_list.view.dto.ToDoListViewResponse;
import com.example.simple_todo_list.view.dto.ToDoViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ToDoListViewController {
    private final ToDoService toDoService;

    @GetMapping("/list")
    public String getToDo(Model model) {
        List<ToDoListViewResponse> todo = toDoService.findAll().stream()
                .map(ToDoListViewResponse::new)
                .toList();
        model.addAttribute("todo", todo);

        return "toDoList";
    }

    @GetMapping("/list/{id}")
    public String getToDo(@PathVariable Long id, Model model) {
        ToDoEntity toDoEntity = toDoService.findById(id);
        model.addAttribute("todo", new ToDoViewResponse(toDoEntity));

        return "toDoContent";
    }
}
