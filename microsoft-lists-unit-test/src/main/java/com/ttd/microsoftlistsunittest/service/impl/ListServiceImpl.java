package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.ListSummaryDto;
import com.ttd.microsoftlistsunittest.service.ListService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.ListRowMapper;
import com.ttd.microsoftlistsunittest.service.rowmapper.dto.ListSummaryDtoRowMapper;
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
    private final ListSummaryDtoRowMapper listSummaryDtoRowMapper;

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

    // Used in Dashboard screen to show all active lists owned or shared with the user
    @Override
    public List<ListSummaryDto> findAllByAccountId(Integer accountId) {
        String sql = """
                SELECT
                     l.Id,
                     l.Color,
                     l.Icon,
                     l.ListName
                 FROM
                     List AS l
                 INNER JOIN
                     ListMemberPermission AS lmp ON l.Id = lmp.ListId
                 WHERE
                     lmp.AccountId = ?
                     AND l.ListStatus = 'active'
                 ORDER BY
                     l.UpdatedAt DESC 
                """;
        return jdbcTemplate.query(sql, listSummaryDtoRowMapper, accountId);
    }

    // Get all favorite lists of a user for dashboard display
    @Override
    public List<ListSummaryDto> findAllFavoriteListsByAccountId(Integer accountId) {
        String sql = """
                SELECT
                l.Id,
                        l.Color,
                        l.Icon,
                        l.ListName
                FROM
                    List AS l
                INNER JOIN
                    FavoriteList AS fl ON l.Id = fl.ListId
                WHERE
                    fl.AccountId = ?
                ORDER BY
                    l.UpdatedAt DESC;
                """;
        return jdbcTemplate.query(sql, listSummaryDtoRowMapper, accountId);
    }

    // Get all recent lists of a user for dashboard display
    @Override
    public List<ListSummaryDto> findAllRecentListsByAccountId(Integer accountId) {
        String sql = """
                SELECT
                l.Id,
                        l.Color,
                        l.Icon,
                        l.ListName
                FROM
                    List AS l
                INNER JOIN
                    RecentList AS rl ON l.Id = rl.ListId
                WHERE
                    rl.AccountId = ?
                ORDER BY
                    rl.AccessedAt DESC;
                """;
        return jdbcTemplate.query(sql, listSummaryDtoRowMapper, accountId);
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
