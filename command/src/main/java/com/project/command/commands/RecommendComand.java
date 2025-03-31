package com.project.command.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.command.component.RecommendationRuleSet;
import com.project.command.model.RecommendationsByRules;
import com.project.command.repository.UserRepository;
import com.project.command.utils.CommandSupportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class RecommendComand implements TelegramCommands{
    Logger logger = LoggerFactory.getLogger(RecommendComand.class);
    private static final String RECOMMEND = "/recommend";

    @Autowired
    private RecommendationRuleSet recommendationRuleSet;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean support(Update update){
        return CommandSupportUtils.ifStringEqualsCommand(update, RECOMMEND);
    }

    @Override
    public SendMessage handle(Update update){
        long chatId = CommandSupportUtils.chatId(update);
        String username = CommandSupportUtils.splitter(update).get(1);

        String userId = userRepository.getUserIdByUserName(username);
        logger.debug("For username {" + username + "} + id: " + userId);

        String message = "";

        if (recommendationRuleSet.recommendationSelection(userId).size() == 0){
            //message = "Для этого пользователя нет подходящих рекоммендаций";
            return new SendMessage(String.valueOf(chatId),
                    "Для этого пользователя нет подходящих рекоммендаций");
        }

        List<RecommendationsByRules> listOfRecs = recommendationRuleSet.recommendationSelection(userId);
        message = "Новые продукты для вас: " + "\n";


        for (int i = 0; i < listOfRecs.size(); i++){
            message += (i+1) + ") Название продукта: " + listOfRecs.get(i).getProduct_name() + ", описание: "  +
                    listOfRecs.get(i).getProduct_text() + "; \n";
        }

        return new SendMessage(String.valueOf(chatId), message);
    }

}
