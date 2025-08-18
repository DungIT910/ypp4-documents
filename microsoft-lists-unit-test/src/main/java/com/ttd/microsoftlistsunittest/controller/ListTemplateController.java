package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.service.ListTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/list-templates")
@RequiredArgsConstructor
public class ListTemplateController {
    private final ListTemplateService listTemplateService;

    @GetMapping("/{listTemplateId}/sample-data")
    public ListSummaryDto getListTemplateSampleData(@PathVariable Integer listTemplateId) {
        return null;
    }
}
