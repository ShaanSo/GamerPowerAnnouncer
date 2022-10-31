package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.polls.StopPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.data.UserPoll;
import ru.katkova.gamerpowerannouncer.dictionary.PollAnswer;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.UserPollService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollAnswerHandler implements UserActionHandler {

    @Autowired
    private UserPollService userPollService;

    @Override
    public UserAction getAction() {
        return PollAnswer.POLL_ANSWER;
    }

    @Override
    public List<PartialBotApiMethod> handle(User user, Update update) {
        List<PartialBotApiMethod> methodList = new ArrayList<>();
        UserPoll userPoll = userPollService.getUserPollByChatId(user.getChatId());
        StopPoll stopPoll = StopPoll.builder()
                .chatId(user.getChatId())
                .messageId(userPoll.getMessageId())
                .build();
        methodList.add(stopPoll);
        return methodList;
    }
}
