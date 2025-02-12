package com.example.spring2.controller;

import com.example.spring2.dto.UserRequestDto;
import com.example.spring2.dto.UserResponseDto;
import com.example.spring2.entity.User;
import com.example.spring2.repository.UserRepository;
import com.example.spring2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.save(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto
    ) {
            return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
        }
}
