package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;

import java.util.List;

public interface ListTemplateService {
    List<ListTemplateSummaryDto> getALlTemplatesByTemplateProviderId(Integer templateProviderId);
}
