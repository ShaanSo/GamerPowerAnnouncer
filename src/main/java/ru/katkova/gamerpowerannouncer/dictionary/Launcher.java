package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Launcher {

    STEAM("Steam"),
    EGS("Epic Games Store"),
    UBISOFT("Ubisoft"),
    GOG("GOG"),
    ITCHIO("Itch.io"),
    BATTLENET("Battle.net"),
    ORIGIN("Origin"),
    DRMFREE("DRM-Free");

    private final String value;
}
