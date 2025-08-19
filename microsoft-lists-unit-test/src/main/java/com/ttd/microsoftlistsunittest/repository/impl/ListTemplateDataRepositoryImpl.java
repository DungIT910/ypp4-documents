package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;
import com.ttd.microsoftlistsunittest.repository.ListTemplateDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ListTemplateDataRepositoryImpl implements ListTemplateDataRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ListTemplateSampleDataDto> getListTemplateSampleData(Integer listTemplateId) {
        String sql = """
                SELECT
                    trow.Id AS rowId,
                    tcol.ColumnName,
                    tcol.Id AS columnId,
                    sdt.Icon AS columnIcon,
                    tcell.CellValue
                FROM
                    TemplateSampleRow trow
                INNER JOIN
                    TemplateColumn tcol ON tcol.ListTemplateId = trow.ListTemplateId
                INNER JOIN
                    SystemDataType sdt ON tcol.SystemDataTypeId = sdt.Id
                LEFT JOIN
                    TemplateSampleCell tcell
                        ON tcol.Id = tcell.TemplateColumnId
                        AND trow.Id = tcell.TemplateSampleRowId
                WHERE
                    trow.ListTemplateId = ?
                ORDER BY
                    trow.DisplayOrder ASC,
                    tcol.DisplayOrder ASC;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListTemplateSampleDataDto.class), listTemplateId);
    }
}
