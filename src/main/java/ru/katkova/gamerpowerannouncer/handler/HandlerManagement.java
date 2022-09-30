package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;


@Component
public class HandlerManagement {
    private Map<Command, CommandHandler> handlers;
    private final CommandHandler defaultHandler;
    private final UserService userService;


    public HandlerManagement (Collection<CommandHandler> handlers, CommandHandler defaultHandler, UserService userService){
        this.handlers = handlers.stream().collect(toMap(CommandHandler::getCommand, Function.identity()));
        this.defaultHandler = defaultHandler;
        this.userService = userService;
    }

    public SendMessage manage(User user, String message) {
        return handlers.getOrDefault(getCommand(message), defaultHandler).handle(user);
    }

    public static Command getCommand(String message) {
        String textCommand = message.split(" ")[0];
        return EnumSet.allOf(Command.class)
                .stream()
                .filter(command -> command.getValue().equalsIgnoreCase(textCommand))
                .findFirst().orElse(Command.START);
    }
}
