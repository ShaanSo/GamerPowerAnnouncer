package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command implements UserAction {

    START("/start", "START_COMMAND_HANDLER"),
    CHANGE_PLATFORM("/platform", "PLATFORM_COMMAND_HANDLER"),
    CHANGE_TYPE("/type", "TYPE_COMMAND_HANDLER"),

    SHOW("/show", "SHOW_COMMAND_HANDLER");


    public String Value;
    public String handler;

//    public String getHandler() {
//        return this.handler;
//    }
}
