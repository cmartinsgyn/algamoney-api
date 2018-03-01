package com.algaworks.algamoney.api.config.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

    private String originPermitida = "http://localhost:8000";
    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    private final Seguranca seguranca = new Seguranca();
    public Seguranca getSeguranca() {
        return seguranca;
    }

    /**classe*/
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Seguranca {
        private boolean enableHttps;
    }
}
