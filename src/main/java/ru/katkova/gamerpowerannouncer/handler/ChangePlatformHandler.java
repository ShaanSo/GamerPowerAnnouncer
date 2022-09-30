package ru.katkova.gamerpowerannouncer.handler;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.katkova.gamerpowerannouncer.bot.Bot;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;

@Service
public class ChangePlatformHandler implements CommandHandler{

    @SneakyThrows
    @Override
    public SendMessage handle(User user) {
        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Hello start \n" +
                        "this bot will be send you actual free PC games from all stores \n" +
                        "if you want to change Platform (PC/XBOX/both) please use command /changePlatform \n" +
                        "if you want to change Type of announcement (Game/Loot/smth) please use command /type")
                .build();
        return message;
    }

    @Override
    public Command getCommand() {
        return Command.CHANGE_PLATFORM_MODE;
    }
}
