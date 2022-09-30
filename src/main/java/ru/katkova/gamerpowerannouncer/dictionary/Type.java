package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Type {
    GAME("game"),
    LOOT("loot"),
    BETA("beta");

    String value;

    public static Type findByValue(String value) {
        Type result = null;
        for (Type type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
