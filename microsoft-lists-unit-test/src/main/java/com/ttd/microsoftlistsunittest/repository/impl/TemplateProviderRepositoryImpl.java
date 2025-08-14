package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.projection.templateprovider.TemplateProviderProjection;
import com.ttd.microsoftlistsunittest.repository.TemplateProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TemplateProviderRepositoryImpl implements TemplateProviderRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TemplateProviderProjection> findAllTemplateProviders() {
        String sql = """
                    SELECT
                        Id AS providerId,
                        ProviderName AS providerName
                    FROM
                        TemplateProvider
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TemplateProviderProjection.class));
    }
}
