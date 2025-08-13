package com.ttd.microsoftlistsunittest.rowmapper.domain;

import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;
import com.ttd.microsoftlistsunittest.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountViewDtoRowMapper extends BaseRowMapper<AccounDisplayDto> {

    @Override
    protected AccounDisplayDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        AccounDisplayDto accounDisplayDto = new AccounDisplayDto();

        accounDisplayDto.setId(rs.getInt("Id"));
        accounDisplayDto.setAvatar(rs.getString("Avatar"));
        accounDisplayDto.setFirstName(rs.getString("FirstName"));
        accounDisplayDto.setLastName(rs.getString("LastName"));
        accounDisplayDto.setEmail(rs.getString("Email"));
        accounDisplayDto.setCompany(rs.getString("Company"));

        return accounDisplayDto;
    }
}
