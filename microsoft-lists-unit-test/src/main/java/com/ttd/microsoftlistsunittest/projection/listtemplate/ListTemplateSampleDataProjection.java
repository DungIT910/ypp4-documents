package com.ttd.microsoftlistsunittest.projection.listtemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListTemplateSampleDataProjection {
    Integer rowId;
    Integer colId;
    String columnName;
    String dataTypeIcon;
    String cellValue;
}
