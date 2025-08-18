package com.ttd.microsoftlistsunittest.projection.listtemplate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListTemplateSummaryProjection {
    private Integer listTemplateId;
    private String title;
    private String icon;
    private String headerImage;
    private String templateDescription;
}
