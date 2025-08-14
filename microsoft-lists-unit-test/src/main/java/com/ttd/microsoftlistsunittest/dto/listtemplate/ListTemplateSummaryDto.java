package com.ttd.microsoftlistsunittest.dto.listtemplate;

import com.ttd.microsoftlistsunittest.projection.listtemplate.ListTemplateSummaryProjection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListTemplateSummaryDto {
    private Integer listTemplateId;
    private String title;
    private String headerImage;
    private String templateDescription;

    public static ListTemplateSummaryDto from(ListTemplateSummaryProjection projection) {
        return ListTemplateSummaryDto.builder()
                .listTemplateId(projection.getListTemplateId())
                .title(projection.getTitle())
                .templateDescription(projection.getTemplateDescription())
                .headerImage(projection.getHeaderImage())
                .build();
    }
}
