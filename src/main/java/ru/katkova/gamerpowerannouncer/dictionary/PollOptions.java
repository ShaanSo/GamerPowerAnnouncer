package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PollOptions implements UserAction {

    GAME("Free Games", "TYPE_HANDLER", 0),
    DLC("Free Loot", "TYPE_HANDLER", 1),
    BETA("Beta Access", "TYPE_HANDLER", 2),


    ALL("All Platforms", "PLATFORM_HANDLER", 0),
    PC("PC", "PLATFORM_HANDLER", 1),
    PS4("Playstation 4", "PLATFORM_HANDLER", 2),
    PS5("Playstation 5", "PLATFORM_HANDLER", 3),
    X_BOX_ONE("XBox One", "PLATFORM_HANDLER", 4),
    X_BOX_SERIES("XBox Series", "PLATFORM_HANDLER", 5),
    XBOX360("Xbox 360", "PLATFORM_HANDLER", 6),
    SWITCH("Nintendo Switch", "PLATFORM_HANDLER", 7),
    IOS("iOS", "PLATFORM_HANDLER", 8),
    ANDROID("Android", "PLATFORM_HANDLER", 9),
    VR("VR", "PLATFORM_HANDLER", 10),
    STEAM("Steam", "PLATFORM_HANDLER", 11),
    EGS("Epic Games Store", "PLATFORM_HANDLER", 12),
    UBISOFT("Ubisoft", "PLATFORM_HANDLER", 13),
    GOG("GOG", "PLATFORM_HANDLER", 14),
    ITCHIO("Itch.io", "PLATFORM_HANDLER", 15),
    BATTLENET("Battle.net", "PLATFORM_HANDLER", 16),
    ORIGIN("Origin", "PLATFORM_HANDLER", 17),
    DRMFREE("DRM-Free", "PLATFORM_HANDLER", 18);

    public final String Value;
    public final String handler;
    public final Integer optionId;
}
