package ru.katkova.gamerpowerannouncer.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_poll")
public class UserPoll {
    @Id
    @Column(name = "chatid")
    Long chatId;

    @Column(name = "pollid")
    String pollId;

    @Column(name = "messageid")
    Integer messageId;

    @Column(name = "poll_question")
    String pollQuestion;

    public static class Builder {
        private UserPoll userPoll;

        public Builder() {
            userPoll = new UserPoll();
        }

        public Builder chatId(Long chatId){
            userPoll.chatId = chatId;
            return this;
        }

        public Builder pollId(String pollId){
            userPoll.pollId = pollId;
            return this;
        }

        public Builder messageId(Integer messageId){
            userPoll.messageId = messageId;
            return this;
        }

        public Builder pollQuestion(String pollQuestion){
            userPoll.pollQuestion = pollQuestion;
            return this;
        }

        public UserPoll build(){
            return userPoll;
        }

    }
}
