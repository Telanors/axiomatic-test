package ru.telanors.rest.util.xml.soap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SoapRequestBuilder {
    @Value("classpath:soap-envelope-template.xml")
    private Resource soapTemplate;

    public String createEnvelope(String body) throws IOException {
        String templateContent = StreamUtils
                .copyToString(soapTemplate.getInputStream(), StandardCharsets.UTF_8);

        return templateContent.replace("${body}", body);
    }
}
