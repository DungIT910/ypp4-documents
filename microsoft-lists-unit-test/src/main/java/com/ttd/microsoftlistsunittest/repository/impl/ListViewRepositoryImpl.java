package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;
import com.ttd.microsoftlistsunittest.repository.ListViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ListViewRepositoryImpl implements ListViewRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ListViewDto> getListViewsByListId(Integer listId) {
        String sql = """
                SELECT
                    lv.Id AS listViewId,
                    lv.ViewName AS viewName,
                    vt.Icon AS listViewIcon,
                    lv.DisplayOrder AS displayOrder
                FROM
                    ListView lv
                INNER JOIN
                    ViewType vt ON lv.ViewTypeId = vt.Id
                WHERE
                    lv.ListId = ?
                ORDER BY
                    lv.DisplayOrder ASC;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListViewDto.class), listId);
    }
}
