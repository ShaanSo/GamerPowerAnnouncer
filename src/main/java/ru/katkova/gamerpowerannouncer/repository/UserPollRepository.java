package ru.katkova.gamerpowerannouncer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.katkova.gamerpowerannouncer.data.UserPoll;

@Repository
public interface UserPollRepository extends JpaRepository<UserPoll, String> {

    UserPoll findFirstByChatId(Long chatId);
    UserPoll findFirstByPollId(String pollId);
}
