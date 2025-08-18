package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.projection.list.ListSummaryProjection;
import com.ttd.microsoftlistsunittest.projection.list.RecentListSummaryProjection;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ListRepositoryImpl implements ListRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<ListSummaryProjection> findListSummaryByListIdAndAccountId(Integer listId, Integer accountId) {
        String sql = """
                SELECT
                    l.Id AS listId,
                    l.ListName AS listName,
                    l.Icon AS icon,
                    l.Color AS color,
                    w.WorkspaceName AS workspaceName,
                    CASE
                        WHEN fl.Id IS NOT NULL THEN TRUE
                        ELSE FALSE
                    END AS isFavorite
                FROM
                    List l
                INNER JOIN
                    Workspace w ON l.WorkspaceId = w.Id
                LEFT JOIN
                    FavoriteList fl ON l.Id = fl.ListId AND fl.AccountId = ?
                WHERE
                        l.Id = ?
                """;
        ListSummaryProjection result = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ListSummaryProjection.class), accountId, listId);
        return Optional.ofNullable(result);
    }

    @Override
    public List<ListSummaryProjection> findAllPersonalListsByAccountId(Integer accountId) {
        String sql = """
                SELECT
                    l.Id,
                    l.Color,
                    l.Icon,
                    l.ListName,
                    wsp.WorkspaceName,
                    CASE
                        WHEN fl.Id IS NOT NULL THEN TRUE
                        ELSE FALSE
                    END AS isFavorite
                FROM
                    List AS l
                INNER JOIN
                    Workspace wsp ON wsp.Id = l.WorkspaceId
                INNER JOIN
                    WorkspaceMember wmb ON wsp.Id = wmb.WorkspaceId
                LEFT JOIN
                    FavoriteList fl ON fl.ListId = l.Id AND fl.AccountId = ?
                WHERE
                    wsp.IsPersonal = TRUE
                    AND wmb.AccountId = ?
                    AND l.ListStatus = 'ACTIVE'
                ORDER BY
                    l.UpdatedAt DESC
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListSummaryProjection.class), accountId, accountId);
    }

    @Override
    public List<ListSummaryProjection> findAllFavoriteListsByAccountId(Integer accountId) {
        String sql = """
                SELECT
                    l.Id AS listId,
                    l.Color AS color,
                    l.Icon AS icon,
                    l.ListName AS listName,
                    w.WorkspaceName AS workspaceName,
                    TRUE AS isFavorite
                FROM
                    List l
                INNER JOIN
                    FavoriteList fl ON l.Id = fl.ListId
                INNER JOIN
                    Workspace w ON l.WorkspaceId = w.Id
                WHERE
                    fl.AccountId = ?
                    AND l.ListStatus = 'ACTIVE'
                ORDER BY
                    l.UpdatedAt DESC
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListSummaryProjection.class), accountId);
    }


    @Override
    public List<RecentListSummaryProjection> findAllRecentListsByAccountId(Integer accountId) {
        String sql = """
                SELECT
                    l.Id AS listId,
                    l.Color AS color,
                    l.Icon AS icon,
                    l.ListName AS listName,
                    w.WorkspaceName AS workspaceName,
                    rl.AccessedAt AS accessedAt,
                    CASE
                        WHEN fl.Id IS NOT NULL THEN TRUE
                        ELSE FALSE
                    END AS isFavorite
                FROM
                    List l
                INNER JOIN
                    RecentList rl ON l.Id = rl.ListId
                INNER JOIN
                    Workspace w ON l.WorkspaceId = w.Id
                LEFT JOIN
                    FavoriteList fl ON fl.ListId = l.Id AND fl.AccountId = rl.AccountId
                WHERE
                    rl.AccountId = ?
                    AND l.ListStatus = 'ACTIVE'
                ORDER BY
                    rl.AccessedAt DESC
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RecentListSummaryProjection.class), accountId);
    }
}
