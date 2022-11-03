package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.dictionary.UserAction;
import ru.katkova.gamerpowerannouncer.service.GiveawayService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowCommandHandler implements UserActionHandler {

    @Autowired
    GiveawayService giveawayService;

    public UserAction getAction() {
        return Command.SHOW;
    }

    @Override
    public List<SendPhoto> handle(User user, Update update) {
        List<SendPhoto> sendPhotoList = new ArrayList<>();

        List<Giveaway> giveawayList = giveawayService.getAllGiveawaysFromDB();

        for (Giveaway giveaway: giveawayList) {
            if (giveawayService.isActualGiveaway(giveaway)) {
                if (giveawayService.isDesirableForUser(user, giveaway)) {
                    InputFile inputFile = new InputFile(giveaway.getImage());
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .chatId(user.getChatId())
                            .parseMode("Markdown")
                            .caption(giveawayService.formCaption(giveaway))
                            .photo(inputFile)
                            .build();
                    sendPhotoList.add(sendPhoto);
                }
            } else {
                giveawayService.deleteFromDB(giveaway);
            }
        }

        return sendPhotoList;
    }
}
