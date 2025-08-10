package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.SystemColumn;
import com.ttd.microsoftlistsunittest.service.SystemColumnService;
import com.ttd.microsoftlistsunittest.service.rowmapper.SystemColumnRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemColumnServiceImpl implements SystemColumnService {

    private final JdbcTemplate jdbcTemplate;
    private final SystemColumnRowMapper rowMapper;

    @Override
    public List<SystemColumn> findAll() {
        String sql = "SELECT * FROM SystemColumn";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public SystemColumn findById(Integer id) {
        String sql = "SELECT * FROM SystemColumn WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void create(SystemColumn column) {
        String sql = """
                INSERT INTO SystemColumn (SystemDataTypeId, ColumnName, DisplayOrder, CreatedBy, CreatedAt, CanRename)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
        jdbcTemplate.update(sql,
                column.getSystemDataTypeId(),
                column.getColumnName(),
                column.getDisplayOrder(),
                column.getCreatedBy(),
                column.getCreatedAt(),
                column.getCanRename()
        );
    }

    @Override
    public void update(SystemColumn column) {
        String sql = """
                UPDATE SystemColumn
                SET SystemDataTypeId = ?, ColumnName = ?, DisplayOrder = ?, CanRename = ?
                WHERE Id = ?
            """;
        jdbcTemplate.update(sql,
                column.getSystemDataTypeId(),
                column.getColumnName(),
                column.getDisplayOrder(),
                column.getCanRename(),
                column.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM SystemColumn WHERE Id = ?";
        jdbcTemplate.update(sql, id);
    }
}
