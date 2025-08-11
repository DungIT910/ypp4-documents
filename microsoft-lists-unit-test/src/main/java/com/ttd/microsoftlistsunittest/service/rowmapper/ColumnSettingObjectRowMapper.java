package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ColumnSettingObject;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ColumnSettingObjectRowMapper extends BaseRowMapper<ColumnSettingObject> {
    @Override
    protected ColumnSettingObject mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ColumnSettingObject obj = new ColumnSettingObject();
        obj.setId(rs.getInt("Id"));
        obj.setColumnId(rs.getInt("ColumnId"));
        obj.setDisplayName(rs.getString("DisplayName"));
        obj.setDisplayColor(rs.getString("DisplayColor"));
        obj.setDisplayOrder(rs.getInt("DisplayOrder"));
        obj.setContext(rs.getString("Context"));
        obj.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
        obj.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
        return obj;
    }
}
