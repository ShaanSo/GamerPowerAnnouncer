package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PollAnswer implements UserAction{

    POLL_ANSWER("PollAnswer", "POLL_ANSWER_HANDLER");

    private final String Value;
    private final String handler;
}
