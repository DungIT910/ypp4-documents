package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.FileAttachment;
import com.ttd.microsoftlistsunittest.service.FileAttachmentService;
import com.ttd.microsoftlistsunittest.service.rowmapper.FileAttachmentRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileAttachmentServiceImpl implements FileAttachmentService {

    private final JdbcTemplate jdbcTemplate;
    private final FileAttachmentRowMapper rowMapper;

    @Override
    public List<FileAttachment> findAll() {
        String sql = "SELECT * FROM FileAttachment";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving file attachments", e);
        }
    }

    @Override
    public Optional<FileAttachment> findById(Integer id) {
        String sql = "SELECT * FROM FileAttachment WHERE Id = ?";
        try {
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving file attachment with ID: " + id, e);
        }
    }

    @Override
    public int save(FileAttachment attachment) {
        validate(attachment);
        String sql = """
                    INSERT INTO FileAttachment (ListRowId, FileAttachmentName, FileUrl, CreatedAt, UpdatedAt)
                    VALUES (?, ?, ?, ?, ?)
                """;
        try {
            return jdbcTemplate.update(sql,
                    attachment.getListRowId(),
                    attachment.getFileAttachmentName(),
                    attachment.getFileUrl(),
                    attachment.getCreatedAt(),
                    attachment.getUpdatedAt()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving file attachment", e);
        }
    }

    @Override
    public int update(FileAttachment attachment) {
        if (attachment.getId() == null || attachment.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        validate(attachment);
        String sql = """
                    UPDATE FileAttachment 
                    SET ListRowId = ?, FileAttachmentName = ?, FileUrl = ?, CreatedAt = ?, UpdatedAt = ?
                    WHERE Id = ?
                """;
        try {
            return jdbcTemplate.update(sql,
                    attachment.getListRowId(),
                    attachment.getFileAttachmentName(),
                    attachment.getFileUrl(),
                    attachment.getCreatedAt(),
                    attachment.getUpdatedAt(),
                    attachment.getId()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating file attachment", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = "DELETE FROM FileAttachment WHERE Id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting file attachment", e);
        }
    }

    private void validate(FileAttachment attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("FileAttachment cannot be null");
        }
        if (attachment.getListRowId() == null) {
            throw new IllegalArgumentException("ListRowId cannot be null");
        }
    }
}
