package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListView;
import com.ttd.microsoftlistsunittest.service.ListViewService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ListViewRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListViewServiceImpl implements ListViewService {

    private final JdbcTemplate jdbcTemplate;
    private final ListViewRowMapper rowMapper;

    @Override
    public ListView findById(Integer id) {
        String sql = "SELECT * FROM ListView WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<ListView> findByListId(Integer listId) {
        String sql = "SELECT * FROM ListView WHERE ListId = ?";
        return jdbcTemplate.query(sql, rowMapper, listId);
    }

    @Override
    public void create(ListView view) {
        String sql = """
            INSERT INTO ListView (ListId, CreatedBy, ViewTypeId, ViewName, DisplayOrder, CreatedAt, UpdatedAt)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                view.getListId(),
                view.getCreatedBy(),
                view.getViewTypeId(),
                view.getViewName(),
                view.getDisplayOrder(),
                view.getCreatedAt(),
                view.getUpdatedAt()
        );
    }

    @Override
    public void update(ListView view) {
        String sql = """
            UPDATE ListView
            SET ViewTypeId = ?, ViewName = ?, DisplayOrder = ?, UpdatedAt = ?
            WHERE Id = ?
        """;
        jdbcTemplate.update(sql,
                view.getViewTypeId(),
                view.getViewName(),
                view.getDisplayOrder(),
                view.getUpdatedAt(),
                view.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM ListView WHERE Id = ?";
        jdbcTemplate.update(sql, id);
    }
}
