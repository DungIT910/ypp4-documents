package com.ttd.microsoftlistsunittest.rowmapper.account;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import com.ttd.microsoftlistsunittest.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountProfileDtoRowMapper extends BaseRowMapper<AccountProfileDto> {

    @Override
    protected AccountProfileDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        AccountProfileDto accountProfileDto = new AccountProfileDto();

        accountProfileDto.setAccountId(rs.getInt("Id"));
        accountProfileDto.setAvatar(rs.getString("Avatar"));
        accountProfileDto.setFirstName(rs.getString("FirstName"));
        accountProfileDto.setLastName(rs.getString("LastName"));
        accountProfileDto.setEmail(rs.getString("Email"));
        accountProfileDto.setCompany(rs.getString("Company"));

        return accountProfileDto;
    }
}
