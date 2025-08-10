package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.TemplateProvider;
import com.ttd.microsoftlistsunittest.service.TemplateProviderService;
import com.ttd.microsoftlistsunittest.service.rowmapper.TemplateProviderRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateProviderServiceImpl implements TemplateProviderService {

    private final JdbcTemplate jdbcTemplate;
    private final TemplateProviderRowMapper templateProviderRowMapper;

    @Override
    public List<TemplateProvider> findAll() {
        String sql = "SELECT * FROM TemplateProvider";
        try {
            return jdbcTemplate.query(sql, templateProviderRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving template providers", e);
        }
    }

    @Override
    public Optional<TemplateProvider> findById(Integer id) {
        validateId(id);

        String sql = "SELECT * FROM TemplateProvider WHERE Id = ?";
        try {
            List<TemplateProvider> results = jdbcTemplate.query(sql, templateProviderRowMapper, id);
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error finding provider by ID: " + id, e);
        }
    }

    @Override
    public int save(TemplateProvider provider) {
        validateProvider(provider);

        String sql = """
            INSERT INTO TemplateProvider (providerName)
            VALUES (?)
        """;

        try {
            return jdbcTemplate.update(sql, provider.getProviderName());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving template provider", e);
        }
    }

    @Override
    public int update(TemplateProvider provider) {
        validateId(provider.getId());
        validateProvider(provider);

        String sql = """
            UPDATE TemplateProvider SET providerName = ? WHERE Id = ?
        """;

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    provider.getProviderName(),
                    provider.getId()
            );

            if (rowsAffected == 0) {
                throw new RuntimeException("No provider found with ID: " + provider.getId());
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating template provider", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        validateId(id);

        String sql = "DELETE FROM TemplateProvider WHERE Id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                throw new RuntimeException("No provider found with ID: " + id);
            }
            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting template provider", e);
        }
    }

    private void validateProvider(TemplateProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }

        if (provider.getProviderName() == null || provider.getProviderName().trim().isEmpty()) {
            throw new IllegalArgumentException("Provider name cannot be null or empty");
        }
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
    }
}
