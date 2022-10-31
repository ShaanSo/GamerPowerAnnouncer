package ru.katkova.gamerpowerannouncer.data;

import lombok.*;
import org.springframework.data.annotation.Transient;
import ru.katkova.gamerpowerannouncer.dictionary.Command;
import ru.katkova.gamerpowerannouncer.dictionary.Platform;
import ru.katkova.gamerpowerannouncer.dictionary.Type;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "GIVEAWAYS")
public class Giveaway {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "worth")
    private String worth;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "instructions")
    private String instructions;
    @Column(name = "open_giveaway_url")
    private String open_giveaway_url;
    @Column(name = "published_date")
    private String published_date;
    @Column(name = "end_date")
    private String end_date;
    @Column(name = "users")
    private String users;
    @Column(name = "status")
    private String status;
    @Column(name = "gamerpower_url")
    private String gamerpower_url;
    @Column(name = "open_giveaway")
    private String open_giveaway;
    @Column(name = "type")
    private String type;
    @Column(name = "platforms")
    private String platforms;
}
