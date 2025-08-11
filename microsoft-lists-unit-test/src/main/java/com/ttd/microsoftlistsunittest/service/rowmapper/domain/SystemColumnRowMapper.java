package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.SystemColumn;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SystemColumnRowMapper extends BaseRowMapper<SystemColumn> {
    @Override
    protected SystemColumn mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        SystemColumn column = new SystemColumn();
        column.setId(rs.getInt("Id"));
        column.setSystemDataTypeId(rs.getInt("SystemDataTypeId"));
        column.setColumnName(rs.getString("ColumnName"));
        column.setDisplayOrder(rs.getInt("DisplayOrder"));
        column.setCreatedBy(rs.getInt("CreatedBy"));
        column.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
        column.setCanRename(rs.getBoolean("CanRename"));
        return column;
    }
}
