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
public class Workspace {
    private Integer id;
    private String workspaceName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
