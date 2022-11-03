package ru.katkova.gamerpowerannouncer.handler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.polls.StopPoll;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.data.UserPoll;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.dictionary.PollOptions;
import ru.katkova.gamerpowerannouncer.dictionary.PollQuestions;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.UserPollService;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class ChangePlatformCommandHandler implements UserActionHandler{

    @Autowired
    UserPollService userPollService;

    @Override
    public List<PartialBotApiMethod> handle(User user, Update update) {

        List<PartialBotApiMethod> methodList = new ArrayList<>();

        UserPoll userPoll = userPollService.getUserPollByChatId(user.getChatId());
        //если уже есть открытый опросник - закрываем
        if (userPoll !=null ) {
            StopPoll stopPoll = StopPoll.builder()
                    .chatId(user.getChatId())
                    .messageId(userPoll.getMessageId())
                    .build();
            methodList.add(stopPoll);
        }

        String pollQuestion = "";
        for (PollQuestions question: PollQuestions.values()) {
            if (question.getHandler().equals("PLATFORM_HANDLER")) {
                pollQuestion = question.getValue();
            }
        }

        List<String> pollOptionList =  new ArrayList<>();
        for (PollOptions option: PollOptions.values()) {
            if (option.getHandler().equals("PLATFORM_HANDLER")) {
                pollOptionList.add(option.getValue());
            }
        }

        SendPoll sendPoll = SendPoll.builder()
                .chatId(user.getChatId())
                .allowMultipleAnswers(true)
                .question(pollQuestion)
                .options(pollOptionList)
                .isAnonymous(false)
                .build();

        methodList.add(sendPoll);
        return methodList;
    }

    @Override
    public UserAction getAction() {
        return Command.CHANGE_PLATFORM;
    }
}