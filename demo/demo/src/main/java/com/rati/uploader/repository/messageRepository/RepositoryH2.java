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
                           rs.getInt("user_id"),
                           rs.getString("body"),
                           rs.getObject("date_created", LocalDateTime.class),
                           rs.getObject("date_updated", LocalDateTime.class));
    }
    
    @Override
    public List<Message> findAll() {
        String sql = "SELECT * FROM MESSAGE";
        List<Message> messages = jdbcTemplate.query(sql, RepositoryH2::mapRow);
        return messages;
    }
    
    @Override
    public Optional<Message> findById(Integer id) {
        String sql = "SELECT * FROM MESSAGE WHERE id=?";
        Message message = jdbcTemplate.queryForObject(sql, RepositoryH2::mapRow, new Object[]{id});
        return Optional.of(message);
    }

    @Override
    public List<Message> findAllByUserId(Integer userId) {
        String sql = "SELECT * FROM MESSAGE WHERE user_id=?";
        List<Message> messages = jdbcTemplate.query(sql, RepositoryH2::mapRow, new Object[]{userId});
        return messages;
    }

    @Override
    public void save(Message message) {
        String sql = "INSERT INTO MESSAGE (id, user_id, body, date_created, date_updated) VALUES (?, ?, ?, NOW(), NULL)";
        jdbcTemplate.update(sql, message.id(), message.userId(), message.body());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM MESSAGE WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    
    @Override
    public boolean existsByIdAndUserId(Integer id, Integer userId) {
        String sql = "SELECT COUNT(*) FROM MESSAGE WHERE id=? AND user_id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{id, userId});
        return count != null && count > 0;
    }
    
}