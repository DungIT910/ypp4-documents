package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.service.ListService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ListRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final JdbcTemplate jdbcTemplate;
    private final ListRowMapper listRowMapper;

    @Override
    public List<ListEntity> findAll() {
        String sql = "SELECT * FROM List";
        return jdbcTemplate.query(sql, listRowMapper);
    }

    @Override
    public Optional<ListEntity> findById(Integer id) {
        String sql = "SELECT * FROM List WHERE Id = ?";
        List<ListEntity> results = jdbcTemplate.query(sql, listRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public int save(ListEntity list) {
        String sql = """
                    INSERT INTO List (ListName, Icon, Color, WorkspaceId, CreatedBy, CreatedAt, UpdatedAt, ListStatus)
                    VALUES (?, ?, ?, ?, ?, GETDATE(), GETDATE(), ?)
                """;
        return jdbcTemplate.update(sql,
                list.getListName(),
                list.getIcon(),
                list.getColor(),
                list.getWorkspaceId(),
                list.getCreatedBy(),
                list.getListStatus()
        );
    }

    @Override
    public int update(ListEntity list) {
        String sql = """
                    UPDATE List SET
                        ListName = ?,
                        Icon = ?,
                        Color = ?,
                        WorkspaceId = ?,
                        UpdatedAt = GETDATE(),
                        ListStatus = ?
                    WHERE Id = ?
                """;
        return jdbcTemplate.update(sql,
                list.getListName(),
                list.getIcon(),
                list.getColor(),
                list.getWorkspaceId(),
                list.getListStatus(),
                list.getId()
        );
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM List WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
