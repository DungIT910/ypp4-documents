package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;
import com.ttd.microsoftlistsunittest.dto.listview.ListViewSettingValueDto;

import java.util.List;

public interface ListViewRepository {
    List<ListViewDto> getListViewsByListId(Integer listId);

    List<ListViewSettingValueDto> getListViewSettingValues(Integer listViewId);
}
