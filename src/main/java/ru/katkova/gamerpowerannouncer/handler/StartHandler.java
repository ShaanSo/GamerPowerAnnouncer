package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class StartHandler implements UserActionHandler {

    @Override
    public UserAction getAction() {
        return Command.START;
    }

    @Override
    public List<SendMessage> handle(User user, Update update) {
        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text(START_TEXT)
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }
}
