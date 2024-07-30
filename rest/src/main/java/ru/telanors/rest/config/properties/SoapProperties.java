package ru.telanors.rest.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "soap")
public class SoapProperties {
    private String url;
    private String namespace;
    private String requestName;
    private String responseName;
}