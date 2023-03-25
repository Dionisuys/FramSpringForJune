package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/* Сервис для пользователей, который обрабатывает CRUD операции.
 *
 * Аннотация @Service указывает, что класс является сервисом в приложении и
 * содержит бизнес-логику приложения. Это позволяет Spring выполнить
 * автоматическую настройку зависимостей для класса и управлять его
 * жизненным циклом.
 *
 * Аннотация @Validated указывает, что класс должен быть валидирован с
 * использованием валидации Bean Validation. В отличие от аннотации @Valid,
 * которая применяется к аргументам методов для проверки их валидности,
 * @Validated применяется к классу и позволяет выполнять дополнительные
 * проверки при использовании методов внутри класса.
 * */
@Service
@Validated
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* Получение пользователя по id */
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with id: " + id));
    }

    /* Создание пользователя
     *
     * Аннотация @Transactional указывает, что метод должен быть выполнен в
     * транзакции базы данных. Если метод выполняется успешно, транзакция будет
     * подтверждена, а если возникнет ошибка, транзакция будет отменена и
     * все изменения в базе данных будут откатаны.
     *
     * Аннотация @NotNull используется для указания, что аргумент метода не
     * должен быть равен null, чтобы предотвратить ошибки времени выполнения в
     * коде.
     *
     * Аннотация @Valid используется для проверки входных данных в теле запроса
     * HTTP на соответствие, что объект является допустимым объектом типа User.
     *
     * Аннотация @PathVariable используется для получения значений переменных из
     * URL-адреса, чтобы получить id пользователя из URL-адреса, который затем
     * используется для извлечения данных пользователя из базы данных.
     *
     * Аннотация @RequestBody используется для получения данных из тела запроса
     * HTTP, чтобы получить данные пользователя, которые были отправлены в теле
     * запроса, и затем обновить пользователя в базе данных на основе этих данных.
     * */
    @Transactional
    public User createUser(@NotNull @Valid User user) {
        return userRepository.save(user);
    }

    /* Обновление пользователя по id */
    @Transactional
    public User updateUser(@PathVariable Long id,
                           @NotNull @Valid @RequestBody User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with id: " + id));

        if (!existingUser.getId().equals(user.getId())) {
            throw new IllegalArgumentException(
                    "User ID in path must match user ID in request body.");
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    /* Удаление пользователя по id */
    @Transactional
    public void deleteUser(@PathVariable Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with id: " + id));

        userRepository.delete(existingUser);
    }
}
