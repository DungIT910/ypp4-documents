package com.ttd.microsoftlistsunittest.service.rowmapper.dto;

import com.ttd.microsoftlistsunittest.dto.ListTypeCardDto;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ListTypeCardDtoRowMapper extends BaseRowMapper<ListTypeCardDto> {

    @Override
    protected ListTypeCardDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        return new ListTypeCardDto(
                rs.getInt("Id"),
                rs.getString("Icon"),
                rs.getString("Title"),
                rs.getString("HeaderImage"),
                rs.getString("ListTypeDescription")
        );
    }
}
