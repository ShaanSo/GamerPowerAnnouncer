package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.PollQuestions;
import ru.katkova.gamerpowerannouncer.dictionary.Type;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ChangeTypePollHandler implements UserActionHandler {
    @Autowired
    UserService userService;
    @Override
    public List<SendMessage> handle(User user, Update update) {
        String userPreferredTypesString = user.getPreferredTypeList();
        Iterator<PollOption> iter = update.getPoll().getOptions().iterator();
        List<String> preferredTypeList = new ArrayList<>();
        for (Type type: Type.values()) {
            if (iter.hasNext() && iter.next().getVoterCount() > 0) {
                preferredTypeList.add(type.getApiValue());
            }
        }
        if (!preferredTypeList.isEmpty()) {
            userPreferredTypesString = String.join((","), preferredTypeList);
            user.setPreferredTypeList(userPreferredTypesString);
            userService.saveUser(user);
        }

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Selected Types: " + userPreferredTypesString)
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
