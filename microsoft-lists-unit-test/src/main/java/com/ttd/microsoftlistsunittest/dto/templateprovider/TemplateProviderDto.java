package com.ttd.microsoftlistsunittest.dto.templateprovider;

import com.ttd.microsoftlistsunittest.projection.templateprovider.TemplateProviderProjection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateProviderDto {
    private Integer providerId;
    private String providerName;

    public static TemplateProviderDto from(TemplateProviderProjection projection) {
        return TemplateProviderDto.builder()
                .providerId(projection.getProviderId())
                .providerName(projection.getProviderName())
                .build();
    }
}

