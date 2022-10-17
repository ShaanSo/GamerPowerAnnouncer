package ru.katkova.gamerpowerannouncer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PollQuestions implements UserAction {
    TYPE("Please, select giveaway type:", "TYPE_HANDLER"),
    PLATFORM("Please, select giveaway platform:", "PLATFORM_HANDLER");

    public final String Value;
    public final String handler;

    public static PollQuestions getByHandler(String handler) {
        for (PollQuestions question: PollQuestions.values()) {
            if (question.getHandler().equals(handler))
                return question;
        }
        return null;
    }
}
