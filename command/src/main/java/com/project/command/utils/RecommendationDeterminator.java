package com.project.command.utils;

import com.project.command.model.UsersTransactions;
import org.springframework.stereotype.Component;

@Component
public class RecommendationDeterminator {

    public void getOwnRecommendation(UsersTransactions usersTransactions){
        String recommendation = "";

        int debitAmount = usersTransactions.getDebit_amount();
        int investAmount = usersTransactions.getInvest_amount();
        int creditAmount = usersTransactions.getCredit_amount();
        int savingAmount = usersTransactions.getSaving_amount();

        boolean rule1 = (debitAmount != 0 && investAmount == 0 && savingAmount > 1000); //Invest 500
        //boolean rule2 = ()
    }
}
