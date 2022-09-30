package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum Command {

    START("/start"),
    CHANGE_PLATFORM_MODE("/platform"),
    CHANGE_TYPE_MODE("/type");

    public String Value;


}
