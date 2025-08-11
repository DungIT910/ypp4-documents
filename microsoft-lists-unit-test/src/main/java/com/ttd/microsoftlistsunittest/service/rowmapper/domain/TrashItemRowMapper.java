package com.ttd.microsoftlistsunittest.service.rowmapper.domain;

import com.ttd.microsoftlistsunittest.domain.TrashItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TrashItemRowMapper implements RowMapper<TrashItem> {
    @Override
    public TrashItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrashItem item = new TrashItem();
        item.setId(rs.getInt("Id"));
        item.setObjectTypeId(rs.getInt("ObjectTypeId"));
        item.setObjectId(rs.getInt("ObjectId"));
        item.setUserDeleteId(rs.getInt("UserDeleteId"));
        item.setDeletedAt(rs.getTimestamp("DeletedAt").toLocalDateTime());
        item.setOriginalPath(rs.getString("OriginalPath"));
        return item;
    }
}
