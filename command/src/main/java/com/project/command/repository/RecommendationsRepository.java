package com.project.command.repository;

import com.project.command.model.RecommendationDTO;
import com.project.command.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
    public class RecommendationsRepository {
        private final JdbcTemplate jdbcTemplate;

        public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public int getRandomTransactionAmount(UUID user){
            Integer result = jdbcTemplate.queryForObject(
                    "SELECT amount FROM transactions t WHERE t.user_id = ? LIMIT 1",
                    Integer.class,
                    user);
            return result != null ? result : 0;
        }

        public List<User> getFewUsers(){
            return jdbcTemplate.queryForList(
                    "SELECT * FROM users LIMIT 15",
                    User.class);
        }

    public Optional<String> findUserNameById(String user_id) {
        String userName = jdbcTemplate.queryForObject(
                "SELECT username FROM users t WHERE t.user_id = user_id",
                String.class);
        if (userName != null) {
            return Optional.of(userName);
        } else {
            return Optional.empty();
        }
    }

    public List<RecommendationDTO> findById(String userId) {
        return Collections.emptyList();
    }
}