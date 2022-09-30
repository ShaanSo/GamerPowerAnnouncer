package ru.katkova.gamerpowerannouncer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.katkova.gamerpowerannouncer.data.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findFirstByChatId(Long chatId);

    void deleteAllByChatId(Long chatId);
}
