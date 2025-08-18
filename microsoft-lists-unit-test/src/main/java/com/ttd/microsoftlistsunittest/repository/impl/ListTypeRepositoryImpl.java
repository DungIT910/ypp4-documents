package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.projection.listtype.ListTypeProjection;
import com.ttd.microsoftlistsunittest.repository.ListTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ListTypeRepositoryImpl implements ListTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ListTypeProjection> findAllListTypes() {
        String sql = """
                SELECT
                    lt.Id AS listTypeId,
                    lt.Title AS title,
                    lt.Icon AS icon,
                    lt.ListTypeDescription AS listTypeDescription,
                    lt.HeaderImage AS headerImage
                FROM
                    ListType lt
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListTypeProjection.class));
    }
}
