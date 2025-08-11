package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ColumnSettingObject;
import com.ttd.microsoftlistsunittest.service.ColumnSettingObjectService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ColumnSettingObjectRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColumnSettingObjectServiceImpl implements ColumnSettingObjectService {

    private final JdbcTemplate jdbcTemplate;
    private final ColumnSettingObjectRowMapper rowMapper;

    @Override
    public List<ColumnSettingObject> findAll() {
        String sql = "SELECT * FROM ColumnSettingObject";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving column setting objects", e);
        }
    }

    @Override
    public Optional<ColumnSettingObject> findById(Integer id) {
        String sql = "SELECT * FROM ColumnSettingObject WHERE Id = ?";
        try {
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving column setting object with ID: " + id, e);
        }
    }

    @Override
    public int save(ColumnSettingObject obj) {
        validate(obj);
        String sql = """
                    INSERT INTO ColumnSettingObject (
                        ColumnId, DisplayName, DisplayColor, DisplayOrder, Context, CreatedAt, UpdatedAt
                    ) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try {
            return jdbcTemplate.update(sql,
                    obj.getColumnId(),
                    obj.getDisplayName(),
                    obj.getDisplayColor(),
                    obj.getDisplayOrder(),
                    obj.getContext(),
                    obj.getCreatedAt(),
                    obj.getUpdatedAt()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving column setting object", e);
        }
    }

    @Override
    public int update(ColumnSettingObject obj) {
        if (obj.getId() == null || obj.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        validate(obj);
        String sql = """
                    UPDATE ColumnSettingObject
                    SET ColumnId = ?, DisplayName = ?, DisplayColor = ?, DisplayOrder = ?, Context = ?, UpdatedAt = ?
                    WHERE Id = ?
                """;
        try {
            return jdbcTemplate.update(sql,
                    obj.getColumnId(),
                    obj.getDisplayName(),
                    obj.getDisplayColor(),
                    obj.getDisplayOrder(),
                    obj.getContext(),
                    obj.getUpdatedAt(),
                    obj.getId()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating column setting object", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = "DELETE FROM ColumnSettingObject WHERE Id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting column setting object", e);
        }
    }

    private void validate(ColumnSettingObject obj) {
        if (obj == null) {
            throw new IllegalArgumentException("ColumnSettingObject cannot be null");
        }
        if (obj.getContext() == null || obj.getContext().trim().isEmpty()) {
            throw new IllegalArgumentException("Context cannot be null or empty");
        }
    }
}
