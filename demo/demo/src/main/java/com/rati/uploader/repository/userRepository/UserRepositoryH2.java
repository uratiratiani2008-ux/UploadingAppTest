package com.rati.uploader.repository.userRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@Profile("PROD")
public class UserRepositoryH2 implements UserRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(String username, String password) {
        String sql = "INSERT INTO User (id, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, jdbcTemplate.queryForObject("SELECT MAX(id) + 1 FROM User", Integer.class), username, password);
    }

    @Override
    public boolean login(String username, String password) {
        String sql = "SELECT COUNT(*) FROM User WHERE username=? AND password=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{username, password});
        return count != null && count > 0;
    }

    @Override
    public Integer getIdFromUsername(String username) {
        String sql = "SELECT id FROM User WHERE username=?";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{username});
        return id;
    }
    
}
