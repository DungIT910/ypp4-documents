package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.TemplateColumn;

import java.util.List;
import java.util.Optional;

public interface TemplateColumnService {
    List<TemplateColumn> findAll();

    Optional<TemplateColumn> findById(Integer id);

    List<TemplateColumn> findByListTemplateId(Integer listTemplateId);

    int save(TemplateColumn column);

    int update(TemplateColumn column);

    int deleteById(Integer id);
}
