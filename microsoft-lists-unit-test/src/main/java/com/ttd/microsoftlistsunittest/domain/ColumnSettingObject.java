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
public class ColumnSettingObject {
    private Integer id;
    private Integer columnId;
    private String displayName;
    private String displayColor;
    private Integer displayOrder;
    private String context;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
