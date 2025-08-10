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
public class SystemColumn {
    private Integer id;
    private Integer systemDataTypeId;
    private String columnName;
    private Integer displayOrder;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private Boolean canRename;
}
