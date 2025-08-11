package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.ViewType;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewTypeRowMapper extends BaseRowMapper<ViewType> {

    @Override
    protected ViewType mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ViewType viewType = new ViewType();
        viewType.setId(rs.getInt("Id"));
        viewType.setTitle(rs.getString("Title"));
        viewType.setHeaderImage(rs.getString("HeaderImage"));
        viewType.setIcon(rs.getString("Icon"));
        viewType.setViewTypeDescription(rs.getString("ViewTypeDescription"));
        return viewType;
    }
}
