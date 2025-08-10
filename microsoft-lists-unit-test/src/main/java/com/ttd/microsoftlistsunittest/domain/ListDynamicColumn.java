package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDynamicColumn {
    private Integer id;
    private Integer listId;
    private Integer systemDataTypeId;
    private Integer systemColumnId;
    private String columnName;
    private String columnDescription;
    private Integer displayOrder;
    private Boolean isSystemColumn;
    private Boolean isVisible;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
