package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListRowComment;
import com.ttd.microsoftlistsunittest.service.ListRowCommentService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.ListRowCommentRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListRowCommentServiceImpl implements ListRowCommentService {

    private final JdbcTemplate jdbcTemplate;
    private final ListRowCommentRowMapper rowMapper;

    @Override
    public List<ListRowComment> findAll() {
        String sql = "SELECT * FROM ListRowComment";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving comments", e);
        }
    }

    @Override
    public Optional<ListRowComment> findById(Integer id) {
        String sql = "SELECT * FROM ListRowComment WHERE Id = ?";
        try {
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving comment with ID: " + id, e);
        }
    }

    @Override
    public int save(ListRowComment comment) {
        validate(comment);
        String sql = """
                    INSERT INTO ListRowComment (ListRowId, Content, CreatedBy, CreatedAt, UpdatedAt)
                    VALUES (?, ?, ?, ?, ?)
                """;
        try {
            return jdbcTemplate.update(sql,
                    comment.getListRowId(),
                    comment.getContent(),
                    comment.getCreatedBy(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving comment", e);
        }
    }

    @Override
    public int update(ListRowComment comment) {
        if (comment.getId() == null || comment.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        validate(comment);
        String sql = """
                    UPDATE ListRowComment
                    SET ListRowId = ?, Content = ?, CreatedBy = ?, UpdatedAt = ?
                    WHERE Id = ?
                """;
        try {
            return jdbcTemplate.update(sql,
                    comment.getListRowId(),
                    comment.getContent(),
                    comment.getCreatedBy(),
                    comment.getUpdatedAt(),
                    comment.getId()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating comment", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = "DELETE FROM ListRowComment WHERE Id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting comment", e);
        }
    }

    private void validate(ListRowComment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
    }
}
