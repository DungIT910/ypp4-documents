package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;
import com.ttd.microsoftlistsunittest.repository.ListTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ListTemplateRepositoryImpl implements ListTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ListTemplateSummaryDto> findALlTemplatesByTemplateProviderId(Integer templateProviderId) {
        String sql = """
                SELECT
                    lt.Id as listTemplateId,
                    lt.Title as title,
                    lt.TemplateDescription as templateDescription,
                    lt.HeaderImage as headerImage
                FROM
                    ListTemplate lt
                WHERE
                    lt.ProviderId = ?;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListTemplateSummaryDto.class), templateProviderId);
    }
}
