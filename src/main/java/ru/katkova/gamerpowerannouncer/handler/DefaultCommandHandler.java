package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.*;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCommandHandler implements UserActionHandler {

    @Autowired
    UserService userService;

    @Override
    public UserAction getAction() {
        return Command.DEFAULT;
    }

    @Override
    public List<SendMessage> handle(User user, Update update) {

        user.setDefaultProperties();
        userService.saveUser(user);

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Default configuration is set")
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }
}

