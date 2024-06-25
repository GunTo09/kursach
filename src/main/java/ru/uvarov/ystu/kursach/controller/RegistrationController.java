package ru.uvarov.ystu.kursach.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.uvarov.ystu.kursach.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> registration(
            @RequestParam("username") String username,
            @RequestParam("password")String password
    ){

        userService.registration(username, password);
        return ResponseEntity.ok().build();

    }

}
