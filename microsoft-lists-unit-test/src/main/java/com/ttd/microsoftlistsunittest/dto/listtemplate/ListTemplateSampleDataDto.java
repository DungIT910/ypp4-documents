package com.ttd.microsoftlistsunittest.dto.listtemplate;

import com.ttd.microsoftlistsunittest.projection.listtemplate.ListTemplateSampleDataProjection;

public class ListTemplateSampleDataDto {
    Integer rowId;
    Integer colId;
    String columnName;
    String dataTypeIcon;
    String cellValue;

    public static ListTemplateSampleDataDto from(ListTemplateSampleDataProjection projection) {
        ListTemplateSampleDataDto dto = new ListTemplateSampleDataDto();
        dto.rowId = projection.getRowId();
        dto.colId = projection.getColId();
        dto.columnName = projection.getColumnName();
        dto.dataTypeIcon = projection.getDataTypeIcon();
        dto.cellValue = projection.getCellValue();
        return dto;
    }
}
