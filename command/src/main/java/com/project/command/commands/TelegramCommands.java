package com.project.command.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface TelegramCommands {
    boolean support(Update update);

    SendMessage handle(Update update);
}
