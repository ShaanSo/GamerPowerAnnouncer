package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
