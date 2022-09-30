package ru.katkova.gamerpowerannouncer.handler;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.katkova.gamerpowerannouncer.bot.Bot;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;

@Primary
@Service
public class StartHandler implements CommandHandler{

//    @Autowired
//    public Bot bot;

    @Override
    public Command getCommand() {
        return Command.START;
    }

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
        return message;}
}
