package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ListView;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ListViewRowMapper extends BaseRowMapper<ListView> {
    @Override
    protected ListView mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ListView view = new ListView();

        view.setId(rs.getInt("Id"));
        view.setListId(rs.getInt("ListId"));
        view.setCreatedBy(rs.getInt("CreatedBy"));
        view.setViewTypeId(rs.getInt("ViewTypeId"));
        view.setViewName(rs.getString("ViewName"));
        view.setDisplayOrder(rs.getInt("DisplayOrder"));
        view.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
        view.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());

        return view;
    }
}
