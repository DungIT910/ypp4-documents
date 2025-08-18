package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;

import java.util.List;

public interface ListTemplateDataService {
    public List<ListTemplateSampleDataDto> getListTemplateSampleData(Integer listTemplateId);
}
