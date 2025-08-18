package com.ttd.microsoftlistsunittest.dto.workspace;

import com.ttd.microsoftlistsunittest.projection.workspace.WorkspaceProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDto {
    private Integer workspaceId;
    private String workspaceName;

    public static WorkspaceDto from(WorkspaceProjection projection) {
        return WorkspaceDto.builder()
                .workspaceId(projection.getWorkspaceId())
                .workspaceName(projection.getWorkspaceName())
                .build();
    }
}
