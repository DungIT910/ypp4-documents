package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.TemplateView;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TemplateViewRowMapper extends BaseRowMapper<TemplateView> {
    @Override
    protected TemplateView mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        TemplateView view = new TemplateView();
        view.setId(rs.getInt("Id"));
        view.setListTemplateId(rs.getInt("ListTemplateId"));
        view.setViewTypeId(rs.getInt("ViewTypeId"));
        view.setViewName(rs.getString("ViewName"));
        view.setDisplayOrder(rs.getInt("DisplayOrder"));
        return view;
    }
}
