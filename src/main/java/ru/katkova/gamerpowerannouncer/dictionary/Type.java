package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    GAME("Free Games", "Game"),
    DLC("Free Loot", "DLC"),
    BETA("Beta Access", "Early Access"),
    OTHER("Other", "Other");

    private final String value;
    private final String apiValue;
}
