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

    public static final String WELCOME_TEXT = "Hello! \n" +
            "This bot will send you actual giveaways (like free games, DLC etc) from different stores and for different platforms.\n" +
            "By default all giveaways types for any platform will be sent. \n" +
            "If you want to change platform and source store (PC/XBOX/EpicGames...) please use command /platform \n" +
            "If you want to change type of announcement (Game/Loot/Beta Access) please use command /type \n" +
            "If you want to see current actual giveaways please use command /show";
    @Override
    public UserAction getAction() {
        return Command.START;
    }

    @Override
    public List<SendMessage> handle(User user, Update update) {
        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text(WELCOME_TEXT)
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }
}
