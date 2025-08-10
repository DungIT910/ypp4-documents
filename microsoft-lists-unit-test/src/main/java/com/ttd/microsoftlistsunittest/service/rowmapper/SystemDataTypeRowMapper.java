package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.SystemDataType;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SystemDataTypeRowMapper extends BaseRowMapper<SystemDataType> {

    @Override
    protected SystemDataType mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        SystemDataType dataType = new SystemDataType();
        dataType.setId(rs.getInt("Id"));
        dataType.setIcon(rs.getString("Icon"));
        dataType.setDataTypeDescription(rs.getString("DataTypeDescription"));
        dataType.setCoverImg(rs.getString("CoverImg"));
        dataType.setDisplayName(rs.getString("DisplayName"));
        dataType.setDataTypeValue(rs.getString("DataTypeValue"));
        return dataType;
    }
}
