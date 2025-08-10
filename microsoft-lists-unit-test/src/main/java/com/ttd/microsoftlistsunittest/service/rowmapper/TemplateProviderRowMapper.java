package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.TemplateProvider;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TemplateProviderRowMapper extends BaseRowMapper<TemplateProvider> {

    @Override
    protected TemplateProvider mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        TemplateProvider provider = new TemplateProvider();
        provider.setId(rs.getInt("Id"));
        provider.setProviderName(rs.getString("ProviderName"));
        return provider;
    }
}
