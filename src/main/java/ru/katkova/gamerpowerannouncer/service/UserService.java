package ru.katkova.gamerpowerannouncer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.data.UserPoll;
import ru.katkova.gamerpowerannouncer.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createNewUser(Long chatId ) {
        log.info("User with chat id " + chatId + " was created in DB");
        User user = new User(chatId);
        userRepository.save(user);
    }

    public void saveUser(User user) {
        log.info("user with chat id " + user.getChatId() + " was saved in DB");
        userRepository.save(user);
    }

    public User getUser(Long chatId) {
        return userRepository.findFirstByChatId(chatId);
    }

    public List<User> restoreUsersFromDB() {
        return userRepository.findAll();
    }

    public boolean existsInDB(Long chatId) {
        return !(userRepository.findFirstByChatId(chatId) == null);
    }

    @Transactional
    public void deleteUser(Long chatId) {
        log.info("User with chat id " + chatId + " was deleted from DB");
        userRepository.deleteAllByChatId(chatId);
    }
}
