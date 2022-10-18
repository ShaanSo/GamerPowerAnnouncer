package ru.katkova.gamerpowerannouncer.data;

import lombok.Getter;
import lombok.Setter;
import ru.katkova.gamerpowerannouncer.dictionary.Launcher;
import ru.katkova.gamerpowerannouncer.dictionary.Platform;
import ru.katkova.gamerpowerannouncer.dictionary.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.StringJoiner;

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

    @Column(name = "launcher_list")
    private String preferredLauncherList;

    public User()
    { }

    public User(Long chatId) {
        this.chatId = chatId;
        this.setDefaultProperties();
    }

    public void setDefaultProperties() {
        StringJoiner preferredPlatformsString = new StringJoiner(",");
        StringJoiner preferredTypesString = new StringJoiner(",");
        StringJoiner preferredLaunchersString = new StringJoiner(",");
        for (Platform platform: Platform.values()) {
            preferredPlatformsString.add(platform.getValue());
        }

        for (Type type: Type.values()) {
            preferredTypesString.add(type.getValue());
        }

        for (Launcher launcher: Launcher.values()) {
            preferredLaunchersString.add(launcher.getValue());
        }
        this.setPreferredPlatformList(preferredPlatformsString.toString());
        this.setPreferredTypeList(preferredTypesString.toString());
        this.setPreferredLauncherList(preferredLaunchersString.toString());
    }
}
