package ru.katkova.gamerpowerannouncer.dictionary;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public enum Platform {
    PC("pc"),
    STEAM("stream"),
    EPIC_GAME_STORE("epic-games-store"),
    UPLAY("uplay"),
    GOG("gog"),
    ICTHIO("icthio"),
    PS4("ps4"),
    X_BOX_ONE("xbox-one"),
    SWITCH("switch"),
    ANDROID("android"),
    IOS("ios"),
    VR("vr"),
    BATTLENET("battlenet");

    String value;

//    public static Set<Platform> getPlatformListByValues(String platformString)
//    {
//        Set<Platform> result = new HashSet<>();
//        String[] platforms = platformString.split(",");
//        for (String platform: platforms) {
//            for (Platform platformDict : values()) {
//                if (platformDict.getValue().equalsIgnoreCase(platform)) {
//                    result.add(platformDict);
//                    }
//                }
//        }
//        return result;
//    }
}
