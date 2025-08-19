package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewSettingValueDto;
import com.ttd.microsoftlistsunittest.service.ListViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/list-views")
@RequiredArgsConstructor
public class ListViewController {
    private final ListViewService listViewService;

    @GetMapping("/{listViewId}/settings")
    public List<ListViewSettingValueDto> getListViewSettingValues(@PathVariable Integer listViewId) {
        return listViewService.getListViewSettingValues(listViewId);
    }
}
