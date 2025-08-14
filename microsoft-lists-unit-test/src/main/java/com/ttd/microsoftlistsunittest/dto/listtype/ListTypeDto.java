package com.ttd.microsoftlistsunittest.dto.listtype;

import com.ttd.microsoftlistsunittest.projection.listtype.ListTypeProjection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListTypeDto {
    private Integer listTypeId;
    private String title;
    private String icon;
    private String listTypeDescription;
    private String headerImage;

    public static ListTypeDto from(ListTypeProjection projection) {
        return ListTypeDto.builder()
                .listTypeId(projection.getListTypeId())
                .title(projection.getTitle())
                .icon(projection.getIcon())
                .listTypeDescription(projection.getListTypeDescription())
                .headerImage(projection.getHeaderImage())
                .build();
    }
}
