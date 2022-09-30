package ru.katkova.gamerpowerannouncer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.repository.GiveawayRepository;

import java.util.List;

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
}
