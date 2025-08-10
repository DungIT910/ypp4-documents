package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ViewType;
import com.ttd.microsoftlistsunittest.service.ViewTypeService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ViewTypeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewTypeServiceImpl implements ViewTypeService {

    private final JdbcTemplate jdbcTemplate;
    private final ViewTypeRowMapper rowMapper;

    @Override
    public List<ViewType> findAll() {
        String sql = "SELECT * FROM ViewType";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ViewType> findById(Integer id) {
        String sql = "SELECT * FROM ViewType WHERE Id = ?";
        List<ViewType> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public int save(ViewType viewType) {
        String sql = """
            INSERT INTO ViewType (Title, HeaderImage, Icon, ViewTypeDescription)
            VALUES (?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                viewType.getTitle(),
                viewType.getHeaderImage(),
                viewType.getIcon(),
                viewType.getViewTypeDescription());
    }

    @Override
    public int update(ViewType viewType) {
        String sql = """
            UPDATE ViewType SET Title = ?, HeaderImage = ?, Icon = ?, ViewTypeDescription = ?
            WHERE Id = ?
        """;
        return jdbcTemplate.update(sql,
                viewType.getTitle(),
                viewType.getHeaderImage(),
                viewType.getIcon(),
                viewType.getViewTypeDescription(),
                viewType.getId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM ViewType WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
