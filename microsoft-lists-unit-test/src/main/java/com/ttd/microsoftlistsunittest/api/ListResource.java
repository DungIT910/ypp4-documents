package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.list.ListCreateDto;
import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lists")
@RequiredArgsConstructor
public class ListResource {
    private final ListService listService;

    @GetMapping("/{listId}")
    public ListDisplayDto getListById(@PathVariable Integer listId) {
        return listService.findListById(listId);
    }

    @PostMapping
    public int createList(@RequestBody ListCreateDto listCreateDto) {
        return listService.createList(listCreateDto);
    }

    @PutMapping("/{listId}")
    public int updateList(@PathVariable Integer listId, @RequestBody ListUpdateDto accountUpdateDto) {
        return listService.updateList(listId, accountUpdateDto);
    }
}
