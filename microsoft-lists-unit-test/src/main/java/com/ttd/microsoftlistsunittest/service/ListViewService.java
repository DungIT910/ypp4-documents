package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;
import com.ttd.microsoftlistsunittest.dto.listview.ListViewSettingValueDto;

import java.util.List;

public interface ListViewService {
    List<ListViewDto> getListViewsByListId(Integer listId);

    List<ListViewSettingValueDto> getListViewSettingValues(Integer listViewId);
}
