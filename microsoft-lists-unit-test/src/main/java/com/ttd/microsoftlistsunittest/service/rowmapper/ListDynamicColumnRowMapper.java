package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ListDynamicColumn;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ListDynamicColumnRowMapper extends BaseRowMapper<ListDynamicColumn> {
    @Override
    protected ListDynamicColumn mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ListDynamicColumn column = new ListDynamicColumn();

        column.setId(rs.getInt("Id"));
        column.setListId(rs.getInt("ListId"));
        column.setSystemDataTypeId(rs.getInt("SystemDataTypeId"));

        int systemColumnId = rs.getInt("SystemColumnId");
        column.setSystemColumnId(rs.wasNull() ? null : systemColumnId);

        column.setColumnName(rs.getString("ColumnName"));
        column.setColumnDescription(rs.getString("ColumnDescription"));
        column.setDisplayOrder(rs.getInt("DisplayOrder"));
        column.setIsSystemColumn(rs.getBoolean("IsSystemColumn"));
        column.setIsVisible(rs.getBoolean("IsVisible"));
        column.setCreatedBy(rs.getInt("CreatedBy"));
        column.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
        column.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());

        return column;
    }
}
