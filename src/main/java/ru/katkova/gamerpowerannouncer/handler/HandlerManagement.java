package ru.katkova.gamerpowerannouncer.handler;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.CallbackQuery;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.dictionary.PollOptions;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.UserService;

import javax.crypto.spec.ChaCha20ParameterSpec;
import java.util.*;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;

@Component
public class HandlerManagement {
    private Map<UserAction, UserActionHandler<?>> handlers;
    private final UserActionHandler<?> defaultHandler;
    private final UserService userService;

    public HandlerManagement (Collection<UserActionHandler<?>> handlers, UserActionHandler<?> defaultHandler, UserService userService){
        this.handlers = handlers.stream().collect(toMap(UserActionHandler::getAction, Function.identity()));
        this.defaultHandler = defaultHandler;
        this.userService = userService;
    }

//    public List<? extends BotApiMethod> manage(User user, String message) {
//        return handlers.getOrDefault(getUserAction(message), defaultHandler).handle(user, message);
//    }

//    public BotApiMethod manage(User user, PollAnswer pollAnswer) {
//        //    update.getPollAnswer().getOptionIds().get(0).intValue() - массив интов с номерами выбранных опций
//        List<SendMessage> sendMessageList = new ArrayList<>();
//        sendMessageList.add(SendMessage.builder().chatId(user.getChatId()).text("test").build());
//        return SendMessage.builder().chatId(user.getChatId()).text("test").build();
////        return new SendMessage();
//                //handlers.getOrDefault(getUserAction(message), defaultHandler).handle(user, poll);
//    }

    public List<? extends BotApiMethod> manage(User user, Update update) {
        if (!update.hasPollAnswer() || (update.hasPoll() && update.getPoll().getTotalVoterCount() != 0))
        return handlers.getOrDefault(getUserAction(update), defaultHandler).handle(user, update);
        else return new ArrayList<>();
    }

//    @SneakyThrows
//    public static UserAction getUserAction(String message) {
//        String textAction = message.split(" ")[0];
////        UserAction userAction = EnumSet.allOf(PollOptions.class)
////                .stream()
////                .filter(pollOption -> pollOption.getValue().equalsIgnoreCase(textAction))
////                .findFirst().orElse(null);
////        if (userAction != null) {
////            CallbackQuery callbackQuery = (CallbackQuery) userAction;
////            if (callbackQuery.getHandler().equals("TYPE_HANDLER")) {
////                return PollOptions.;
////            }
////            else if (callbackQuery.getHandler().equals("PLATFORM_HANDLER")) {
////                return CallbackQuery.PLATFORM_HANDLER;
////            }
////        }
//
//            UserAction userAction = EnumSet.allOf(Command.class)
//                    .stream()
//                    .filter(command -> command.getValue().equalsIgnoreCase(textAction))
//                    .findFirst().orElse(Command.START);
//        return userAction;
//    }

    @SneakyThrows
    public static UserAction getUserAction(Update update) {
        UserAction userAction = Command.START;
        if (update.hasMessage() && update.getMessage().isCommand()) {
            userAction = EnumSet.allOf(Command.class)
                    .stream()
                    .filter(command -> command.getValue().equalsIgnoreCase(update.getMessage().getText()))
                    .findFirst().orElse(Command.START);
        } else if (update.hasPoll()) {
            userAction = EnumSet.allOf(PollOptions.class)
                    .stream()
                    .filter(command -> command.getValue().equalsIgnoreCase(update.getPoll().getQuestion()))
                    .findFirst().orElse(null);
        } else return Command.START;
        return userAction;
    }
}
