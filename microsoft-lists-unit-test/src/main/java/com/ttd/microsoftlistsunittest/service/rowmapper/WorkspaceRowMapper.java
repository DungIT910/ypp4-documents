package com.ttd.microsoftlistsunittest.service.rowmapper;

import com.ttd.microsoftlistsunittest.domain.Workspace;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class WorkspaceRowMapper extends BaseRowMapper<Workspace> {

    @Override
    protected Workspace mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        Workspace workspace = new Workspace();

        workspace.setId(rs.getInt("Id"));
        workspace.setWorkspaceName(rs.getString("WorkspaceName"));
        workspace.setCreatedAt(rs.getObject("CreatedAt", LocalDateTime.class));
        workspace.setUpdatedAt(rs.getObject("UpdatedAt", LocalDateTime.class));

        return workspace;
    }
}