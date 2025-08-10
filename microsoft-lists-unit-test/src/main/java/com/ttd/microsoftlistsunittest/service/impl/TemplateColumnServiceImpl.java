package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.TemplateColumn;
import com.ttd.microsoftlistsunittest.service.TemplateColumnService;
import com.ttd.microsoftlistsunittest.service.rowmapper.TemplateColumnRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateColumnServiceImpl implements TemplateColumnService {

    private final JdbcTemplate jdbcTemplate;
    private final TemplateColumnRowMapper rowMapper;

    @Override
    public List<TemplateColumn> findAll() {
        String sql = "SELECT * FROM TemplateColumn";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<TemplateColumn> findById(Integer id) {
        String sql = "SELECT * FROM TemplateColumn WHERE Id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    @Override
    public List<TemplateColumn> findByListTemplateId(Integer listTemplateId) {
        String sql = "SELECT * FROM TemplateColumn WHERE ListTemplateId = ?";
        return jdbcTemplate.query(sql, rowMapper, listTemplateId);
    }

    @Override
    public int save(TemplateColumn column) {
        validate(column);
        String sql = """
            INSERT INTO TemplateColumn (SystemDataTypeId, ListTemplateId, ColumnName, ColumnDescription, DisplayOrder, IsVisible)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                column.getSystemDataTypeId(),
                column.getListTemplateId(),
                column.getColumnName(),
                column.getColumnDescription(),
                column.getDisplayOrder(),
                column.getIsVisible()
        );
    }

    @Override
    public int update(TemplateColumn column) {
        if (column.getId() == null || column.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or invalid");
        }
        validate(column);
        String sql = """
            UPDATE TemplateColumn
            SET SystemDataTypeId = ?, ListTemplateId = ?, ColumnName = ?, ColumnDescription = ?, DisplayOrder = ?, IsVisible = ?
            WHERE Id = ?
        """;
        return jdbcTemplate.update(sql,
                column.getSystemDataTypeId(),
                column.getListTemplateId(),
                column.getColumnName(),
                column.getColumnDescription(),
                column.getDisplayOrder(),
                column.getIsVisible(),
                column.getId()
        );
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM TemplateColumn WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private void validate(TemplateColumn column) {
        if (column.getSystemDataTypeId() == null || column.getListTemplateId() == null) {
            throw new IllegalArgumentException("SystemDataTypeId and ListTemplateId cannot be null");
        }
        if (column.getColumnName() == null || column.getColumnName().trim().isEmpty()) {
            throw new IllegalArgumentException("ColumnName cannot be empty");
        }
    }
}
