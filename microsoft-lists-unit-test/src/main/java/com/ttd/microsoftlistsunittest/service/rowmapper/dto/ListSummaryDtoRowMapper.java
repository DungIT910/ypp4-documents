package com.ttd.microsoftlistsunittest.service.rowmapper.dto;

import com.ttd.microsoftlistsunittest.dto.ListSummaryDto;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ListSummaryDtoRowMapper extends BaseRowMapper<ListSummaryDto> {

    @Override
    protected ListSummaryDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ListSummaryDto dto = new ListSummaryDto();

        dto.setId(rs.getInt("Id"));
        dto.setColor(rs.getString("Color"));
        dto.setIcon(rs.getString("Icon"));
        dto.setListName(rs.getString("ListName"));

        return dto;
    }
}
