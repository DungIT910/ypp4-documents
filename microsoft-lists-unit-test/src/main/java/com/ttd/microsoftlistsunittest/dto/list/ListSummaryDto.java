package com.ttd.microsoftlistsunittest.dto.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ListDetailDto extends BaseListDto {
    private String workspaceName;
    private Boolean isFavorite;
}
