package ru.katkova.gamerpowerannouncer.handler;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;

import java.util.List;

public interface UserActionHandler<S extends BotApiMethod<?>> {

    List<S> handle(User user, Update update);
    UserAction getAction();
}
