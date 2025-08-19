package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;
import com.ttd.microsoftlistsunittest.repository.ListViewRepository;
import com.ttd.microsoftlistsunittest.service.ListViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListViewServiceImpl implements ListViewService {
    private final ListViewRepository listViewRepository;

    @Override
    public List<ListViewDto> getListViewsByListId(Integer listId) {
        return listViewRepository.getListViewsByListId(listId);
    }
}
