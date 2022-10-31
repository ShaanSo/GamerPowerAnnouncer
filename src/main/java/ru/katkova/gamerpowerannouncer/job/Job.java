package ru.katkova.gamerpowerannouncer.job;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.service.GiveawayParserService;
import ru.katkova.gamerpowerannouncer.service.GiveawayService;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Job {

    @Autowired
    private GiveawayParserService giveawayParserService;

    @Autowired
    private UserService userService;

    @Autowired
    private GiveawayService giveawayService;

    @SneakyThrows
    public List<SendPhoto> check() {

        //делаем запрос в API и разбираем ответ
        log.debug("[ScheduledJob] Check for updates started");
//        String response = gpRequestService.handleRequest();
//        String response = "";
//        List<Giveaway> giveawayList = giveawayParserService.getGiveawayListFromElements(response);

        //получаем пользователей из БД, которым нужно разослать уведомление
//       List<User> userList =  userService.restoreUsersFromDB();

        //проходим по списку присланных предложений


        List<Giveaway> giveawayList = giveawayParserService.getGiveawayListFromElements("response");
        List<User> userList = userService.restoreUsersFromDB();
        List<SendPhoto> sendPhotoList = new ArrayList<>();

        for (Giveaway giveaway : giveawayList) {
            if (!giveawayService.existsInDB(giveaway)) {
                giveawayService.putIntoDB(giveaway);
                InputFile inputFile = new InputFile(giveaway.getImage());
                for (User user : userList) {
                    if (giveawayService.isActualGiveaway(giveaway) && giveawayService.isDesirableForUser(user, giveaway)) {
                        SendPhoto sendPhoto = SendPhoto.builder()
                                .caption(giveawayService.formCaption(giveaway))
                                .photo(inputFile)
                                .parseMode("Markdown")
                                .chatId(user.getChatId())
                                .build();
                        sendPhotoList.add(sendPhoto);
//                        bot.execute(sendPhoto);
                    }
                }
            }
        }
        return sendPhotoList;
    }
}
