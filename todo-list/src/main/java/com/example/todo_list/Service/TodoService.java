package com.example.todo_list.Service;

import com.example.todo_list.entity.Todo;
import com.example.todo_list.entity.TodoHistory;
import com.example.todo_list.Repository.TodoRepository;
import com.example.todo_list.Repository.TodoHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoHistoryRepository todoHistoryRepository;

    public TodoService(
            TodoRepository todoRepository,
            TodoHistoryRepository todoHistoryRepository) {

        this.todoRepository = todoRepository;
        this.todoHistoryRepository = todoHistoryRepository;
    }

    // 1️⃣ Create Task
    public Todo createTask(Todo todo) {

        Todo savedTodo = todoRepository.save(todo);

        TodoHistory history = new TodoHistory();

        history.setTodoId(savedTodo.getId());
        history.setTaskName(savedTodo.getTaskName());
        history.setStatus(savedTodo.getStatus());
        history.setAction("CREATE");
        history.setActionTime(LocalDateTime.now());

        todoHistoryRepository.save(history);

        return savedTodo;
    }

    // 2️⃣ Get All Active Tasks
    public List<Todo> getAllTasks() {

        return todoRepository.findByStatus(1);
    }

    // 3️⃣ Get Particular User's Active Tasks
    public List<Todo> getTasksByUser(Long userId) {

        return todoRepository.findByUserIdAndStatus(userId, 1);
    }

    // 4️⃣ Update Task
    public Todo updateTask(Long id, Todo updatedTodo) {

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));

        existingTodo.setTaskName(updatedTodo.getTaskName());
        existingTodo.setStatus(updatedTodo.getStatus());

        Todo savedTodo = todoRepository.save(existingTodo);

        TodoHistory history = new TodoHistory();

        history.setTodoId(savedTodo.getId());
        history.setTaskName(savedTodo.getTaskName());
        history.setStatus(savedTodo.getStatus());
        history.setAction("UPDATE");
        history.setActionTime(LocalDateTime.now());

        todoHistoryRepository.save(history);

        return savedTodo;
    }

    // 5️⃣ Delete Task
    public Todo deleteTask(Long id) {

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));

        TodoHistory history = new TodoHistory();

        history.setTodoId(existingTodo.getId());
        history.setTaskName(existingTodo.getTaskName());
        history.setStatus(0);
        history.setAction("DELETE");
        history.setActionTime(LocalDateTime.now());

        todoHistoryRepository.save(history);

        existingTodo.setStatus(0);

        return todoRepository.save(existingTodo);
    }
}