package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.SystemDataType;
import com.ttd.microsoftlistsunittest.service.SystemDataTypeService;
import com.ttd.microsoftlistsunittest.service.rowmapper.SystemDataTypeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemDataTypeServiceImpl implements SystemDataTypeService {
    private final JdbcTemplate jdbcTemplate;
    private final SystemDataTypeRowMapper rowMapper;

    @Override
    public List<SystemDataType> findAll() {
        String sql = "SELECT * FROM SystemDataType";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving system data types", e);
        }
    }

    @Override
    public Optional<SystemDataType> findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }
        String sql = "SELECT * FROM SystemDataType WHERE Id = ?";
        try {
            List<SystemDataType> results = jdbcTemplate.query(sql, rowMapper, id);
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving system data type with ID: " + id, e);
        }
    }

    @Override
    public int save(SystemDataType dataType) {
        validate(dataType);
        String sql = """
                INSERT INTO SystemDataType (Icon, DataTypeDescription, CoverImg, DisplayName, DataTypeValue)
                VALUES (?, ?, ?, ?, ?)
            """;
        try {
            return jdbcTemplate.update(sql,
                    dataType.getIcon(),
                    dataType.getDataTypeDescription(),
                    dataType.getCoverImg(),
                    dataType.getDisplayName(),
                    dataType.getDataTypeValue());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving system data type", e);
        }
    }

    @Override
    public int update(SystemDataType dataType) {
        if (dataType.getId() == null || dataType.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        validate(dataType);
        String sql = """
                UPDATE SystemDataType 
                SET Icon = ?, DataTypeDescription = ?, CoverImg = ?, DisplayName = ?, DataTypeValue = ?
                WHERE Id = ?
            """;
        try {
            return jdbcTemplate.update(sql,
                    dataType.getIcon(),
                    dataType.getDataTypeDescription(),
                    dataType.getCoverImg(),
                    dataType.getDisplayName(),
                    dataType.getDataTypeValue(),
                    dataType.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating system data type", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }
        String sql = "DELETE FROM SystemDataType WHERE Id = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            if (rows == 0) {
                throw new RuntimeException("No SystemDataType found with ID: " + id);
            }
            return rows;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting system data type", e);
        }
    }

    private void validate(SystemDataType dataType) {
        if (dataType == null) {
            throw new IllegalArgumentException("SystemDataType cannot be null");
        }
        if (dataType.getDisplayName() == null || dataType.getDisplayName().trim().isEmpty()) {
            throw new IllegalArgumentException("DisplayName cannot be null or empty");
        }
        if (dataType.getDataTypeValue() == null || dataType.getDataTypeValue().trim().isEmpty()) {
            throw new IllegalArgumentException("DataTypeValue cannot be null or empty");
        }
    }
}
