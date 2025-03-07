package com.example.simple_todo_list.todolist.controller;

import com.example.simple_todo_list.service.ToDoService;
import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import com.example.simple_todo_list.todolist.dto.AddToDoRequest;
import com.example.simple_todo_list.todolist.dto.ToDoResponse;
import com.example.simple_todo_list.todolist.dto.UpdateToDoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환
@CrossOrigin(origins = "*")
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("/todo")
    public ResponseEntity<ToDoEntity> addToDo(@RequestBody AddToDoRequest request) {
        ToDoEntity savedToDo = toDoService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedToDo);
    }

    @GetMapping("/todo")
    public ResponseEntity<List<ToDoResponse>> findAllToDo() {
        List<ToDoResponse> todo = toDoService.findAll()
                .stream()
                .map(ToDoResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(todo);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDoResponse> findTodo(@PathVariable long id) {
        ToDoEntity toDoEntity = toDoService.findById(id);

        return ResponseEntity.ok()
                .body(new ToDoResponse(toDoEntity));
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable long id) {
        toDoService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<ToDoEntity> updateToDo(@PathVariable long id, @RequestBody UpdateToDoRequest request) {
        ToDoEntity updatedToDo = toDoService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedToDo);
    }
}
