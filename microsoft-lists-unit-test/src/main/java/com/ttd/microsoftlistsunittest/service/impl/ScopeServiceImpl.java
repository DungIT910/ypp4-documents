package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Scope;
import com.ttd.microsoftlistsunittest.service.ScopeService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.ScopeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScopeServiceImpl implements ScopeService {

    private final JdbcTemplate jdbcTemplate;
    private final ScopeRowMapper rowMapper = new ScopeRowMapper();

    @Override
    public Scope create(Scope scope) {
        String sql = "INSERT INTO Scope (Code, DisplayName, ScopeDescription, Icon) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                scope.getCode(),
                scope.getDisplayName(),
                scope.getScopeDescription(),
                scope.getIcon()
        );
        return scope;
    }

    @Override
    public Optional<Scope> findById(int id) {
        try {
            String sql = "SELECT * FROM Scope WHERE Id = ?";
            Scope scope = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(scope);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Scope> findAll() {
        String sql = "SELECT * FROM Scope";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public boolean update(Scope scope) {
        String sql = "UPDATE Scope SET Code = ?, DisplayName = ?, ScopeDescription = ?, Icon = ? WHERE Id = ?";
        int rows = jdbcTemplate.update(sql,
                scope.getCode(),
                scope.getDisplayName(),
                scope.getScopeDescription(),
                scope.getIcon(),
                scope.getId()
        );
        return rows > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Scope WHERE Id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}