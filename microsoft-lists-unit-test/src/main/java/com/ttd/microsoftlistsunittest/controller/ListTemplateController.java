package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;
import com.ttd.microsoftlistsunittest.service.ListTemplateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/list-templates")
@RequiredArgsConstructor
public class ListTemplateController {
    private final ListTemplateDataService listTemplateDataService;

    @GetMapping("/{listTemplateId}/sample-data")
    public List<ListTemplateSampleDataDto> getListTemplateSampleData(@PathVariable Integer listTemplateId) {
        return listTemplateDataService.getListTemplateSampleData(listTemplateId);
    }
}
