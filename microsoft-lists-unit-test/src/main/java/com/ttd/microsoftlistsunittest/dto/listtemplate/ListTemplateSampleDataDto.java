package com.ttd.microsoftlistsunittest.dto.listtemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListTemplateSampleDataDto {
    private Integer rowId;
    private Integer colId;
    private String columnName;
    private String dataTypeIcon;
    private String cellValue;
}
