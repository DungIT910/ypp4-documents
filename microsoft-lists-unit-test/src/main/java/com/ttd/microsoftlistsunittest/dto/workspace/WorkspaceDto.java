package com.ttd.microsoftlistsunittest.dto.workspace;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDto {
    private Integer workspaceId;
    private String workspaceName;

}
