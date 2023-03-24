package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Репозиторий для пользователей, который обрабатывает CRUD операции.*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
