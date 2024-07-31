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
import ru.telanors.rest.service.SoapClientService;
import ru.telanors.rest.util.exception.SoapClientException;
import ru.telanors.rest.util.xml.soap.SoapRequestBuilder;
import ru.telanors.rest.util.xml.soap.SoapResponseParser;

import java.nio.charset.StandardCharsets;

import static ru.telanors.rest.util.xml.soap.SoapConstants.PERSON_RESPONSE_TAG;
import static ru.telanors.rest.util.xml.soap.SoapConstants.SOAP_URL;

@Service
@RequiredArgsConstructor
public class SoapClientServiceImpl implements SoapClientService {
    private final SoapRequestBuilder soapRequestBuilder;
    private final SoapResponseParser soapResponseParser;

    @Override
    public String sendRequest(String bodyData) throws SoapClientException {
        try {
            var soapRequest = soapRequestBuilder.createEnvelope(bodyData);
            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpPost httpPost = new HttpPost(SOAP_URL);
            httpPost.setHeader("Content-Type", "text/xml; charset=UTF-8;");

            HttpEntity stringEntity = new StringEntity(soapRequest, StandardCharsets.UTF_8);
            httpPost.setEntity(stringEntity);

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            var entity = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);

            return soapResponseParser.extractXml(entity, PERSON_RESPONSE_TAG);
        } catch (Exception e) {
            throw new SoapClientException("Error occurred while sending SOAP request", e);
        }
    }
}
