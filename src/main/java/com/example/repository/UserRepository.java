package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Репозиторий для пользователей, который обрабатывает CRUD операции для общения
 * с базой данных.
 *
 * Аннотация @Repository используется в Spring для пометки класса как репозитория,
 * который выполняет операции доступа к данным, такие как сохранение, извлечение,
 * удаление и обновление. Класс, помеченный этой аннотацией, автоматически
 * сканируется Spring и создается экземпляр, который может быть внедрен в другие
 * компоненты приложения, такие как сервисы и контроллеры.
 * */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
