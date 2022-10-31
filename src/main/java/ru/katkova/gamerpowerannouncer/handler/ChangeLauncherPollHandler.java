package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.*;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ChangeLauncherPollHandler implements UserActionHandler {

    @Autowired
    private UserService userService;

    @Override
    public UserAction getAction() {
        return PollQuestions.LAUNCHER;
    }

    @Override
    public List<SendMessage> handle(User user, Update update) {
        String userPreferredLauncherString = user.getPreferredLauncherList();
        Iterator<PollOption> iter = update.getPoll().getOptions().iterator();
        List<String> preferredLauncherList = new ArrayList<>();
        for (Launcher launcher: Launcher.values()) {
            if (iter.hasNext() && iter.next().getVoterCount() > 0) {
                preferredLauncherList.add(launcher.getValue());
            }
        }
        if (!preferredLauncherList.isEmpty()) {
            userPreferredLauncherString = String.join((","), preferredLauncherList);
            user.setPreferredLauncherList(userPreferredLauncherString);
            userService.saveUser(user);
        }

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Selected Launchers: " + userPreferredLauncherString)
                .build();
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(message);
        return sendMessageList;
    }
}
