package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.listtemplate.ListTemplateSampleDataProjection;

import java.util.List;

public interface ListTemplateDataRepository {
    List<ListTemplateSampleDataProjection> getListTemplateSampleData(Integer listTemplateId);
}
