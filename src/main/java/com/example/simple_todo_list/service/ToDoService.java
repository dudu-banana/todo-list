package com.example.simple_todo_list.service;

import com.example.simple_todo_list.todolist.domain.ToDoEntity;
import com.example.simple_todo_list.todolist.dto.AddToDoRequest;
import com.example.simple_todo_list.todolist.dto.UpdateToDoRequest;
import com.example.simple_todo_list.todolist.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    // 할 일 추가
    public ToDoEntity save(AddToDoRequest request) {
        return toDoRepository.save(request.toDoEntity());
    }

    // 할 일 목록 조회
    public List<ToDoEntity> findAll() {
        return toDoRepository.findAll();
    }

    // 할 일 개별 조회
    public ToDoEntity findById(long id) {
        return toDoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

    // 할 일 삭제
    public void delete(long id) {
        toDoRepository.deleteById(id);
    }

    // 할 일 수정
    @Transactional
    public ToDoEntity update(long id, UpdateToDoRequest request) {
        ToDoEntity toDoEntity = toDoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));

        toDoEntity.update(request.getTitle(), request.getContent());

        return toDoEntity;
    }
}
