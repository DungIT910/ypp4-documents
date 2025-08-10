package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateView {
    private Integer id;
    private Integer listTemplateId;
    private Integer viewTypeId;
    private String viewName;
    private Integer displayOrder;
}
