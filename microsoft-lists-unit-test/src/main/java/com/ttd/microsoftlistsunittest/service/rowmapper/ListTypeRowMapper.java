package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ListType;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ListTypeRowMapper extends BaseRowMapper<ListType> {

    @Override
    protected ListType mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ListType listType = new ListType();
        listType.setId(rs.getInt("Id"));
        listType.setTitle(rs.getString("Title"));
        listType.setIcon(rs.getString("Icon"));
        listType.setListTypeDescription(rs.getString("ListTypeDescription"));
        listType.setHeaderImage(rs.getString("HeaderImage"));
        return listType;
    }
}
