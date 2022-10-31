package ru.katkova.gamerpowerannouncer.bot;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.polls.StopPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.katkova.gamerpowerannouncer.data.UserPoll;
import ru.katkova.gamerpowerannouncer.handler.HandlerManagement;
import ru.katkova.gamerpowerannouncer.job.Job;
import ru.katkova.gamerpowerannouncer.service.UserPollService;
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
    private UserService userService;
    @Autowired
    private UserPollService userPollService;
    @Autowired
    private HandlerManagement handlerManagement;
    @Autowired
    private Job job;

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
        Long chatId;
        if (update.hasPollAnswer()) {
            //для приватного чата chatId = user.Id, подумать над неприватным чатом
            chatId = update.getPollAnswer().getUser().getId();
        } else if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
        } else if (update.hasPoll() && update.getPoll().getTotalVoterCount() > 0) {
            chatId = userPollService.getChatIdByPollId(update.getPoll().getId());
        } else {
            return;
        }

        if (!userService.existsInDB(chatId)) {
            userService.createNewUser(chatId);
            SendMessage greetings = SendMessage.builder()
                    .chatId(chatId)
                    .text(GREETINGS_TEXT)
                    .build();
            execute(greetings);
        }

            List<? extends PartialBotApiMethod> sendMethodList = handlerManagement.manage(userService.getUser(chatId), update);
            for (PartialBotApiMethod method: sendMethodList) {
                //если пытаемся создать опросник, то сохранем id актуального опросника в пользователя
                if (method.getClass().isAssignableFrom(SendPoll.class)) {
                    SendPoll messageMethod = (SendPoll) method;
                    Message message = execute(messageMethod);
                    UserPoll userPoll = new UserPoll.Builder()
                            .chatId(chatId)
                            .pollId(message.getPoll().getId())
                            .messageId(message.getMessageId())
                            .pollQuestion(message.getPoll().getQuestion())
                            .build();
                    userPollService.saveUserPoll(userPoll);
                } else if (method.getClass().isAssignableFrom(SendPhoto.class)) {
                        SendPhoto messageMethod = (SendPhoto) method;
                        execute(messageMethod);
                } else if (method.getClass().isAssignableFrom(SendMessage.class)) {
                    SendMessage messageMethod = (SendMessage) method;
                    execute(messageMethod);
                } else if (method.getClass().isAssignableFrom(StopPoll.class)) {
                    StopPoll messageMethod = (StopPoll) method;
                    try {
                        execute(messageMethod);
                    } catch (TelegramApiRequestException e) {
                        //do nothing
                    }
                }
            }

    if (update.hasMessage() && update.getMessage().isCommand()
            && update.getMessage().getText().equals("/show")
            && !job.check().isEmpty()) {
        for (SendPhoto sendPhoto: job.check()) {
            execute(sendPhoto);
        }
    }


//        else if (update.hasPollAnswer()) {
//            BotApiMethod sendPoll = handlerManagement.manage(userService.getUser(chatId), update);
//            execute(sendPoll);
//            StopPoll stopPoll = StopPoll.builder().chatId(chatId).messageId(userService.getUser(chatId).getActivePollMessageId()).build();
//            execute(stopPoll);
//        } else if (update.hasPoll()) {
//            //сохраняем результаты опросника в пользователя
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
