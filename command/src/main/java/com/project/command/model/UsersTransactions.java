package com.project.command.model;

import java.util.Objects;
import java.util.UUID;

public class UsersTransactions {
    private UUID user_id;
    //Withdraw
    private int debit_amountWithdraw;
    private int invest_amountWithdraw;
    private int credit_amountWithdraw;
    private int saving_amountWithdraw;

    //Deposit
    private int debit_amountDeposit;
    private int invest_amountDeposit;
    private int credit_amountDeposit;
    private int saving_amountDeposit;

    public UsersTransactions(UUID user_id, int debit_amountWithdraw, int invest_amountWithdraw, int credit_amountWithdraw,
                             int saving_amountWithdraw, int debit_amountDeposit, int invest_amountDeposit, int credit_amountDeposit,
                             int saving_amountDeposit) {
        this.user_id = user_id;
        this.debit_amountWithdraw = debit_amountWithdraw;
        this.invest_amountWithdraw = invest_amountWithdraw;
        this.credit_amountWithdraw = credit_amountWithdraw;
        this.saving_amountWithdraw = saving_amountWithdraw;
        this.debit_amountDeposit = debit_amountDeposit;
        this.invest_amountDeposit = invest_amountDeposit;
        this.credit_amountDeposit = credit_amountDeposit;
        this.saving_amountDeposit = saving_amountDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersTransactions that = (UsersTransactions) o;
        return Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
