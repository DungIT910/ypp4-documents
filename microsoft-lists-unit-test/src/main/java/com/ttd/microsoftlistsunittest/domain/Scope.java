package com.ttd.microsoftlistsunittest.domain;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scope implements Serializable {
    private Integer id;
    private String code;
    private String displayName;
    private String scopeDescription;
    private String icon;
}
