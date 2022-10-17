package ru.katkova.gamerpowerannouncer.handler;

import org.checkerframework.checker.guieffect.qual.UIPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Platform;
import ru.katkova.gamerpowerannouncer.dictionary.PollQuestions;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChangeTypePollHandler implements UserActionHandler {

    @Autowired
    UserService userService;

    @Override
    public List<SendMessage> handle(User user, Update update) {

        String finalList = user.getPreferredPlatformList();
        update.getPoll().getOptions();
        int i = 0;
        List<String> preferredPlatformList = new ArrayList<>();
        for (Platform platform: Platform.values()) {
            if (update.getPoll().getOptions().get(i).getVoterCount() > 0) {
                preferredPlatformList.add(platform.getValue());
            }
            finalList = preferredPlatformList.stream().collect(Collectors.joining(("," )));
            user.setPreferredPlatformList(finalList);
            i++;
        }
        userService.saveUser(user);

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Selected Types:")
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }

    @Override
    public UserAction getAction() {
        return PollQuestions.TYPE;
    }
}
