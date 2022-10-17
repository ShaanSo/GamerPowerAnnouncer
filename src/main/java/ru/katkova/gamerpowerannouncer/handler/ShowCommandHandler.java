package ru.katkova.gamerpowerannouncer.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;

import java.util.ArrayList;
import java.util.List;

public class ShowCommandHandler implements UserActionHandler {

    public UserAction getAction() {
        return Command.SHOW;
    }

    @Override
    public List<SendMessage> handle(User user, Update update) {

        //формируем запрос в GP
        //раскладываем на сообщения
        //формируем сообщения

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }
}
