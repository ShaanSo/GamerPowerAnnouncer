package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PollQuestions implements UserAction {
    TYPE("Please, select giveaway type:", "TYPE_HANDLER"),
    LAUNCHER("Please, select giveaway launcher:", "LAUNCHER_HANDLER"),
    PLATFORM("Please, select giveaway platform:", "PLATFORM_HANDLER");

    private final String value;
    private final String handler;
}
