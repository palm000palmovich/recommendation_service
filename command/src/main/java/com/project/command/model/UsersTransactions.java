package com.project.command.model;

import java.util.UUID;

public class UsersTransactions {
    private UUID user_id;
    private int debit_amount;
    private int invest_amount;
    private int credit_amount;
    private int saving_amount;


    public int getDebit_amount() {
        return debit_amount;
    }

    public int getInvest_amount() {
        return invest_amount;
    }

    public int getCredit_amount() {
        return credit_amount;
    }

    public int getSaving_amount() {
        return saving_amount;
    }

    public UsersTransactions(UUID user_id, int debit_amount, int invest_amount, int credit_amount, int saving_amount) {
        this.user_id = user_id;
        this.debit_amount = debit_amount;
        this.invest_amount = invest_amount;
        this.credit_amount = credit_amount;
        this.saving_amount = saving_amount;
    }


}
