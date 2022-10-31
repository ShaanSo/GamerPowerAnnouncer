package ru.katkova.gamerpowerannouncer.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.katkova.gamerpowerannouncer.bot.Bot;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.data.JsonElement;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.service.GPRequestService;
import ru.katkova.gamerpowerannouncer.service.GiveawayParserService;
import ru.katkova.gamerpowerannouncer.service.GiveawayService;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
@Slf4j
public class ScheduledJob {

    @Autowired
    private GPRequestService gpRequestService;
    @Autowired
    private UserService userService;
    @Autowired
    private GiveawayService giveawayService;
    @Autowired
    private GiveawayParserService giveawayParserService;
    @Autowired
    private Bot bot;


//    public static void execute() {
//        ScheduledJob scheduledJob = new ScheduledJob();
//        scheduledJob.check();
//    }

//    @Scheduled(fixedRate = 10000000000L)
    @Scheduled(cron = "${bot.scheduleCron}", zone = "${bot.timeZone}")
    @SneakyThrows
    public void check() {

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
                        bot.execute(sendPhoto);
                    }
                }
            }
        }
    }
}
