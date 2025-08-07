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

        account.setId(rs.getInt("id"));
        account.setAvatar(rs.getString("avatar"));
        account.setFirstName(rs.getString("firstName"));
        account.setLastName(rs.getString("lastName"));
        account.setDateBirth(rs.getObject("dateBirth", LocalDate.class));
        account.setEmail(rs.getString("email"));
        account.setCompany(rs.getString("company"));
        account.setAccountStatus(rs.getString("accountStatus"));
        account.setAccountPassword(rs.getString("accountPassword"));
        account.setCreatedAt(rs.getObject("createdAt", LocalDateTime.class));
        account.setUpdatedAt(rs.getObject("updatedAt", LocalDateTime.class));

        return account;
    }

}
