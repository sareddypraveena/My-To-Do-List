package com.example.todo_list.Controller;

import com.example.todo_list.Service.UserService;
import com.example.todo_list.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1️⃣ Register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        User savedUser = userService.register(user);

        return new ResponseEntity<>(
                savedUser,
                HttpStatus.CREATED
        );
    }

    // 2️⃣ Login
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {

        User loggedInUser = userService.login(
                user.getEmail(),
                user.getPassword()
        );

        if (loggedInUser == null) {
            return new ResponseEntity<>(
                    HttpStatus.UNAUTHORIZED
            );
        }

        return new ResponseEntity<>(
                loggedInUser,
                HttpStatus.OK
        );
    }

    // 3️⃣ Logout
    @PostMapping("/logout/{userId}")
    public ResponseEntity<User> logout(@PathVariable Long userId) {

        User user = userService.logout(userId);

        if (user == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }
}