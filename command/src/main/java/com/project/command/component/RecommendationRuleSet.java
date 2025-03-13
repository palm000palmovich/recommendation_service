package com.project.command.component;


import com.project.command.model.DepositTransactions;
import com.project.command.model.RecommendationsDTO;
import com.project.command.model.WithdrawTransactions;
import com.project.command.repository.RecommendationsConstants;
import com.project.command.repository.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RecommendationRuleSet {
    @Autowired
    private RecommendationsRepository recommendationsRepository;
    private RecommendationsConstants recommendationsConstants;
    private RecommendationsDTO recommendationsDTO;
    private Logger logger = LoggerFactory.getLogger(RecommendationRuleSet.class);

    public List<RecommendationsDTO> getRecsForUserById(String userId){
        List<RecommendationsDTO> listRecommendations = new ArrayList<>();

        logger.debug("RecommendationRuleSet.getRecsForUserById");

        DepositTransactions depositTransactions = recommendationsRepository.getDepositAmountByUserId(userId);
        int depositDebit = depositTransactions.getDebit_amount();
        int depositSaving = depositTransactions.getSaving_amount();
        int depositCredit = depositTransactions.getCredit_amount();
        int depositInvest = depositTransactions.getInvest_amount();

        logger.debug("Deposits: " + depositTransactions);

        WithdrawTransactions  withdrawTransactions = recommendationsRepository.getWithdrawAmountByUserId(userId);
        int withdrawDebit = withdrawTransactions.getDebit_amount();
        int withdrawSaving = withdrawTransactions.getSaving_amount();
        int withdrawCredit = withdrawTransactions.getCredit_amount();
        int withdrawInvest = withdrawTransactions.getInvest_amount();

        logger.debug("Withdraws: " + withdrawTransactions);

        //Invest500
        if (depositDebit + withdrawDebit > 0
                && depositInvest + withdrawInvest == 0
                && depositSaving > 1000){listRecommendations.add(
                        new RecommendationsDTO(recommendationsConstants.invest500, recommendationsConstants.invest500Text));}

        //Top saving
        if (depositDebit + withdrawDebit > 0
                && (depositDebit >= 50.000
                || depositSaving >= 50.000)
                && depositDebit > withdrawDebit){listRecommendations.add(
                        new RecommendationsDTO(recommendationsConstants.topSaving, recommendationsConstants.topSavingTest));}


        //Простой кредит
        if (depositCredit + withdrawCredit == 0
                && depositDebit > withdrawDebit
                && withdrawDebit > 100.000){listRecommendations.add(
                        new RecommendationsDTO(recommendationsConstants.simpleCredit, recommendationsConstants.simpleCreditText)
        );}

        return listRecommendations;
    }
}
