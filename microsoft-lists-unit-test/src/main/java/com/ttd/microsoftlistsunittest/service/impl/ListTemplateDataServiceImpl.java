package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;
import com.ttd.microsoftlistsunittest.repository.ListTemplateDataRepository;
import com.ttd.microsoftlistsunittest.service.ListTemplateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTemplateDataServiceImpl implements ListTemplateDataService {
    private final ListTemplateDataRepository listTemplateDataRepository;

    @Override
    public List<ListTemplateSampleDataDto> getListTemplateSampleData(Integer listTemplateId) {
        return listTemplateDataRepository.getListTemplateSampleData(listTemplateId).stream()
                .map(ListTemplateSampleDataDto::from)
                .toList();
    }
}
