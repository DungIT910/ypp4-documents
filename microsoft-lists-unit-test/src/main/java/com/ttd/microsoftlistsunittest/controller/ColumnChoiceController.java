package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import com.ttd.microsoftlistsunittest.dto.columnchoice.ColumnChoiceDto;
import com.ttd.microsoftlistsunittest.service.ColumnChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/column-choices")
@RequiredArgsConstructor
public class ColumnChoiceController {
    private final ColumnChoiceService columnChoiceService;

    @GetMapping
    public List<ColumnChoiceDto> getColumnChoices(
            @RequestParam("context") ColumnChoiceContext context,
            @RequestParam("columnId") Integer columnId
    ) {
        return columnChoiceService.getColumnChoices(context, columnId);
    }
}
