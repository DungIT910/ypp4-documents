package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workspace {
    private int id;
    private String workspaceName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
