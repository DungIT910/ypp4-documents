package com.ttd.microsoftlistsunittest.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrashItem {
    private Integer id;
    private Integer objectTypeId;
    private Integer objectId;
    private Integer userDeleteId;
    private LocalDateTime deletedAt;
    private String originalPath;
}
