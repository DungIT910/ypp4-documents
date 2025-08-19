package com.ttd.microsoftlistsunittest.dto.columnchoice;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnChoiceDto {
    private Integer columnChoiceId;
    private ColumnChoiceContext context;
    private String displayName;
    private String displayColor;
    private Integer displayOrder;
}
