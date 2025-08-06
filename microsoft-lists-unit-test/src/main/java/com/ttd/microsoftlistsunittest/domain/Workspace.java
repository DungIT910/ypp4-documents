package com.ttd.microsoftlistsunittest.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Workspace {
    @NonNull
    private Integer id;
    @NonNull
    private String workspaceName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
