package com.rati.uploader.repository.messageRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rati.uploader.model.Message;

@Repository
@Profile("PROD")
public class RepositoryH2 implements RepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    public RepositoryH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Message(rs.getInt("id"),
                           rs.getString("username"),
                           rs.getString("body"),
                           rs.getObject("date_created", LocalDateTime.class),
                           rs.getObject("date_updated", LocalDateTime.class));
    }
    
    public List<Message> getAllMessages() {
        String sql = "SELECT * FROM Messages";
        List<Message> messages = jdbcTemplate.query(sql, RepositoryH2::mapRow);
        return messages;
    }
    
    public Optional<Message> getMessageById(Integer id) {
        String sql = "SELECT * FROM Messages WHERE id=?";
        Message message = jdbcTemplate.queryForObject(sql, RepositoryH2::mapRow, new Object[]{id});
        return Optional.of(message);
    }

    public List<Message> getMessagesByUsername(String username) {
        String sql = "SELECT * FROM Messages WHERE username=?";
        List<Message> messages = jdbcTemplate.query(sql, RepositoryH2::mapRow, new Object[]{username});
        return messages;
    }

    public void add(Integer id, String username, String body) {
        String sql = "INSERT INTO Messages (id, username, body, date_created, date_updated) VALUES (?, ?, ?, NOW(), NULL)";
        jdbcTemplate.update(sql, id, username, body);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Messages WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Integer id, String body) {
        String sql = "UPDATE Messages SET body=?, date_updated=NOW() WHERE id=?";
        jdbcTemplate.update(sql, body, id);
    }
    
    public boolean isOwner(Integer id, String username) {
        String sql = "SELECT COUNT(*) FROM Messages WHERE id=? AND username=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{id, username});
        return count != null && count > 0;
    }

}