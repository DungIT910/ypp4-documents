package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.listtemplate.ListTemplateSummaryProjection;

import java.util.List;

public interface ListTemplateRepository {
    List<ListTemplateSummaryProjection> findALlTemplatesByTemplateProviderId(Integer templateProviderId);
}
