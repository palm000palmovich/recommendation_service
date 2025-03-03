package com.project.command.repository;

import com.project.command.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UsersDataRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersDataRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //______________FOR DEBIT__________________________

    //Final amount for withdraw-transactions
    public int getTotalDebitWithdrawAmount(String userId) {
        // Преобразуем userId из String в UUID
        UUID userUUID = UUID.fromString(userId);

        Integer totalAmount = jdbcTemplate.queryForObject(
                "SELECT SUM(t.amount) FROM transactions t " +
                        "JOIN products p ON t.product_id = p.id " +
                        "WHERE t.user_id = ? AND t.type = 'withdraw' AND p.type = 'DEBIT'",
                Integer.class,
                userUUID);

        return totalAmount;
    }






    //------------------FOR INTERACTIVE TEST--------------------------
    //Find users with withdraw-debit transactions
    public List<UUID> getUsersWithDebitWithdrawTransactions() {
        return jdbcTemplate.queryForList(
                "SELECT t.user_id FROM transactions t " +
                        " WHERE t.type = 'deposit' JOIN products p ON t.product_id = p.id",
                UUID.class);
    }

}
