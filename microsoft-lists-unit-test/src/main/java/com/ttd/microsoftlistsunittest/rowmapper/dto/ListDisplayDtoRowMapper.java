package com.ttd.microsoftlistsunittest.rowmapper.dto;

import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;
import com.ttd.microsoftlistsunittest.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ListDisplayDtoRowMapper extends BaseRowMapper<ListDisplayDto> {

    @Override
    protected ListDisplayDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ListDisplayDto dto = new ListDisplayDto();

        dto.setId(rs.getInt("Id"));
        dto.setColor(rs.getString("Color"));
        dto.setIcon(rs.getString("Icon"));
        dto.setListName(rs.getString("ListName"));

        return dto;
    }
}
