package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PollOptions implements UserAction {

    GAME("Free Games", "TYPE_HANDLER"),
    DLC("Free Loot", "TYPE_HANDLER"),
    BETA("Beta Access", "TYPE_HANDLER"),

//    ALL("All Platforms", "PLATFORM_HANDLER"),
    PC("PC", "PLATFORM_HANDLER"),
    PS4("Playstation 4", "PLATFORM_HANDLER"),
    PS5("Playstation 5", "PLATFORM_HANDLER"),
    X_BOX_ONE("XBox One", "PLATFORM_HANDLER"),
    X_BOX_SERIES("XBox Series", "PLATFORM_HANDLER"),
    XBOX360("Xbox 360", "PLATFORM_HANDLER"),
    SWITCH("Nintendo Switch", "PLATFORM_HANDLER"),
    IOS("iOS", "PLATFORM_HANDLER"),
    ANDROID("Android", "PLATFORM_HANDLER"),
    VR("VR", "PLATFORM_HANDLER"),


    STEAM("Steam", "LAUNCHER_HANDLER"),
    EGS("Epic Games Store", "LAUNCHER_HANDLER"),
    UBISOFT("Ubisoft", "LAUNCHER_HANDLER"),
    GOG("GOG", "LAUNCHER_HANDLER"),
    ITCHIO("Itch.io", "LAUNCHER_HANDLER"),
    BATTLENET("Battle.net", "LAUNCHER_HANDLER"),
    ORIGIN("Origin", "LAUNCHER_HANDLER"),
    DRMFREE("DRM-Free", "LAUNCHER_HANDLER");

    private final String Value;
    private final String handler;
//    private final Integer optionId;
}
