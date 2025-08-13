package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.list.FavoriteListDto;
import com.ttd.microsoftlistsunittest.dto.list.ListDetailDto;
import com.ttd.microsoftlistsunittest.dto.list.MyListDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListDto;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.rowmapper.list.FavoriteListDtoRowMapper;
import com.ttd.microsoftlistsunittest.rowmapper.list.ListDetailDtoRowMapper;
import com.ttd.microsoftlistsunittest.rowmapper.list.MyListDtoRowMapper;
import com.ttd.microsoftlistsunittest.rowmapper.list.RecentListDtoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListRepositoryImpl implements ListRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RecentListDtoRowMapper recentListDtoRowMapper;
    private final MyListDtoRowMapper myListDtoRowMapper;
    private final FavoriteListDtoRowMapper favoriteListDtoRowMapper;
    private final ListDetailDtoRowMapper listDetailDtoRowMapper;

    @Override
    public Optional<ListDetailDto> findListDetailByListIdAndAccountId(Integer listId, Integer accountId) {
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
                LIMIT 1
                """;
        ListDetailDto result = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ListDetailDto.class), accountId, listId);
        return Optional.ofNullable(result);
    }

    @Override
    public List<MyListDto> findAllByAccountId(Integer accountId) {
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
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MyListDto.class), accountId);
    }

    @Override
    public List<FavoriteListDto> findAllFavoriteListsByAccountId(Integer accountId) {
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
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FavoriteListDto.class), accountId);
    }

    @Override
    public List<RecentListDto> findAllRecentListsByAccountId(Integer accountId) {
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
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RecentListDto.class), accountId);
    }
}
