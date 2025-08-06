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
public class WorkspaceMember {
    private Integer workspaceId;
    private Integer accountId;
    private LocalDateTime joinedAt;
    private String memberStatus;
    private LocalDateTime updatedAt;
}
