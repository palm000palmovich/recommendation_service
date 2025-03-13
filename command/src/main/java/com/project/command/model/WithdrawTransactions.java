package com.project.command.model;

public class WithdrawTransactions {
    private int debit_amount;
    private int saving_amount;
    private int credit_amount;
    private int invest_amount;

    public WithdrawTransactions(int debit_amount, int saving_amount, int credit_amount, int invest_amount) {
        this.debit_amount = debit_amount;
        this.saving_amount = saving_amount;
        this.credit_amount = credit_amount;
        this.invest_amount = invest_amount;
    }

    //Getters


    public int getDebit_amount() {
        return debit_amount;
    }

    public int getSaving_amount() {
        return saving_amount;
    }

    public int getCredit_amount() {
        return credit_amount;
    }

    public int getInvest_amount() {
        return invest_amount;
    }

    @Override
    public String toString() {
        return "WithdrawTransactions{" +
                "debit_amount=" + debit_amount +
                ", saving_amount=" + saving_amount +
                ", credit_amount=" + credit_amount +
                ", invest_amount=" + invest_amount +
                '}';
    }
}
