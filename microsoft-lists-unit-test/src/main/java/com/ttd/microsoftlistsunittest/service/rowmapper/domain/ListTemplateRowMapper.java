package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.ListTemplate;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ListTemplateRowMapper extends BaseRowMapper<ListTemplate> {
    @Override
    protected ListTemplate mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        ListTemplate template = new ListTemplate();

        template.setId(rs.getInt("Id"));
        template.setTitle(rs.getString("Title"));
        template.setHeaderImage(rs.getString("HeaderImage"));
        template.setTemplateDescription(rs.getString("TemplateDescription"));
        template.setIcon(rs.getString("Icon"));
        template.setColor(rs.getString("Color"));
        template.setSummary(rs.getString("Summary"));
        template.setFeature(rs.getString("Feature"));
        template.setProviderId(rs.getInt("ProviderId"));

        return template;
    }
}
