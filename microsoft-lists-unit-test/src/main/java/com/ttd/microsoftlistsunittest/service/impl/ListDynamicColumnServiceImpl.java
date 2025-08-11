package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListDynamicColumn;
import com.ttd.microsoftlistsunittest.service.ListDynamicColumnService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.ListDynamicColumnRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListDynamicColumnServiceImpl implements ListDynamicColumnService {

    private final JdbcTemplate jdbcTemplate;
    private final ListDynamicColumnRowMapper rowMapper;

    @Override
    public ListDynamicColumn findById(Integer id) {
        String sql = "SELECT * FROM ListDynamicColumn WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<ListDynamicColumn> findByListId(Integer listId) {
        String sql = "SELECT * FROM ListDynamicColumn WHERE ListId = ?";
        return jdbcTemplate.query(sql, rowMapper, listId);
    }

    @Override
    public void create(ListDynamicColumn column) {
        String sql = """
                    INSERT INTO ListDynamicColumn (
                        ListId, SystemDataTypeId, SystemColumnId, ColumnName, ColumnDescription,
                        DisplayOrder, IsSystemColumn, IsVisible, CreatedBy, CreatedAt, UpdatedAt
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                column.getListId(),
                column.getSystemDataTypeId(),
                column.getSystemColumnId(),
                column.getColumnName(),
                column.getColumnDescription(),
                column.getDisplayOrder(),
                column.getIsSystemColumn(),
                column.getIsVisible(),
                column.getCreatedBy(),
                column.getCreatedAt(),
                column.getUpdatedAt()
        );
    }

    @Override
    public void update(ListDynamicColumn column) {
        String sql = """
                    UPDATE ListDynamicColumn SET
                        SystemDataTypeId = ?, SystemColumnId = ?, ColumnName = ?, ColumnDescription = ?,
                        DisplayOrder = ?, IsSystemColumn = ?, IsVisible = ?, UpdatedAt = ?
                    WHERE Id = ?
                """;
        jdbcTemplate.update(sql,
                column.getSystemDataTypeId(),
                column.getSystemColumnId(),
                column.getColumnName(),
                column.getColumnDescription(),
                column.getDisplayOrder(),
                column.getIsSystemColumn(),
                column.getIsVisible(),
                column.getUpdatedAt(),
                column.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM ListDynamicColumn WHERE Id = ?";
        jdbcTemplate.update(sql, id);
    }
}
