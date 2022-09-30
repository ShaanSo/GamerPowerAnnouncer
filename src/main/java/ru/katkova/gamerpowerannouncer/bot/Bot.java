package ru.katkova.gamerpowerannouncer.bot;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.handler.HandlerManagement;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    @Getter
    @Value("${bot.name}")
    private final String BotUsername;
    @Getter
    @Value("${bot.token}")
    private final String BotToken;

    @Autowired
    UserService userService;

    @Autowired
    HandlerManagement handlerManagement;

    private static final String GREETINGS_TEXT = "Привет! Я GamerPower Announcer Bot. \n"+
            "Я буду присылать тебе анонсы бесплатных игр и дополнений с сайта GamerPower. \n" +
            "https://www.gamerpower.com/ \n" +
            "Для того чтобы выбрать тип анонсов (игры/допольнительные материалы/) используй команду /type \n" +
            "Для того чтобы выбрать интересующую платформу используй команду /platform \n" +
            "Приятного пользования!";

    public Bot(
            TelegramBotsApi telegramBotsApi,
            @Value("${bot.name}") String botUsername,
            @Value("${bot.token}") String botToken) throws TelegramApiException {
        this.BotUsername = botUsername;
        this.BotToken = botToken;
        telegramBotsApi.registerBot(this);
    }

@SneakyThrows
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();

        if (!userService.existsInDB(chatId)) {
            userService.createNewUser(chatId);
            SendMessage greetings = SendMessage.builder()
                    .chatId(chatId)
                    .text(GREETINGS_TEXT)
                    .build();
            execute(greetings);
        }

        if (update.hasMessage()) {
            if (update.getMessage().isCommand()) {
                SendMessage sendMessage = handlerManagement.manage(userService.getUser(chatId), update.getMessage().getText());
                execute(sendMessage);
            }
        }
//
//        if (!userService.existsInDB(chatId)) {
//            userService.createNewUser(user);
//
//            List<Promotion> promotionList = promotionService.getActualPromotions();
//            for (Promotion p: promotionList) {
//                formAndSendPromotion(p, user);
//            }
//        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        if (updates.get(0).hasMyChatMember()) {
            ChatMemberUpdated chatMemberUpdated = updates.get(0).getMyChatMember();
            if (chatMemberUpdated.getNewChatMember().getStatus().equals("kicked")) {
                userService.deleteUser(chatMemberUpdated.getChat().getId());
            }
        } else updates.forEach(this::onUpdateReceived);
    }

//    public void formAndSendPromotion(Promotion pr, User user) {
//        String preparedText = "*Название:* "+pr.title + "\n" + "*Описание:* "+ pr.description + "\n" + "*Начало раздачи:* " + pr.startDate + "\n" + "*Окончание раздачи:* " + pr.endDate;
//        if (pr.getImageUrl() != null) {
//            InputFile inputFile = new InputFile(pr.getImageUrl());
//            SendPhoto message = SendPhoto.builder()
//                    .photo(inputFile)
//                    .chatId(user.getChatId())
//                    .parseMode("Markdown")
//                    .caption(preparedText).build();
////            message.setPhoto(inputFile);
////            message.setParseMode("Markdown");
////            message.setCaption(preparedText);
////            message.setChatId(user.getChatId());
//            try {
//                execute(message);
//            }  catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else {
//            SendMessage message = SendMessage.builder()
//                    .chatId(user.getChatId())
//                    .parseMode("Markdown")
//                    .text(preparedText).build();
//            try {
//                execute(message);
//            }  catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
////            message.setChatId(user.getChatId());
////            message.setParseMode("Markdown");
////            message.setText(preparedText);
//        }
//    }

//    @Override
//    public String getBotUsername() {
//        return properties.getUsername();
//    }
//
//    @Override
//    public String getBotToken() {
//        return properties.getToken();
//    }

//    @Override
//    public String getBotUsername() { return
//        "GPAnnouncerBot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "5566286187:AAHaM_H3bGJsMkMOTb5qCWgoWTtsZJVlCJs";
//    }
}
