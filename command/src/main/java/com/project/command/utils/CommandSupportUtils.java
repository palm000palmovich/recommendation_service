package com.project.command.utils;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandSupportUtils{
    public static boolean ifStringEqualsCommand(Update update, String command){
        return text(update)
                .map(it -> it.equals(command))
                .orElse(false);
    }

    private void splitter(Update update){
        String recommendUsername = text(update).get();

        String[] parts
    }

    public static boolean ifStringLooksLikeRegex(Update update, String regex){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.text())
                .map(it -> it.matches(regex))
                .orElse(false);
    }

    public static Long chatId(Update update){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.chat())
                .map(it -> it.id())
                .orElse(-1L);
    }

    public static Optional<String> text(Update update){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.text());
    }
}
