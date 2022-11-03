package ru.katkova.gamerpowerannouncer.handler;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import java.util.List;

public interface UserActionHandler<S extends PartialBotApiMethod<?>> {
   String START_TEXT = "Hello! \n" +
            "This bot will send you actual giveaways (like free games, DLC etc) from different stores and for different platforms.\n" +
            "By default all giveaways types for any platform will be sent. \n" +
            "If you want to change platform (PC/XBOX/EpicGames...) please use command /platform \n" +
           "If you want to change source store (EpicGames/GOG...) please use command /launcher \n" +
           "If you want to change type of announcement (Game/Loot/Beta Access) please use command /type \n" +
           "If you want to return the default settings please use command /default \n" +
           "If you want to see current actual giveaways according to your settings please use command /show";

    List<S> handle(User user, Update update);
    UserAction getAction();
}
