package com.project.command.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.command.utils.CommandSupportUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements TelegramCommands {
    private static final String START = "/start";

    @Override
    public boolean support(Update update){
        return CommandSupportUtils.ifStringEqualsCommand(update, START);
    }

    @Override
    public SendMessage handle(Update update){
        long chatId = CommandSupportUtils.chatId(update);

        String startInfo = "Привет! Я бот, подбирающий банковские рекомендации." + "\n"
                + "Введи команду /recommend";

        return new SendMessage(String.valueOf(chatId), startInfo);
    }
}
