package com.example.todo_list.Repository;

import com.example.todo_list.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(
            String email,
            String password
    );
}