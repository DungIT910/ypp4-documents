package com.ttd.microsoftlistsunittest.dto;

import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountProfileDtoRowMapper extends BaseRowMapper<AccountProfileDto> {

    @Override
    protected AccountProfileDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        AccountProfileDto dto = new AccountProfileDto();
        dto.setId(rs.getInt("Id"));
        dto.setAvatar(rs.getString("Avatar"));
        dto.setEmail(rs.getString("Email"));
        dto.setFirstName(rs.getString("FirstName"));
        dto.setLastName(rs.getString("LastName"));
        dto.setCompany(rs.getString("Company"));
        return dto;
    }
}
