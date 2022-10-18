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
    PC("PC"),
    PS4("Playstation 4"),
    PS5("Playstation 5"),
    X_BOX_ONE("XBox One"),
    X_BOX_SERIES("XBox Series"),
    XBOX360("Xbox 360"),
    SWITCH("Nintendo Switch"),
    IOS("iOS"),
    ANDROID("Android"),
    VR("VR");



    private final String value;

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
