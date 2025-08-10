package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ViewSettingKey;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewSettingKeyRowMapper implements RowMapper<ViewSettingKey> {
    @Override
    public ViewSettingKey mapRow(ResultSet rs, int rowNum) throws SQLException {
        ViewSettingKey key = new ViewSettingKey();
        key.setId(rs.getInt("Id"));
        key.setSettingKey(rs.getString("SettingKey"));
        key.setValueType(rs.getString("ValueType"));
        return key;
    }
}
