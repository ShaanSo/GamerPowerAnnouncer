package ru.katkova.gamerpowerannouncer.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.katkova.gamerpowerannouncer.data.User;
import ru.katkova.gamerpowerannouncer.dictionary.Command;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChangeTypeHandler implements CommandHandler {
    @Override
    public SendMessage handle(User user) {

        InlineKeyboardButton inlineKeyboardButton1 = InlineKeyboardButton.builder()
                        .text("Game").callbackData("gameType").build();
        InlineKeyboardButton inlineKeyboardButton2 = InlineKeyboardButton.builder()
                        .text("DLC").callbackData("DLCType").build();
        InlineKeyboardButton inlineKeyboardButton3 = InlineKeyboardButton.builder()
                        .text("Beta").callbackData("Beta").build();
        InlineKeyboardButton inlineKeyboardButton4 = InlineKeyboardButton.builder()
                        .text("All Types").callbackData("AllTypes").build();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> list1 = new ArrayList<>();
        list1.add(inlineKeyboardButton1);
        list1.add(inlineKeyboardButton2);
        List<InlineKeyboardButton> list2 = new ArrayList<>();
        list2.add(inlineKeyboardButton3);
        list2.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(list1);
        rowList.add(list2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage message = SendMessage.builder()
                .chatId(user.getChatId())
                .text("Выберите тип:")
                .replyMarkup(inlineKeyboardMarkup)
                .build();
        return message;
    }

    @Override
    public Command getCommand() {
        return Command.CHANGE_TYPE_MODE;
    }
}
