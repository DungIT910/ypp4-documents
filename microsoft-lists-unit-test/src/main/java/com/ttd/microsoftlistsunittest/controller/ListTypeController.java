package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;
import com.ttd.microsoftlistsunittest.service.ListTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/list-types")
@RequiredArgsConstructor
public class ListTypeController {
    private final ListTypeService listTypeService;

    @GetMapping
    public List<ListTypeDto> getAllListTypes() {
        return listTypeService.getAllListTypes();
    }
}
