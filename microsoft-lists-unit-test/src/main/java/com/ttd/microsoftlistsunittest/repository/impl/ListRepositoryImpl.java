package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.domain.model.ListStatus;
import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.repository.sql.ListSqlFragment;
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
    public Optional<ListSummaryDto> findListSummaryByListIdAndAccountId(Integer listId, Integer accountId) {
        String sql = String.format("""
                SELECT
                    %s
                FROM
                    List l
                INNER JOIN
                    Workspace w ON l.WorkspaceId = w.Id
                LEFT JOIN
                    FavoriteList fl ON l.Id = fl.ListId AND fl.AccountId = ?
                WHERE
                    l.Id = ?
                """, ListSqlFragment.SELECT_LIST_SUMMARY);

        ListSummaryDto result = jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(ListSummaryDto.class),
                accountId, listId);
        return Optional.ofNullable(result);
    }

    @Override
    public List<ListSummaryDto> findAllPersonalListsByAccountId(Integer accountId) {
        String sql = String.format("""
                SELECT
                    %s
                FROM
                    List AS l
                INNER JOIN
                    Workspace w ON w.Id = l.WorkspaceId
                INNER JOIN
                    WorkspaceMember wmb ON w.Id = wmb.WorkspaceId
                LEFT JOIN
                    FavoriteList fl ON fl.ListId = l.Id AND fl.AccountId = ?
                WHERE
                    w.IsPersonal = TRUE
                    AND wmb.AccountId = ?
                    AND l.ListStatus = ?
                ORDER BY
                    l.UpdatedAt DESC
                """, ListSqlFragment.SELECT_LIST_SUMMARY);

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(ListSummaryDto.class),
                accountId,
                accountId,
                ListStatus.ACTIVE.name());
    }

    @Override
    public List<ListSummaryDto> findAllFavoriteListsByAccountId(Integer accountId) {
        String sql = String.format("""
                SELECT
                    %s
                FROM
                    List l
                INNER JOIN
                    FavoriteList fl ON l.Id = fl.ListId
                INNER JOIN
                    Workspace w ON l.WorkspaceId = w.Id
                WHERE
                    fl.AccountId = ?
                    AND l.ListStatus = ?
                ORDER BY
                    l.UpdatedAt DESC
                """, ListSqlFragment.SELECT_LIST_SUMMARY);

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(ListSummaryDto.class),
                accountId,
                ListStatus.ACTIVE.name());
    }

    @Override
    public List<ListSummaryDto> findAllRecentListsByAccountId(Integer accountId) {
        String sql = String.format("""
                SELECT
                    %s,
                    rl.AccessedAt AS accessedAt
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
                    AND l.ListStatus = ?
                ORDER BY
                    rl.AccessedAt DESC
                """, ListSqlFragment.SELECT_LIST_SUMMARY);

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(ListSummaryDto.class),
                accountId,
                ListStatus.ACTIVE.name());
    }
}