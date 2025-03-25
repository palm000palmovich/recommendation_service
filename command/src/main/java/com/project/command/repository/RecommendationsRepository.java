package com.project.command.repository;

import com.project.command.model.DepositTransactions;
import com.project.command.model.WithdrawTransactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Cacheable(cacheNames = {"depositAmount"},  key = "{#userId}")
    public DepositTransactions getDepositAmountByUserId(String userId) {

        String script = "SELECT " +
                "SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS debit_amount, " +
                "SUM(CASE WHEN p.type = 'SAVING' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS saving_amount, " +
                "SUM(CASE WHEN p.type = 'CREDIT' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS credit_amount, " +
                "SUM(CASE WHEN p.type = 'INVEST' AND t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS invest_amount " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ?";

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

    @Cacheable(cacheNames = {"withdrawAmount"},  key = "{#userId}")
    public WithdrawTransactions getWithdrawAmountByUserId(String userId) {
        String script = "SELECT " +
                "SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS debit_amount, " +
                "SUM(CASE WHEN p.type = 'SAVING' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS saving_amount, " +
                "SUM(CASE WHEN p.type = 'CREDIT' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS credit_amount, " +
                "SUM(CASE WHEN p.type = 'INVEST' AND t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS invest_amount " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ?";
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

    public int getCountTransactionsByProductName(String userId, String product){
        String script = "SELECT COUNT(t.product_id) " +
                "FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ? AND p.type = ?";

        Integer count = jdbcTemplate.queryForObject(script, new Object[]{userId, product}, Integer.class);

        return count;
    }


}