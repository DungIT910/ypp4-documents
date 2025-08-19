package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import com.ttd.microsoftlistsunittest.dto.columnchoice.ColumnChoiceDto;
import com.ttd.microsoftlistsunittest.repository.ColumnChoiceRepository;
import com.ttd.microsoftlistsunittest.service.ColumnChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnChoiceServiceImpl implements ColumnChoiceService {
    private final ColumnChoiceRepository columnChoiceRepository;

    @Override
    public List<ColumnChoiceDto> getColumnChoices(ColumnChoiceContext context, Integer columnId) {
        return columnChoiceRepository.getColumnChoices(context, columnId);
    }
}
