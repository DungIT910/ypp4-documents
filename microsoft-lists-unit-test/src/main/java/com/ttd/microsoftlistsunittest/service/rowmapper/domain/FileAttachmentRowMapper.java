package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.FileAttachment;
import com.ttd.microsoftlistsunittest.service.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class FileAttachmentRowMapper extends BaseRowMapper<FileAttachment> {
    @Override
    protected FileAttachment mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        FileAttachment attachment = new FileAttachment();
        attachment.setId(rs.getInt("Id"));
        attachment.setListRowId(rs.getInt("ListRowId"));
        attachment.setFileAttachmentName(rs.getString("FileAttachmentName"));
        attachment.setFileUrl(rs.getString("FileUrl"));
        attachment.setCreatedAt(rs.getObject("CreatedAt", LocalDateTime.class));
        attachment.setUpdatedAt(rs.getObject("UpdatedAt", LocalDateTime.class));
        return attachment;
    }
}
