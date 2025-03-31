package com.project.command.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public String getUserIdByUserName(String userName){
        UUID userId = jdbcTemplate.queryForObject(
                "SELECT id FROM users WHERE username = ?", UUID.class, userName
        );
        return userId.toString();
    }
}
