package com.ttd.microsoftlistsunittest.projection.workspace;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceProjection {
    private Integer workspaceId;
    private String workspaceName;
}
