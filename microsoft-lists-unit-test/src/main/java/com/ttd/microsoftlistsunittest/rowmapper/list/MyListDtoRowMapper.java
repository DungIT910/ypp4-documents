package com.ttd.microsoftlistsunittest.rowmapper.list;

import com.ttd.microsoftlistsunittest.dto.list.MyListDto;
import com.ttd.microsoftlistsunittest.rowmapper.base.BaseRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MyListDtoRowMapper extends BaseRowMapper<MyListDto> {

    @Override
    protected MyListDto mapFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        MyListDto myListDto = new MyListDto();

        myListDto.setListId(rs.getInt("Id"));
        myListDto.setColor(rs.getString("Color"));
        myListDto.setIcon(rs.getString("Icon"));
        myListDto.setListName(rs.getString("ListName"));
        myListDto.setWorkspaceName(rs.getString("WorkspaceName"));
        myListDto.setIsFavorite(rs.getBoolean("IsFavorite"));

        return myListDto;
    }
}
