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
@Table(name = "TELEGRAM_USERS")
public class User {
    @Id
    @Column(name = "chatid")
    private Long chatId;

    @Column(name = "platform_list")
    private String preferredPlatformList;

    @Column(name = "type_list")
    private String preferredTypeList;

    public User()
    { }

    public User(Long chatId) {
        this.chatId = chatId;
    }
}
