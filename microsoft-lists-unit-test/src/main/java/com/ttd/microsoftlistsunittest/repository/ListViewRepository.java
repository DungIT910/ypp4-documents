package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;

import java.util.List;

public interface ListViewRepository {
    List<ListViewDto> getListViewsByListId(Integer listId);
}
