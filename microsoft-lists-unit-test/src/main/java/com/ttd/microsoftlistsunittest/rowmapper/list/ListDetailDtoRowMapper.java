package com.ttd.microsoftlistsunittest.rowmapper.list;

import com.ttd.microsoftlistsunittest.dto.list.ListDetailDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ListDetailDtoRowMapper implements RowMapper<ListDetailDto> {
    @Override
    public ListDetailDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListDetailDto listDetailDto = new ListDetailDto();

        listDetailDto.setListId(rs.getInt("Id"));
        listDetailDto.setListName(rs.getString("ListName"));
        listDetailDto.setWorkspaceName(rs.getString("WorkspaceName"));
        listDetailDto.setIcon(rs.getString("Icon"));
        listDetailDto.setColor(rs.getString("Color"));
        listDetailDto.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
        listDetailDto.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
        listDetailDto.setIsFavorite(rs.getBoolean("IsFavorite"));

        return listDetailDto;
    }
}