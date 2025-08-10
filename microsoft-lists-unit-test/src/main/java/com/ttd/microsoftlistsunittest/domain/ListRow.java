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
public class ListRow {
    private Integer id;
    private Integer listId;
    private Integer displayOrder;
    private LocalDateTime modifiedAt;
    private Integer createdBy;
    private String listRowStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
