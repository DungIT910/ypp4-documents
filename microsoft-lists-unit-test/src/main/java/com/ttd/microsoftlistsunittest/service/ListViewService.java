package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;

import java.util.List;

public interface ListViewService {
    List<ListViewDto> getListViewsByListId(Integer listId);
}
