package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;

import java.util.List;

public interface ListTemplateRepository {
    List<ListTemplateSummaryDto> findALlTemplatesByTemplateProviderId(Integer templateProviderId);
}
