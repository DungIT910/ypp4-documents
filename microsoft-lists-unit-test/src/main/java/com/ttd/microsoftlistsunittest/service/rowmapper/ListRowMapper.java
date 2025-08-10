package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ListRowMapper implements RowMapper<ListEntity> {
    @Override
    public ListEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListEntity list = new ListEntity();

        list.setId(rs.getInt("Id"));
        list.setListName(rs.getString("ListName"));
        list.setIcon(rs.getString("Icon"));
        list.setColor(rs.getString("Color"));
        list.setWorkspaceId(rs.getInt("WorkspaceId"));
        list.setCreatedBy(rs.getInt("CreatedBy"));
        list.setCreatedAt(rs.getObject("CreatedAt", LocalDateTime.class));
        list.setUpdatedAt(rs.getObject("UpdatedAt", LocalDateTime.class));
        list.setListStatus(rs.getString("ListStatus"));

        return list;
    }
}
