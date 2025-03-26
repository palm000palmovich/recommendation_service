package com.project.command.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.command.component.RecommendationRuleSet;
import com.project.command.utils.CommandSupportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecommendComand {
    private static final String RECOMMEND = "/recommend";
    private final String REGEX = "";

    @Autowired
    private RecommendationRuleSet recommendationRuleSet;

    public boolean support(Update update){
        return CommandSupportUtils.ifStringEqualsCommand(update, RECOMMEND);
    }

    public SendMessage handle(Update update){

    }

}
