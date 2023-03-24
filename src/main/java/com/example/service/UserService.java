package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Сервис для пользователей, который обрабатывает CRUD операции.*/
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /*Получение пользователя по id*/
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*Создание пользователя*/
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /*Обновление пользователя по id*/
    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    /*Удаление пользователя по id*/
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}