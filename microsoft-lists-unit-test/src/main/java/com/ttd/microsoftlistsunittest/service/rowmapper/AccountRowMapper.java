package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AccountRowMapper extends BaseRowMapper<Account> {

    @Override
    protected Account mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();

        account.setId(rs.getInt("Id"));
        account.setAvatar(rs.getString("Avatar"));
        account.setFirstName(rs.getString("FirstName"));
        account.setLastName(rs.getString("LastName"));
        account.setDateBirth(rs.getObject("DateBirth", LocalDate.class));
        account.setEmail(rs.getString("Email"));
        account.setCompany(rs.getString("Company"));
        account.setAccountStatus(rs.getString("AccountStatus"));
        account.setAccountPassword(rs.getString("AccountPassword"));
        account.setCreatedAt(rs.getObject("CreatedAt", LocalDateTime.class));
        account.setUpdatedAt(rs.getObject("UpdatedAt", LocalDateTime.class));

        return account;
    }
}
