package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.list.ListDetailDto;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lists")
@RequiredArgsConstructor
public class ListResource {
    private final ListService listService;

    @GetMapping("/{listId}")
    public ListDetailDto getListDetailsById(@PathVariable Integer listId,
                                            @RequestParam(value = "accountId", required = true) Integer accountId) {
        return listService.findListDetailByListIdAndAccountId(listId, accountId);
    }
}
