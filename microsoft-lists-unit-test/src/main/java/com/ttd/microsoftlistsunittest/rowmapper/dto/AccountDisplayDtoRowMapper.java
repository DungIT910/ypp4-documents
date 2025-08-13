package com.ttd.microsoftlistsunittest.rowmapper.dto;

import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;
import com.ttd.microsoftlistsunittest.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountDisplayDtoRowMapper extends BaseRowMapper<AccounDisplayDto> {

    @Override
    protected AccounDisplayDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        AccounDisplayDto dto = new AccounDisplayDto();
        dto.setId(rs.getInt("Id"));
        dto.setAvatar(rs.getString("Avatar"));
        dto.setEmail(rs.getString("Email"));
        dto.setFirstName(rs.getString("FirstName"));
        dto.setLastName(rs.getString("LastName"));
        dto.setCompany(rs.getString("Company"));
        return dto;
    }
}
