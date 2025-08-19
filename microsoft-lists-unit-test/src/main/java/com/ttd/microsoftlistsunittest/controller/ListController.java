package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.dto.list.ListDataDto;
import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;
import com.ttd.microsoftlistsunittest.service.ListDataService;
import com.ttd.microsoftlistsunittest.service.ListService;
import com.ttd.microsoftlistsunittest.service.ListViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lists")
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;
    private final ListViewService listViewService;
    private final ListDataService listDataService;

    @GetMapping("/{listId}")
    public ListSummaryDto getListSummaryById(@PathVariable Integer listId,
                                             @RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.getListSummaryByListIdAndAccountId(listId, accountId);
    }

    @GetMapping("/{listId}/data")
    public List<ListDataDto> getListDataById(@PathVariable Integer listId) {
        return listDataService.getListDataByListId(listId);
    }


    @GetMapping("/personal")
    public List<ListSummaryDto> getPersonalListsByAccountId(@RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.getPersonalListsByAccountId(accountId);
    }

    @GetMapping("/favorite")
    public List<ListSummaryDto> getFavoriteListsByAccountId(@RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.getFavoriteListsByAccountId(accountId);
    }

    @GetMapping("/recent")
    public List<ListSummaryDto> getRecentListsByAccountId(@RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.getRecentListsByAccountId(accountId);
    }

    @GetMapping("/{listId}/list-views")
    public List<ListViewDto> getListViewsByListId(@PathVariable Integer listId) {
        return listViewService.getListViewsByListId(listId);
    }
}
