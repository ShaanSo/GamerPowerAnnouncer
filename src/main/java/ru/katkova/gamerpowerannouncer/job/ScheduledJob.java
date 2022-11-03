package ru.katkova.gamerpowerannouncer.job;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.katkova.gamerpowerannouncer.bot.Bot;

import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class ScheduledJob {

    @Autowired
    private Bot bot;

    @Autowired
    private Job job;

    @Scheduled(cron = "${bot.scheduleCron}", zone = "${bot.timeZone}")
    @SneakyThrows
    public void check() {
        log.info("[ScheduledJob] Check for updates started");
        List<SendPhoto> sendPhotoList = job.check();
        for (SendPhoto sendPhoto : sendPhotoList) {
            log.info("Execute Photo" + sendPhoto.getCaption());
            try {
                bot.execute(sendPhoto);
            } catch (Exception e)
            {
                //do nothing
            }
        }
    }
}
