package ru.telanors.rest.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ru.telanors.rest.config.properties.SoapProperties;
import ru.telanors.rest.service.SoapClientService;
import ru.telanors.rest.util.xml.soap.SoapRequestBuilder;
import ru.telanors.rest.util.xml.soap.SoapResponseParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SoapClientServiceImpl implements SoapClientService {
    private final SoapProperties soapProperties;

    private final SoapRequestBuilder soapRequestBuilder;
    private final SoapResponseParser soapResponseParser;

    @Override
    public String sendRequest(String bodyData) throws IOException {
        var soapRequest = soapRequestBuilder.createEnvelope(bodyData);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(soapProperties.getUrl());
            httpPost.setHeader("Content-Type", "text/xml; charset=UTF-8;");

            HttpEntity stringEntity = new StringEntity(soapRequest, StandardCharsets.UTF_8);
            httpPost.setEntity(stringEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                var entity = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                return soapResponseParser.extractXml(entity, soapProperties.getResponseName());
            }
        }
    }
}
