package com.project.command.utils;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CommandSupportUtils{
    public static boolean ifStringEqualsCommand(Update update, String command){
        if (splitter(update).get(0).equals(command)){
            return true;
        }

        return text(update)
                .map(it -> it.equals(command))
                .orElse(false);
    }

    public static Long chatId(Update update){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.chat())
                .map(it -> it.id())
                .orElse(-1L);
    }

    public static List<String> splitter(Update update){
        String recommendUsername = text(update).get();

        String[] parts = recommendUsername.split(" ", 2);
        String commandPart = parts.length > 0 ? parts[0] : null;
        String usernamePart = parts.length > 1 ? parts[1] : null;

        List<String> splittedrecommendUsernameInList = new ArrayList<>();
        splittedrecommendUsernameInList.add(commandPart);
        splittedrecommendUsernameInList.add(usernamePart);

        return splittedrecommendUsernameInList;
    }

    public static Optional<String> text(Update update){
        return Optional.of(update)
                .map(it -> it.message())
                .map(it -> it.text());
    }
}
