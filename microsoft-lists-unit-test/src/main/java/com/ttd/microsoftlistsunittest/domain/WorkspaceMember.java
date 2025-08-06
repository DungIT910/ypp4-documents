package com.ttd.microsoftlistsunittest.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class WorkspaceMember {
    @NonNull
    private Integer id;
    @NonNull
    private Integer workspaceId;
    @NonNull
    private Integer accountId;
    private LocalDateTime joinedAt;
    private String memberStatus;
    private LocalDateTime updatedAt;
}
