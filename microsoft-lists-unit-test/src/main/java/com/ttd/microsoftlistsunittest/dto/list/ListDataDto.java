package com.ttd.microsoftlistsunittest.dto.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDataDto {
    private Integer rowId;
    private Integer columnId;
    private String columnName;
    private String columnIcon;
    private String cellValue;
}
