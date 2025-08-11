package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.KeySetting;
import com.ttd.microsoftlistsunittest.service.KeySettingService;
import com.ttd.microsoftlistsunittest.service.rowmapper.KeySettingRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeySettingServiceImpl implements KeySettingService {

    private final JdbcTemplate jdbcTemplate;
    private final KeySettingRowMapper rowMapper;

    @Override
    public List<KeySetting> findAll() {
        String sql = "SELECT * FROM KeySetting";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving key settings", e);
        }
    }

    @Override
    public Optional<KeySetting> findById(Integer id) {
        String sql = "SELECT * FROM KeySetting WHERE Id = ?";
        try {
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving key setting with ID: " + id, e);
        }
    }

    @Override
    public int save(KeySetting keySetting) {
        validate(keySetting);
        String sql = """
                    INSERT INTO KeySetting (Icon, KeyName, ValueType, IsDefaultValue, ValueOfDefault, IsShareLinkSetting)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            return jdbcTemplate.update(sql,
                    keySetting.getIcon(),
                    keySetting.getKeyName(),
                    keySetting.getValueType(),
                    keySetting.getIsDefaultValue(),
                    keySetting.getValueOfDefault(),
                    keySetting.getIsShareLinkSetting()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving key setting", e);
        }
    }

    @Override
    public int update(KeySetting keySetting) {
        if (keySetting.getId() == null || keySetting.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        validate(keySetting);
        String sql = """
                    UPDATE KeySetting 
                    SET Icon = ?, KeyName = ?, ValueType = ?, IsDefaultValue = ?, ValueOfDefault = ?, IsShareLinkSetting = ?
                    WHERE Id = ?
                """;
        try {
            return jdbcTemplate.update(sql,
                    keySetting.getIcon(),
                    keySetting.getKeyName(),
                    keySetting.getValueType(),
                    keySetting.getIsDefaultValue(),
                    keySetting.getValueOfDefault(),
                    keySetting.getIsShareLinkSetting(),
                    keySetting.getId()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating key setting", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = "DELETE FROM KeySetting WHERE Id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting key setting", e);
        }
    }

    private void validate(KeySetting keySetting) {
        if (keySetting == null) {
            throw new IllegalArgumentException("KeySetting cannot be null");
        }
        if (keySetting.getKeyName() == null || keySetting.getKeyName().trim().isEmpty()) {
            throw new IllegalArgumentException("KeyName cannot be null or empty");
        }
        if (keySetting.getValueType() == null || keySetting.getValueType().trim().isEmpty()) {
            throw new IllegalArgumentException("ValueType cannot be null or empty");
        }
    }
}
