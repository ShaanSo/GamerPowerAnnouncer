package ru.katkova.gamerpowerannouncer.handler;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.*;
import ru.katkova.gamerpowerannouncer.service.UserService;

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

    public List<? extends PartialBotApiMethod> manage(User user, Update update) {
        if  (!(update.hasPoll() && update.getPoll().getTotalVoterCount() == 0))
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
        UserAction userAction;
        if (update.hasMessage() && update.getMessage().isCommand()) {
            userAction = EnumSet.allOf(Command.class)
                    .stream()
                    .filter(command -> command.getValue().equalsIgnoreCase(update.getMessage().getText()))
                    .findFirst().orElse(Command.START);
        } else if (update.hasPoll()) {
            userAction = EnumSet.allOf(PollQuestions.class)
                    .stream()
                    .filter(question -> question.getValue().equalsIgnoreCase(update.getPoll().getQuestion()))
                    .findFirst().orElse(null);
        } else if (update.hasPollAnswer()) {
            userAction = PollAnswer.POLL_ANSWER;
        } else return Command.START;
        return userAction;
    }
}
