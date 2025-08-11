package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
