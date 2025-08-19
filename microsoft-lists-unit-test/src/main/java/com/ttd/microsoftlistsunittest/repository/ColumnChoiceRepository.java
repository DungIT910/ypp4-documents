package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import com.ttd.microsoftlistsunittest.dto.columnchoice.ColumnChoiceDto;

import java.util.List;

public interface ColumnChoiceRepository {
    List<ColumnChoiceDto> getColumnChoices(ColumnChoiceContext context, Integer columnId);
}
