package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.ObjectType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ObjectTypeRowMapper implements RowMapper<ObjectType> {
    @Override
    public ObjectType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ObjectType obj = new ObjectType();
        obj.setId(rs.getInt("Id"));
        obj.setCode(rs.getString("Code"));
        obj.setDisplayName(rs.getString("DisplayName"));
        obj.setIcon(rs.getString("Icon"));
        return obj;
    }
}
