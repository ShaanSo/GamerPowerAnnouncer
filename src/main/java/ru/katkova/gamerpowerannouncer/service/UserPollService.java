package ru.katkova.gamerpowerannouncer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.data.UserPoll;
import ru.katkova.gamerpowerannouncer.repository.UserPollRepository;

@Slf4j
@Service
public class UserPollService {

    @Autowired
    UserPollRepository userPollRepository;

    public void saveUserPoll(UserPoll userPoll) {
        log.info("userPoll with chat id " + userPoll.getChatId() + " was saved in DB");
        userPollRepository.save(userPoll);
    }

    public UserPoll getUserPollByChatId(Long chatId) {
        return userPollRepository.findFirstByChatId(chatId);
    }
}
