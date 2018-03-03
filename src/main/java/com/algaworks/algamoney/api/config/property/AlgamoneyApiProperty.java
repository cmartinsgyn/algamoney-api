package com.algaworks.algamoney.api.config.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgamoneyApiProperty {

    private String originPermitida = "http://localhost:8000";
    private final Seguranca seguranca = new Seguranca();

    /**classe*/
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Seguranca {
        private boolean enableHttps;
    }
}
