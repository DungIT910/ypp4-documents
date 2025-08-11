package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.ShareLink;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ShareLinkRowMapper implements RowMapper<ShareLink> {
    @Override
    public ShareLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShareLink shareLink = new ShareLink();
        shareLink.setId(rs.getInt("Id"));
        shareLink.setListId(rs.getInt("ListId"));
        shareLink.setTargetUrl(rs.getString("TargetUrl"));
        shareLink.setScopeId(rs.getInt("ScopeId"));
        shareLink.setPermissionId(rs.getInt("PermissionId"));
        shareLink.setLinkStatus(rs.getString("LinkStatus"));
        shareLink.setCreatedBy(rs.getInt("CreatedBy"));
        shareLink.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
        shareLink.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
        return shareLink;
    }
}
