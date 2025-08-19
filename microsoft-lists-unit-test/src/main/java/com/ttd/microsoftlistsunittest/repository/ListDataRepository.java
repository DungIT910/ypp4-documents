package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.list.ListDataDto;

import java.util.List;

public interface ListDataRepository {
    List<ListDataDto> getListDataByListId(Integer listId);
}
