package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.list.ListDataDto;
import com.ttd.microsoftlistsunittest.repository.ListDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ListDataRepositoryImpl implements ListDataRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ListDataDto> getListDataByListId(Integer listId) {
        String sql = """
                SELECT
                    lc.Id AS ColumnId,
                    sdt.Icon AS ColumnIcon,
                    lc.ColumnName AS ColumnName,
                    lr.Id AS RowId,
                    lcvl.CellValue AS CellValue
                FROM
                    ListRow lr
                INNER JOIN
                    ListCellValue lcvl ON lcvl.ListRowId = lr.Id
                INNER JOIN
                    ListDynamicColumn lc ON lc.Id = lcvl.ListColumnId AND lc.ListId = lr.ListId
                INNER JOIN
                    SystemDataType sdt ON sdt.Id = lc.SystemDataTypeId
                WHERE
                    lc.ListId = ?
                ORDER BY
                    lc.DisplayOrder ASC,
                    lr.DisplayOrder ASC;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListDataDto.class), listId);
    }
}
