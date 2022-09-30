package ru.katkova.gamerpowerannouncer.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import ru.katkova.gamerpowerannouncer.dictionary.Type;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonElement {
    private String id;
    private String title;
    private String worth;
    private String thumbnail;
    private String image;
    private String description;
    private String instructions;
    private String open_giveaway_url;
    private String published_date;
    private String end_date;
    private String users;
    private String status;
    private String gamerpower_url;
    private String open_giveaway;
    private String type;
    private String platforms;


}