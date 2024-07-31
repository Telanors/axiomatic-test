package ru.telanors.soap.endpoint;

import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.telanors.soap.service.XsltService;

import javax.xml.namespace.QName;

import static ru.telanors.soap.util.SoapConstants.*;

@Endpoint
@RequiredArgsConstructor
public class PersonEndpoint {
    private final XsltService xsltService;

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = PERSON_REQUEST_TAG)
    @ResponsePayload
    public JAXBElement<String> transformPerson(@RequestPayload JAXBElement<String> request) {
        String transformedXml = xsltService.transformXml(request.getValue());
        return new JAXBElement<>(new QName(TARGET_NAMESPACE, PERSON_RESPONSE_TAG), String.class, transformedXml);
    }
}