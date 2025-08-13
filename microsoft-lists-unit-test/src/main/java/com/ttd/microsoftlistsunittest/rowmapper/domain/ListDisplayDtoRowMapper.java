package com.ttd.microsoftlistsunittest.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ListDisplayDtoRowMapper implements RowMapper<ListDisplayDto> {
    @Override
    public ListDisplayDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListDisplayDto listDisplayDto = new ListDisplayDto();

        listDisplayDto.setId(rs.getInt("Id"));
        listDisplayDto.setListName(rs.getString("ListName"));
        listDisplayDto.setIcon(rs.getString("Icon"));
        listDisplayDto.setColor(rs.getString("Color"));
        listDisplayDto.setWorkspaceId(rs.getInt("WorkspaceId"));
        listDisplayDto.setCreatedBy(rs.getInt("CreatedBy"));

        return listDisplayDto;
    }
}
