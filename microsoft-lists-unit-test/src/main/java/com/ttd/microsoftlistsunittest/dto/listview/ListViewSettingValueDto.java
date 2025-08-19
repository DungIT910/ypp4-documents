package com.ttd.microsoftlistsunittest.dto.listview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListViewSettingValueDto {
    private Integer viewSettingKeyId;
    private String settingKey;
    private String valueType;
    private Integer groupByColumnId;
    private String rawValue;
}