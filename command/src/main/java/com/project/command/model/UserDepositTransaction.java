package com.project.command.model;

import java.util.Objects;
import java.util.UUID;

public class UserDepositTransaction {
    private UUID user_id;
    private int debit_amountDeposit;
    private int invest_amountDeposit;
    private int credit_amountDeposit;
    private int saving_amountDeposit;

    public UserDepositTransaction(UUID user_id, int debit_amountDeposit, int invest_amountDeposit, int credit_amountDeposit, int saving_amountDeposit) {
        this.user_id = user_id;
        this.debit_amountDeposit = debit_amountDeposit;
        this.invest_amountDeposit = invest_amountDeposit;
        this.credit_amountDeposit = credit_amountDeposit;
        this.saving_amountDeposit = saving_amountDeposit;
    }

    //Getters
    public UUID getUser_id() {
        return user_id;
    }

    public int getDebit_amountDeposit() {
        return debit_amountDeposit;
    }

    public int getInvest_amountDeposit() {
        return invest_amountDeposit;
    }

    public int getCredit_amountDeposit() {
        return credit_amountDeposit;
    }

    public int getSaving_amountDeposit() {
        return saving_amountDeposit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDepositTransaction that = (UserDepositTransaction) o;
        return Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
