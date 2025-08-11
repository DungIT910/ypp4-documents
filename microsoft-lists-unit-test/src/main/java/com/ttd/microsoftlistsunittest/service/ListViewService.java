package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListView;

import java.util.List;

public interface ListViewService {
    ListView findById(Integer id);

    List<ListView> findByListId(Integer listId);

    void create(ListView listView);

    void update(ListView listView);

    void delete(Integer id);
}
