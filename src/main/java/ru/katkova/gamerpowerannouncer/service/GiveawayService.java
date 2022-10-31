package ru.katkova.gamerpowerannouncer.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.repository.GiveawayRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class GiveawayService {

    @Autowired
    private GiveawayRepository giveawayRepository;

//    public void update(List<Giveaway> giveawayList) {
//        for (Giveaway giveaway: giveawayList) {
//            if (giveaway.existsInBD()) {
//                giveaway.changeStatus(ActualSend)
//
//            } else {
//
//            }
//        }
        //идем по присланному списку
        //ищем в БД - если находим, проставляем статус ActualSend
        //идем по списку пользователей и отправляем записи из нового списка, но не ActualSend (перезаписываем статус как ActualSend)
        //тут для найденных можно проверить данные типо даты - и если данные обновились проставить статус ActualSend
        //если не находим - добавляем как новый со статусом ActualNew
        //для всех остальных запсей в БД (не Actual и не New) указываем статус EXPIRED
//    }

    //для нового пользователя
    //идем по БД и отправляем все ActualSend

//    public boolean existsInDB(Giveaway giveaway) {
//        List<giveaway> promotionList= giveawayRepository.findByTitleAndStartDateAndEndDate(promotion.title, promotion.startDate, promotion.endDate);
//        return !promotionList.isEmpty();
//    }

    public void putIntoDB(Giveaway giveaway) {
        giveawayRepository.save(giveaway);
    }

    public boolean isActualGiveaway(Giveaway giveaway) {
        if (giveaway.getEnd_date().equals("N/A") ) return true;
        else {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date endDateFormatted = df.parse(giveaway.getEnd_date());
                Date gmtDate = df.parse(df.format(new Date()));
                return gmtDate.before(endDateFormatted);
            } catch (ParseException parseException) {
                return true;
            }
        }
    }

    public void deleteFromDB(Giveaway giveaway) {
        giveawayRepository.delete(giveaway);
    }

    public boolean existsInDB(Giveaway giveaway) {
        return giveawayRepository.findById(giveaway.getId()).isPresent();
    }

    public boolean isDesirableForUser(User user, Giveaway giveaway) {
        Boolean isTypeDesired = Arrays.stream(user.getPreferredTypeList().split(","))
                .anyMatch(type -> giveaway.getType().equals(type));
        List<String> preferredPlatformList = new ArrayList<>(List.of(user.getPreferredPlatformList().split(",")));
        Boolean isPlatformDesired = preferredPlatformList.retainAll(Arrays.asList(giveaway.getPlatforms()));
        List<String> preferredLauncherList = new ArrayList<>(List.of(user.getPreferredLauncherList().split(",")));
        Boolean isLauncherDesired = preferredLauncherList.retainAll(Arrays.asList(giveaway.getPlatforms()));

        return isTypeDesired && isPlatformDesired && isLauncherDesired;
    }

    public String formCaption(Giveaway giveaway) {
        return "*Title: * "+giveaway.getTitle() + "\n" +
                "*Description: * "+ giveaway.getDescription() + "\n" +
                "*End date: * " + giveaway.getEnd_date() + "\n" +
                "*Instructions: *" + giveaway.getInstructions() + "\n" +
                "*Type: *" + giveaway.getType() + "\n" +
                "*Platforms: *" + giveaway.getPlatforms() + "\n" +
                "*Gamerpower URL: *" + giveaway.getGamerpower_url() + "\n" +
                "*Open Giveaway URL: *" + giveaway.getOpen_giveaway_url();
    }

    public List<Giveaway> getAllGiveawaysFromDB() {
        return giveawayRepository.findAll();
    }
}
