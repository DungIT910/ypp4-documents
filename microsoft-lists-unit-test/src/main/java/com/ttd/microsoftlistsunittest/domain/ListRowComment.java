package com.ttd.microsoftlistsunittest.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListRowComment {
    private Integer id;
    private Integer listRowId;
    private String content;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
