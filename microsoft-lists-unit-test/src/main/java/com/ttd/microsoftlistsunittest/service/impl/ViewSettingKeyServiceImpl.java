package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ViewSettingKey;
import com.ttd.microsoftlistsunittest.service.ViewSettingKeyService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ViewSettingKeyRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewSettingKeyServiceImpl implements ViewSettingKeyService {

    private final JdbcTemplate jdbcTemplate;
    private final ViewSettingKeyRowMapper rowMapper;

    @Override
    public List<ViewSettingKey> findAll() {
        String sql = "SELECT * FROM ViewSettingKey";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ViewSettingKey> findById(Integer id) {
        String sql = "SELECT * FROM ViewSettingKey WHERE Id = ?";
        List<ViewSettingKey> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public int save(ViewSettingKey key) {
        String sql = "INSERT INTO ViewSettingKey (SettingKey, ValueType) VALUES (?, ?)";
        return jdbcTemplate.update(sql, key.getSettingKey(), key.getValueType());
    }

    @Override
    public int update(ViewSettingKey key) {
        String sql = "UPDATE ViewSettingKey SET SettingKey = ?, ValueType = ? WHERE Id = ?";
        return jdbcTemplate.update(sql, key.getSettingKey(), key.getValueType(), key.getId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM ViewSettingKey WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
