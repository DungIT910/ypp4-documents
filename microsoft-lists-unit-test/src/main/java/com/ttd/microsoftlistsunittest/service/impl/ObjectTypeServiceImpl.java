package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ObjectType;
import com.ttd.microsoftlistsunittest.service.ObjectTypeService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.ObjectTypeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectTypeServiceImpl implements ObjectTypeService {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectTypeRowMapper rowMapper;

    @Override
    public List<ObjectType> findAll() {
        String sql = "SELECT * FROM ObjectType";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ObjectType> findById(Integer id) {
        String sql = "SELECT * FROM ObjectType WHERE Id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    @Override
    public int save(ObjectType objectType) {
        validate(objectType);
        String sql = """
                    INSERT INTO ObjectType (Code, DisplayName, Icon)
                    VALUES (?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                objectType.getCode(),
                objectType.getDisplayName(),
                objectType.getIcon());
    }

    @Override
    public int update(ObjectType objectType) {
        if (objectType.getId() == null || objectType.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        validate(objectType);
        String sql = """
                    UPDATE ObjectType
                    SET Code = ?, DisplayName = ?, Icon = ?
                    WHERE Id = ?
                """;
        return jdbcTemplate.update(sql,
                objectType.getCode(),
                objectType.getDisplayName(),
                objectType.getIcon(),
                objectType.getId());
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = "DELETE FROM ObjectType WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private void validate(ObjectType objectType) {
        if (objectType == null) {
            throw new IllegalArgumentException("ObjectType cannot be null");
        }
        if (objectType.getCode() == null || objectType.getCode().isEmpty()) {
            throw new IllegalArgumentException("Code cannot be empty");
        }
        if (objectType.getDisplayName() == null || objectType.getDisplayName().isEmpty()) {
            throw new IllegalArgumentException("DisplayName cannot be empty");
        }
    }
}
