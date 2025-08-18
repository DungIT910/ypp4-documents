package com.ttd.microsoftlistsunittest.dto.listtemplate;

import lombok.*;

@Getter
@Setter
public class ListTemplateSummaryDto {
    private Integer listTemplateId;
    private String title;
    private String icon;
    private String headerImage;
    private String templateDescription;
}
