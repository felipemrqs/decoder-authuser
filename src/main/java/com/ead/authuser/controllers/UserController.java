package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserModel> getById(@PathVariable UUID uuid) {
        Optional<UserModel> user = userService.findById(uuid);
        return user.map(u -> ResponseEntity.status(HttpStatus.OK).body(u)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID uuid) {
        return userService.findById(uuid).map(userModel -> {
            userService.deleteUser(uuid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID uuid, @RequestBody @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) {
        var userModelOptional = userService.findById(uuid);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var userModel = userModelOptional.get();
        userModel.setFullName(userDto.fullName());
        userModel.setPhoneNumber(userDto.phoneNumber());
        userModel.setCpf(userDto.cpf());
        userModel.setUpdatedAt(LocalDateTime.now());
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{uuid}/password")
    public ResponseEntity<Object> updateUserPassword(@PathVariable UUID uuid, @RequestBody @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto) {
        var userModelOptional = userService.findById(uuid);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var userModel = userModelOptional.get();
        if (!userDto.oldPassword().equals(userModel.getPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Mismatched old password");
        }
        userModel.setPassword(userDto.password());
        userModel.setUpdatedAt(LocalDateTime.now());
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{uuid}/image")
    public ResponseEntity<Object> updateUserImage(@PathVariable UUID uuid, @RequestBody @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto) {
        var userModelOptional = userService.findById(uuid);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var userModel = userModelOptional.get();
        userModel.setImageUrl(userDto.imageUrl());
        userModel.setUpdatedAt(LocalDateTime.now());
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
