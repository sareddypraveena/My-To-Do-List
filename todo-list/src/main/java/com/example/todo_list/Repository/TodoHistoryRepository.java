package com.example.todo_list.Repository;

import com.example.todo_list.entity.TodoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoHistoryRepository
        extends JpaRepository<TodoHistory, Long> {
}