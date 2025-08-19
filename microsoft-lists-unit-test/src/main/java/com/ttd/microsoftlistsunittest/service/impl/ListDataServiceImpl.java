package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.list.ListDataDto;
import com.ttd.microsoftlistsunittest.repository.ListDataRepository;
import com.ttd.microsoftlistsunittest.service.ListDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListDataServiceImpl implements ListDataService {
    private final ListDataRepository listDataRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<ListDataDto> getListDataByListId(Integer listId) {
        return listDataRepository.getListDataByListId(listId);
    }
}
