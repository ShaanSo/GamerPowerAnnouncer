//package ru.katkova.gamerpowerannouncer.data;
//
//import lombok.Getter;
//import lombok.Setter;
//import ru.katkova.gamerpowerannouncer.dictionary.Platform;
//import ru.katkova.gamerpowerannouncer.dictionary.Type;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "USER_CONFIGURATION")
//public class UserConfiguration {
//    @Id
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "userchatid")
//    private Long UserChatId;
//    @Column(name = "type")
//    private Type type;
//    @Column(name = "platfirmlist")
//    private String platformList;
//
//    public String formPlatformList(List<Platform> platformList) {
//        return  platformList.stream().map(Enum::toString).collect(Collectors.joining(","));
//    }
//}
