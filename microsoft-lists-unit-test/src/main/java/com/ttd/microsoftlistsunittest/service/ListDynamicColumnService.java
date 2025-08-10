package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListDynamicColumn;

import java.util.List;

public interface ListDynamicColumnService {
    ListDynamicColumn findById(Integer id);

    List<ListDynamicColumn> findByListId(Integer listId);

    void create(ListDynamicColumn column);

    void update(ListDynamicColumn column);

    void delete(Integer id);
}
