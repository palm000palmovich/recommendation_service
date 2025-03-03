package com.project.command.model;

import java.util.Objects;
import java.util.UUID;

public class UserWithdrawTransaction {

    private UUID user_id;
    private int debit_amountWithdraw;
    private int invest_amountWithdraw;
    private int credit_amountWithdraw;
    private int saving_amountWithdraw;

    public UserWithdrawTransaction(UUID user_id, int debit_amountWithdraw,
                                   int invest_amountWithdraw,
                                   int credit_amountWithdraw,
                                   int saving_amountWithdraw) {
        this.user_id = user_id;
        this.debit_amountWithdraw = debit_amountWithdraw;
        this.invest_amountWithdraw = invest_amountWithdraw;
        this.credit_amountWithdraw = credit_amountWithdraw;
        this.saving_amountWithdraw = saving_amountWithdraw;
    }

    //Getters
    public UUID getUser_id() {
        return user_id;
    }

    public int getDebit_amountWithdraw() {
        return debit_amountWithdraw;
    }

    public int getInvest_amountWithdraw() {
        return invest_amountWithdraw;
    }

    public int getCredit_amountWithdraw() {
        return credit_amountWithdraw;
    }

    public int getSaving_amountWithdraw() {
        return saving_amountWithdraw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWithdrawTransaction that = (UserWithdrawTransaction) o;
        return Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
