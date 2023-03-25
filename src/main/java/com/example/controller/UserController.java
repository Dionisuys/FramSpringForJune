package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/* Контроллер для пользователей, который обрабатывает CRUD операции
 * HTTP-запросов.
 *
 * Аннотация @RestController указывает, что класс является контроллером,
 * который обрабатывает HTTP-запросы RESTful. Эта аннотация объединяет
 * аннотации @Controller и @ResponseBody.
 *
 * Аннотация @RequestMapping предоставляет сопоставление между HTTP-запросами и
 * методами контроллера. Она используется для определения базового URL-адреса
 * контроллера и для определения дополнительных путей в запросе, которые будут
 * обслуживаться этим контроллером.
 * */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* Аннотации @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
     * для обработки HTTP-запросов.
     *
     * Аннотация @PathVariable используется в методах контроллера для получения
     * id пользователя из URL.
     *
     * Аннотация @RequestBody используется в методах контроллера для получения
     * данных пользователя из тела запроса HTTP.
     *
     * Аннотация @Valid используется в методах для проверки переданных
     * в контроллер данных.
     * */

    /*Получение пользователя по id*/
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*Создание пользователя*/
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /*Обновление пользователя по id*/
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /*Удаление пользователя по id*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
