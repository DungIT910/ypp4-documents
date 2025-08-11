package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.Scope;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScopeRowMapper implements RowMapper<Scope> {
    @Override
    public Scope mapRow(ResultSet rs, int rowNum) throws SQLException {
        Scope scope = new Scope();
        scope.setId(rs.getInt("Id"));
        scope.setCode(rs.getString("Code"));
        scope.setDisplayName(rs.getString("DisplayName"));
        scope.setScopeDescription(rs.getString("ScopeDescription"));
        scope.setIcon(rs.getString("Icon"));
        return scope;
    }
}
