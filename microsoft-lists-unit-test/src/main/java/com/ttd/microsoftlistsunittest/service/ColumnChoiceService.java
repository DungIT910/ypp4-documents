package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import com.ttd.microsoftlistsunittest.dto.columnchoice.ColumnChoiceDto;

import java.util.List;

public interface ColumnChoiceService {
    List<ColumnChoiceDto> getColumnChoices(ColumnChoiceContext context, Integer columnId);
}
