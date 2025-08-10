package com.ttd.microsoftlistsunittest.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workspace {
    private Integer id;
    private String workspaceName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
