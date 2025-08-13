package com.ttd.microsoftlistsunittest.rowmapper.list;

import com.ttd.microsoftlistsunittest.dto.list.RecentListDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecentListDtoRowMapper implements RowMapper<RecentListDto> {
    @Override
    public RecentListDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecentListDto recentListDto = new RecentListDto();

        recentListDto.setListId(rs.getInt("Id"));
        recentListDto.setListName(rs.getString("ListName"));
        recentListDto.setIcon(rs.getString("Icon"));
        recentListDto.setColor(rs.getString("Color"));
        recentListDto.setWorkspaceId(rs.getInt("WorkspaceId"));
        recentListDto.setIsFavorite(rs.getBoolean("IsFavorite"));

        return recentListDto;
    }
}