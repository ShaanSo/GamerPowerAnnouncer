package ru.katkova.gamerpowerannouncer.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;

public interface CommandHandler {
    SendMessage handle(User user);

    Command getCommand();
}
