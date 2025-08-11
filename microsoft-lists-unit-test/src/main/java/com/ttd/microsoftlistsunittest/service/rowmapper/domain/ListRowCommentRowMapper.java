package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.ListRowComment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ListRowCommentRowMapper implements RowMapper<ListRowComment> {
    @Override
    public ListRowComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListRowComment comment = new ListRowComment();
        comment.setId(rs.getInt("Id"));
        comment.setListRowId(rs.getInt("ListRowId"));
        comment.setContent(rs.getString("Content"));
        comment.setCreatedBy(rs.getInt("CreatedBy"));
        comment.setCreatedAt(rs.getObject("CreatedAt", LocalDateTime.class));
        comment.setUpdatedAt(rs.getObject("UpdatedAt", LocalDateTime.class));
        return comment;
    }
}
