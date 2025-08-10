package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.TemplateColumn;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TemplateColumnRowMapper extends BaseRowMapper<TemplateColumn> {
    @Override
    protected TemplateColumn mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        TemplateColumn column = new TemplateColumn();
        column.setId(rs.getInt("Id"));
        column.setSystemDataTypeId(rs.getInt("SystemDataTypeId"));
        column.setListTemplateId(rs.getInt("ListTemplateId"));
        column.setColumnName(rs.getString("ColumnName"));
        column.setColumnDescription(rs.getString("ColumnDescription"));
        column.setDisplayOrder(rs.getInt("DisplayOrder"));
        column.setIsVisible(rs.getBoolean("IsVisible"));
        return column;
    }
}
