package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;

import java.util.List;

public interface ListTemplateDataRepository {
    List<ListTemplateSampleDataDto> getListTemplateSampleData(Integer listTemplateId);
}
