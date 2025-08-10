package com.ttd.microsoftlistsunittest.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachment {
    private Integer id;
    private Integer listRowId;
    private String fileAttachmentName;
    private String fileUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
