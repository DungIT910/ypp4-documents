package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.rowmapper.domain.ListDisplayDtoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListRepositoryImpl implements ListRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ListDisplayDtoRowMapper listDisplayDtoRowMapper;
    private final com.ttd.microsoftlistsunittest.rowmapper.dto.ListDisplayDtoRowMapper listDisplayDtoRowMapper;

    @Override
    public Optional<ListDisplayDto> findById(Integer id) {
        String sql = "SELECT l.Id, l.Color, l.Icon, l.ListName FROM List l WHERE Id = ?";
        List<ListDisplayDto> results = jdbcTemplate.query(sql, listDisplayDtoRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public List<ListDisplayDto> findAllByAccountId(Integer accountId) {
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
        return jdbcTemplate.query(sql, listDisplayDtoRowMapper, accountId);
    }

    @Override
    public List<ListDisplayDto> findAllFavoriteListsByAccountId(Integer accountId) {
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
        return jdbcTemplate.query(sql, listDisplayDtoRowMapper, accountId);
    }

    @Override
    public List<ListDisplayDto> findAllRecentListsByAccountId(Integer accountId) {
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
        return jdbcTemplate.query(sql, listDisplayDtoRowMapper, accountId);
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
