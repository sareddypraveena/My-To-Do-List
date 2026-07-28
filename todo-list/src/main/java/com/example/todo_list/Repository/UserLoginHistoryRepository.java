package com.example.todo_list.Repository;

import com.example.todo_list.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginHistoryRepository
        extends JpaRepository<UserLoginHistory, Long> {

    Optional<UserLoginHistory>
    findFirstByUserIdAndStatusOrderByLoginTimeDesc(
            Long userId,
            String status
    );
}