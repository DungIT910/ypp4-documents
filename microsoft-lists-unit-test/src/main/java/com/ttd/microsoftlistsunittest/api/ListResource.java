package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListSummaryDto;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lists")
@RequiredArgsConstructor
public class ListResource {
    private final ListService listService;

    @GetMapping("/{listId}")
    public ListSummaryDto getListSummaryById(@PathVariable Integer listId,
                                             @RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.getListSummaryByListIdAndAccountId(listId, accountId);
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
    public List<RecentListSummaryDto> getRecentListsByAccountId(@RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.getRecentListsByAccountId(accountId);
    }
}
