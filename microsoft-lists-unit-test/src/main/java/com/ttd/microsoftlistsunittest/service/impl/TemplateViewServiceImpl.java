package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.TemplateView;
import com.ttd.microsoftlistsunittest.service.TemplateViewService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.TemplateViewRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateViewServiceImpl implements TemplateViewService {

    private final JdbcTemplate jdbcTemplate;
    private final TemplateViewRowMapper rowMapper;

    @Override
    public List<TemplateView> findAll() {
        String sql = "SELECT * FROM TemplateView";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<TemplateView> findById(Integer id) {
        String sql = "SELECT * FROM TemplateView WHERE Id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    @Override
    public List<TemplateView> findByListTemplateId(Integer listTemplateId) {
        String sql = "SELECT * FROM TemplateView WHERE ListTemplateId = ?";
        return jdbcTemplate.query(sql, rowMapper, listTemplateId);
    }

    @Override
    public int save(TemplateView view) {
        validate(view);
        String sql = """
                    INSERT INTO TemplateView (ListTemplateId, ViewTypeId, ViewName, DisplayOrder)
                    VALUES (?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                view.getListTemplateId(),
                view.getViewTypeId(),
                view.getViewName(),
                view.getDisplayOrder()
        );
    }

    @Override
    public int update(TemplateView view) {
        if (view.getId() == null || view.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or invalid");
        }
        validate(view);
        String sql = """
                    UPDATE TemplateView 
                    SET ListTemplateId = ?, ViewTypeId = ?, ViewName = ?, DisplayOrder = ?
                    WHERE Id = ?
                """;
        return jdbcTemplate.update(sql,
                view.getListTemplateId(),
                view.getViewTypeId(),
                view.getViewName(),
                view.getDisplayOrder(),
                view.getId()
        );
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM TemplateView WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private void validate(TemplateView view) {
        if (view.getListTemplateId() == null || view.getViewTypeId() == null) {
            throw new IllegalArgumentException("ListTemplateId and ViewTypeId cannot be null");
        }
        if (view.getViewName() == null || view.getViewName().trim().isEmpty()) {
            throw new IllegalArgumentException("ViewName cannot be empty");
        }
    }
}
