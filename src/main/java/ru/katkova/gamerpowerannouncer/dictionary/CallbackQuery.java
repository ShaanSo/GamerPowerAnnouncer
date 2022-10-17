package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CallbackQuery implements UserAction {

    GAME("GameType", "TYPE_HANDLER"),
    DLC("DLCType", "TYPE_HANDLER"),
    BETA("BetaType", "TYPE_HANDLER"),
    ALL_TYPES("AllTypes", "TYPE_HANDLER"),
    TYPE_HANDLER("TYPE_HANDLER", "TYPE_HANDLER"),

    PC("PCPlatform", "PLATFORM_HANDLER"),
    PS4("PS4Platform", "PLATFORM_HANDLER"),
    X_BOX_ONE("XBoxPlatform", "PLATFORM_HANDLER"),
    SWITCH("SwitchPlatform", "PLATFORM_HANDLER"),
    IOS("IOSPlatform", "PLATFORM_HANDLER"),
    ANDROID("AndroidPlatform", "PLATFORM_HANDLER"),
    VR("VRPlatform", "PLATFORM_HANDLER"),
    ALL_PLATFORMS("AllPlatforms", "PLATFORM_HANDLER"),
    PLATFORM_HANDLER("AllTypes", "PLATFORM_HANDLER");


    public final String Value;
    public final String handler;
}