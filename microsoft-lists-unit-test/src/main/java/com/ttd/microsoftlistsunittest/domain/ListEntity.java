package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListEntity {
    private Integer id;
    private String listName;
    private String icon;
    private String color;
    private Integer workspaceId;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String listStatus;
}
