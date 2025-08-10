package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListTemplate;
import com.ttd.microsoftlistsunittest.service.ListTemplateService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ListTemplateRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListTemplateServiceImpl implements ListTemplateService {

    private final JdbcTemplate jdbcTemplate;
    private final ListTemplateRowMapper listTemplateRowMapper;

    @Override
    public List<ListTemplate> findAll() {
        String sql = "SELECT * FROM ListTemplate";
        try {
            return jdbcTemplate.query(sql, listTemplateRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving list templates", e);
        }
    }

    @Override
    public Optional<ListTemplate> findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }

        String sql = "SELECT * FROM ListTemplate WHERE Id = ?";
        try {
            List<ListTemplate> results = jdbcTemplate.query(sql, listTemplateRowMapper, id);
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error finding template by ID: " + id, e);
        }
    }

    @Override
    public List<ListTemplate> findAllByProviderId(Integer providerId) {
        if (providerId == null || providerId <= 0) {
            throw new IllegalArgumentException("ProviderId must be a positive integer");
        }

        String sql = "SELECT * FROM ListTemplate WHERE ProviderId = ?";
        try {
            return jdbcTemplate.query(sql, listTemplateRowMapper, providerId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving templates by providerId: " + providerId, e);
        }
    }

    @Override
    public int save(ListTemplate template) {
        validateTemplate(template);

        String sql = """
                INSERT INTO ListTemplate 
                (Title, HeaderImage, TemplateDescription, Icon, Color, Summary, Feature, ProviderId)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try {
            return jdbcTemplate.update(sql,
                    template.getTitle(),
                    template.getHeaderImage(),
                    template.getTemplateDescription(),
                    template.getIcon(),
                    template.getColor(),
                    template.getSummary(),
                    template.getFeature(),
                    template.getProviderId()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving list template", e);
        }
    }

    @Override
    public int update(ListTemplate template) {
        if (template.getId() == null || template.getId() <= 0) {
            throw new IllegalArgumentException("Template ID cannot be null or negative for update");
        }

        validateTemplate(template);

        String sql = """
                UPDATE ListTemplate
                SET Title = ?, HeaderImage = ?, TemplateDescription = ?, Icon = ?, 
                    Color = ?, Summary = ?, Feature = ?, ProviderId = ?
                WHERE Id = ?
            """;

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    template.getTitle(),
                    template.getHeaderImage(),
                    template.getTemplateDescription(),
                    template.getIcon(),
                    template.getColor(),
                    template.getSummary(),
                    template.getFeature(),
                    template.getProviderId(),
                    template.getId()
            );

            if (rowsAffected == 0) {
                throw new RuntimeException("No list template found with ID: " + template.getId());
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating list template", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }

        String sql = "DELETE FROM ListTemplate WHERE Id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);

            if (rowsAffected == 0) {
                throw new RuntimeException("No list template found with ID: " + id);
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting list template", e);
        }
    }

    private void validateTemplate(ListTemplate template) {
        if (template == null) {
            throw new IllegalArgumentException("ListTemplate cannot be null");
        }

        if (template.getTitle() == null || template.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Template title cannot be null or empty");
        }

        if (template.getProviderId() == null || template.getProviderId() <= 0) {
            throw new IllegalArgumentException("ProviderId is required and must be positive");
        }
    }
}
