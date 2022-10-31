package ru.katkova.gamerpowerannouncer.service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.katkova.gamerpowerannouncer.data.Giveaway;
import ru.katkova.gamerpowerannouncer.data.JsonElement;
import ru.katkova.gamerpowerannouncer.dictionary.Type;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class GiveawayParserService {

    ObjectMapper objectMapper = new ObjectMapper();
//    List<Giveaway> giveawayList = new ArrayList<>();

    public List<Giveaway> getGiveawayListFromElements(String message) {
        log.info("[GiveawayParserService] Trying parse json");
        try {
            File file = new File("src/main/resources/test_cut.json");
            List<JsonElement> elementsList = Arrays.asList(objectMapper.readValue(file, JsonElement[].class));
//            giveawayList = JsonElement.getGiveawayListFromElements(elementsList);
            List<Giveaway> giveawayList = new ArrayList<>();
            elementsList.stream().forEach(elements -> {
                Giveaway giveaway = Giveaway.builder()
                        .id(elements.getId())
                        .title(elements.getTitle())
                        .worth(elements.getWorth())
                        .thumbnail(elements.getThumbnail())
                        .image(elements.getImage())
                        .description(elements.getDescription())
                        .instructions(elements.getInstructions())
                        .open_giveaway_url(elements.getOpen_giveaway_url())
                        .published_date(elements.getPublished_date())
                        .end_date(elements.getEnd_date())
                        .users(elements.getUsers())
                        .status(elements.getStatus())
                        .gamerpower_url(elements.getGamerpower_url())
                        .open_giveaway(elements.getOpen_giveaway())
                        .type(elements.getType())
                        .platforms(elements.getPlatforms())
//                    .platformList(Platform.getPlatformListByValues(elements.getPlatforms()))
                        .build();
                giveawayList.add(giveaway);
            });
            return giveawayList;
        } catch (
                StreamReadException estr) {
            System.out.println("StreamReadException");
            return null;
        } catch (
                IOException e) {
            System.out.println("IOEXCEPTION");
            e.printStackTrace();
            return null;
        }
    }

//    public List<Giveaway> getGiveawayListFromElements(List<JsonElement> elementsList) {
//        List<Giveaway> giveawayList = new ArrayList<>();
//        elementsList.stream().forEach(elements -> {
//            Giveaway giveaway = Giveaway.builder()
//                    .id(elements.getId())
//                    .title(elements.getTitle())
//                    .worth(elements.getWorth())
//                    .thumbnail(elements.getThumbnail())
//                    .image(elements.getImage())
//                    .description(elements.getDescription())
//                    .instructions(elements.getInstructions())
//                    .open_giveaway_url(elements.getOpen_giveaway_url())
//                    .published_date(elements.getPublished_date())
//                    .end_date(elements.getEnd_date())
//                    .users(elements.getUsers())
//                    .status(elements.getStatus())
//                    .gamerpower_url(elements.getGamerpower_url())
//                    .open_giveaway(elements.getOpen_giveaway())
//                    .type(Type.findByValue(elements.getType()))
//                    .platforms(elements.getPlatforms())
////                    .platformList(Platform.getPlatformListByValues(elements.getPlatforms()))
//                    .build();
//            giveawayList.add(giveaway);
//        });
//        return giveawayList;
//    }
}
