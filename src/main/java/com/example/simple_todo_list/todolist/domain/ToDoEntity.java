package com.example.simple_todo_list.todolist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content")
    private String content;

    @Builder
    public ToDoEntity(String title, String content) {
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 엔티티 생성시 시간 저장
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 엔티티 수정 시 시간 저장
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
