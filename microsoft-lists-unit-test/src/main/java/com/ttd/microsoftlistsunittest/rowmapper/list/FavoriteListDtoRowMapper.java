package com.ttd.microsoftlistsunittest.rowmapper.list;

import com.ttd.microsoftlistsunittest.dto.list.FavoriteListDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FavoriteListDtoRowMapper implements RowMapper<FavoriteListDto> {
    @Override
    public FavoriteListDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        FavoriteListDto favoriteListDto = new FavoriteListDto();
        favoriteListDto.setListId(rs.getInt("listId"));
        favoriteListDto.setListName(rs.getString("listName"));
        favoriteListDto.setIcon(rs.getString("icon"));
        favoriteListDto.setColor(rs.getString("color"));
        favoriteListDto.setWorkspaceName(rs.getInt("workspaceName"));
        return favoriteListDto;
    }
}