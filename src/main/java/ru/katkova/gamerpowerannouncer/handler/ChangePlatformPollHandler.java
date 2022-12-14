package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Platform;
import ru.katkova.gamerpowerannouncer.dictionary.PollQuestions;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ChangePlatformPollHandler implements UserActionHandler{

    @Autowired
    private UserService userService;

    @Override
    public List<SendMessage> handle(User user, Update update) {
        String userPreferredPlatformsString = user.getPreferredPlatformList();
        Iterator<PollOption> iter = update.getPoll().getOptions().iterator();
        List<String> preferredPlatformList = new ArrayList<>();
        for (Platform platform: Platform.values()) {
            if (iter.hasNext() && iter.next().getVoterCount() > 0) {
                preferredPlatformList.add(platform.getValue());
            }
        }
        if (!preferredPlatformList.isEmpty()) {
            userPreferredPlatformsString = String.join((","), preferredPlatformList);
            user.setPreferredPlatformList(userPreferredPlatformsString);
            userService.saveUser(user);
        }

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Selected Platforms: " + userPreferredPlatformsString)
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }

    @Override
    public UserAction getAction() {
        return PollQuestions.PLATFORM;
    }
}
