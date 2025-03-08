package com.project.command.repository;

import com.project.command.model.DepositTransactions;
import com.project.command.model.User;
import com.project.command.model.WithdrawTransactions;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(RecommendationsRepository.class);

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRandomTransactionAmount(UUID user) {
        Integer result = jdbcTemplate.queryForObject(
                "SELECT amount FROM transactions t WHERE t.user_id = ? LIMIT 1",
                Integer.class,
                user);
        return result != null ? result : 0;
    }

    public List<User> getFewUsers() {
        return jdbcTemplate.query("SELECT id, username, first_name, last_name FROM users LIMIT 15",
                (rs, rowNum) ->
                        new User(
                                UUID.fromString(rs.getString("id")),
                                rs.getString("username"),
                                rs.getString("first_name"),
                                rs.getString("last_name")));

    }

    public DepositTransactions getDepositAmountByUserId(String userId) {

        String script = "SELECT " +
                "SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS debit_amount, " +
                "SUM(CASE WHEN p.type = 'SAVING' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS saving_amount, " +
                "SUM(CASE WHEN p.type = 'CREDIT' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS credit_amount, " +
                "SUM(CASE WHEN p.type = 'INVEST' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS invest_amount " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ?";

        //new Object[]{userUUID}
        DepositTransactions depositTransactions = jdbcTemplate.queryForObject(script, (rs, rowNum) ->
            new DepositTransactions(
                    rs.getInt("debit_amount"),
                    rs.getInt("saving_amount"),
                    rs.getInt("credit_amount"),
                    rs.getInt("invest_amount")),
                userId);
        logger.debug("Debit info: " + depositTransactions.toString());
        return depositTransactions;
    }

    public WithdrawTransactions getWithdrawAmountByUserId(String userId) {

        String script = "SELECT " +
                "SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS debit_amount, " +
                "SUM(CASE WHEN p.type = 'SAVING' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS saving_amount, " +
                "SUM(CASE WHEN p.type = 'CREDIT' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS credit_amount, " +
                "SUM(CASE WHEN p.type = 'INVEST' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS invest_amount " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ?";

        //new Object[]{userUUID}
        WithdrawTransactions withdrawTransactions = jdbcTemplate.queryForObject(script, (rs, rowNum) ->
                        new WithdrawTransactions(
                                rs.getInt("debit_amount"),
                                rs.getInt("saving_amount"),
                                rs.getInt("credit_amount"),
                                rs.getInt("invest_amount")),
                userId);
        logger.debug("Withdraw info: " + withdrawTransactions.toString());
        return withdrawTransactions;
    }


}