package ru.katkova.gamerpowerannouncer.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.service.GPRequestService;
import ru.katkova.gamerpowerannouncer.service.GiveawayParserService;
import ru.katkova.gamerpowerannouncer.service.GiveawayService;
import ru.katkova.gamerpowerannouncer.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class ScheduledJob {

    @Autowired
    GPRequestService gpRequestService;

    @Autowired
    UserService userService;

    @Autowired
    GiveawayService giveawayService;

    @Autowired
    GiveawayParserService giveawayParserService;

   @Scheduled(fixedRate = 100000L)
    public void check() {

        //делаем запрос в API и разбираем ответ
        log.debug("[CheckForUpdatesJob] Check for updates started");
//        String response = gpRequestService.handleRequest();
//        List<Giveaway> giveawayList = giveawayParserService.getGiveawayListFromElements(response);
       List<Giveaway> giveawayList = giveawayParserService.getGiveawayListFromElements("response");

       //получаем пользователей из БД, которым нужно разослать уведомление
        List<User> userList =  userService.restoreUsersFromDB();

        //обновляем список
//       giveawayService.update(giveawayList);


       //проходим по списку присланных предложений
        for (Giveaway giveaway: giveawayList) {

            //проверяем, подходит ли предложение и не публиковали ли его уже
//            if (promotionService.isActualPromotion(pr) && (!promotionService.existsInDB(pr))) {
                //складываем в БД
//                giveaway.setId(UUID.randomUUID().toString());
            giveawayService.putIntoDB(giveaway);
                //формируем текст сообщения
//                for (User user:userList) {
//                    bot.formAndSendPromotion(pr, user);
//                }
            }
        }
}
