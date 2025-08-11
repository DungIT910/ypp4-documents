package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.KeySetting;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class KeySettingRowMapper extends BaseRowMapper<KeySetting> {

    @Override
    protected KeySetting mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        KeySetting keySetting = new KeySetting();
        keySetting.setId(rs.getInt("Id"));
        keySetting.setIcon(rs.getString("Icon"));
        keySetting.setKeyName(rs.getString("KeyName"));
        keySetting.setValueType(rs.getString("ValueType"));
        keySetting.setIsDefaultValue(rs.getBoolean("IsDefaultValue"));
        keySetting.setValueOfDefault(rs.getString("ValueOfDefault"));
        keySetting.setIsShareLinkSetting(rs.getBoolean("IsShareLinkSetting"));
        return keySetting;
    }
}
