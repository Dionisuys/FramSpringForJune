package com.example.model;

import jakarta.persistence.*;

/* Класс-сущность для пользователя, который имеет поля id, имя и
 * адрес электронной почты.
 *
 * Аннотация @Entity используется в JPA для пометки класса как сущности базы
 * данных. Класс соответствует таблице в базе данных.
 *
 * Аннотация @Table(name = "users") указывает имя таблицы в базе данных,
 * соответствующей данной сущности.
 * */
@Entity
@Table(name = "users")
public class User {

    /* Аннотация @Id помечает поле в классе как первичный ключ в базе данных.
     *
     * Аннотация @GeneratedValue(strategy = GenerationType.IDENTITY) указывает,
     * как будет генерироваться значение первичного ключа при вставке новых
     * записей в таблицу. В данном случае генерация значения будет
     * осуществляться с помощью автоинкремента в БД.
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public User() {
    }

    /* Конструктор с двумя параметрами предназначен для создания объекта User
     * без указания его идентификатора, который будет сгенерирован автоматически
     * при сохранении сущности в базу данных.
     * */

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
