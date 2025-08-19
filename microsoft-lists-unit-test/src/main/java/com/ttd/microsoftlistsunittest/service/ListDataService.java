package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.list.ListDataDto;

import java.util.List;

public interface ListDataService {
    public List<ListDataDto> getListDataByListId(Integer listId);
}
