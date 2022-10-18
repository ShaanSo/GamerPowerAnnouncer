package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command implements UserAction {

    START("/start", "START_COMMAND_HANDLER"),
    CHANGE_PLATFORM("/platform", "PLATFORM_COMMAND_HANDLER"),
    CHANGE_TYPE("/type", "TYPE_COMMAND_HANDLER"),
    CHANGE_LAUNCHER("/launcher", "LAUNCHER_COMMAND_HANDLER"),
    DEFAULT("/default", "DEFAULT_COMMAND_HANDLER"),
    SHOW("/show", "SHOW_COMMAND_HANDLER");

    private final String Value;
    private final String handler;

//    public String getHandler() {
//        return this.handler;
//    }
}
