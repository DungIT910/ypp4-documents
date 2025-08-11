package com.ttd.microsoftlistsunittest.service.rowmapper.base;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseRowMapper<T> implements RowMapper<T> {
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return mapFromResultSet(rs, rowNum);
    }

    protected abstract T mapFromResultSet(ResultSet rs, int rowNum) throws SQLException;
}
