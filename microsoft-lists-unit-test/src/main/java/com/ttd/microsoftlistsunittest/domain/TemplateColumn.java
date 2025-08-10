package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateColumn {
    private Integer id;
    private Integer systemDataTypeId;
    private Integer listTemplateId;
    private String columnName;
    private String columnDescription;
    private Integer displayOrder;
    private Boolean isVisible;
}
