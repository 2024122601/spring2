package com.example.spring2.service;

import com.example.spring2.dto.UserRequestDto;
import com.example.spring2.dto.UserResponseDto;
import com.example.spring2.entity.User;
import com.example.spring2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto save(UserRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserResponseDto> dtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto dto = new UserResponseDto(user.getId(), user.getName(),user.getEmail());
            dtos.add(dto);
        }
        return dtos;

    }
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 id 유저 없음")
        );

        return new UserResponseDto(user.getId(), user.getName(),user.getEmail());
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 id 유저 없음")
        );

        user.update(dto.getName(), dto.getEmail());
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
    @Transactional
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("그런 id가진 유저 없어서 삭제 못함");
        }

        userRepository.deleteById(id);
    }
}
