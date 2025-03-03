package com.project.command.repository;

import com.project.command.model.UserDepositTransaction;
import com.project.command.model.UserWithdrawTransaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UsersDataRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersDataRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    //------------------FOR INTERACTIVE TEST--------------------------

    //Find users with withdraw transactions
    // Метод для получения информации о пользователе из таблицы users_withdraw
    public UserWithdrawTransaction getUserWithdrawInfo(String id) {

        return  jdbcTemplate.queryForObject(
                "SELECT user_id, debit_amount, saving_amount, credit_amount, invest_amount FROM users_withdraws WHERE user_id = ?",
                (rs, rowNum) -> new UserWithdrawTransaction(
                        rs.getObject("user_id", UUID.class),
                        rs.getInt("debit_amount"),
                        rs.getInt("saving_amount"),
                        rs.getInt("credit_amount"),
                        rs.getInt("invest_amount")),
                id);
    }

    //Find users with deposit transactions
    // Метод для получения информации о пользователе из таблицы users_deposit

    public UserDepositTransaction getUserDepositInfo(String id) {
        return  jdbcTemplate.queryForObject(
                "SELECT user_id, debit_amount, saving_amount, credit_amount, invest_amount FROM users_deposits WHERE user_id = ?",
                (rs, rowNum) -> new UserDepositTransaction(
                        rs.getObject("user_id", UUID.class),
                        rs.getInt("debit_amount"),
                        rs.getInt("saving_amount"),
                        rs.getInt("credit_amount"),
                        rs.getInt("invest_amount")),
                id);
    }

}
