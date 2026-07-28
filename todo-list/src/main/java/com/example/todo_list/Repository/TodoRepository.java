package com.example.todo_list.Repository;

import com.example.todo_list.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // All active tasks
    List<Todo> findByStatus(Integer status);

    // Particular user's active tasks
    List<Todo> findByUserIdAndStatus(
            Long userId,
            Integer status
    );
}