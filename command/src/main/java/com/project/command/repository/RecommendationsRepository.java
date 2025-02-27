package com.project.command.repository;

import com.project.command.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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
            return jdbcTemplate.query("SELECT id, username, first_name, last_name FROM users LIMIT 15",
                    (rs, rowNum) ->
                            new User(
                                    UUID.fromString(rs.getString("id")),
                                    rs.getString("username"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name")));

        }

    }