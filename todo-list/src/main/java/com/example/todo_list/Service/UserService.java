package com.example.todo_list.Service;

import com.example.todo_list.Repository.UserRepository;
import com.example.todo_list.Repository.UserLoginHistoryRepository;
import com.example.todo_list.entity.User;
import com.example.todo_list.entity.UserLoginHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserLoginHistoryRepository userLoginHistoryRepository;

    public UserService(
            UserRepository userRepository,
            UserLoginHistoryRepository userLoginHistoryRepository) {

        this.userRepository = userRepository;
        this.userLoginHistoryRepository = userLoginHistoryRepository;
    }

    // Register
    public User register(User user) {
        return userRepository.save(user);
    }

    // Login
    public User login(String email, String password) {

        User user = userRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);

        if (user != null) {

            UserLoginHistory history = new UserLoginHistory();

            history.setUserId(user.getId());
            history.setLoginTime(LocalDateTime.now());
            history.setStatus("LOGIN");

            userLoginHistoryRepository.save(history);
        }

        return user;
    }

    // Logout
    public User logout(Long userId) {

        UserLoginHistory history =
                userLoginHistoryRepository
                        .findFirstByUserIdAndStatusOrderByLoginTimeDesc(
                                userId,
                                "LOGIN"
                        )
                        .orElse(null);

        if (history != null) {

            history.setLogoutTime(LocalDateTime.now());
            history.setStatus("LOGOUT");

            userLoginHistoryRepository.save(history);
        }

        return userRepository.findById(userId).orElse(null);
    }
}