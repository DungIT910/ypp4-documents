package com.ttd.microsoftlistsunittest.domain;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShareLink implements Serializable {
    private Integer id;
    private Integer listId;
    private String targetUrl;
    private Integer scopeId;
    private Integer permissionId;
    private String linkStatus;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
