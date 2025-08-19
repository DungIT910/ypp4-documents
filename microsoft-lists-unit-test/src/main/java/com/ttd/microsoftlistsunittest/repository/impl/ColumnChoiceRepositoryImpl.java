package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import com.ttd.microsoftlistsunittest.dto.columnchoice.ColumnChoiceDto;
import com.ttd.microsoftlistsunittest.repository.ColumnChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ColumnChoiceRepositoryImpl implements ColumnChoiceRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ColumnChoiceDto> getColumnChoices(ColumnChoiceContext context, Integer columnId) {
        String sql = """
                 SELECT
                     clc.Id AS columnChoiceId,
                     clc.DisplayName,
                     clc.DisplayColor,
                     clc.DisplayOrder,
                     clc.Context
                 FROM
                     ColumnChoice clc
                 WHERE
                     clc.Context = ?
                     AND clc.ColumnId = ?
                 ORDER BY
                     clc.DisplayOrder
                """;

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(ColumnChoiceDto.class),
                context.name(),
                columnId
        );
    }
}
