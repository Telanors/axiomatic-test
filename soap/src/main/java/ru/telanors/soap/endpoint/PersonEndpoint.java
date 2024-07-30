package ru.telanors.soap.endpoint;

import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.telanors.soap.service.XsltService;

import javax.xml.namespace.QName;

@Endpoint
@RequiredArgsConstructor
public class PersonEndpoint {

    private static final String NAMESPACE_URI = "http://telanors.ru/soap/";
    private final XsltService xsltService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "personRequest")
    @ResponsePayload
    public JAXBElement<String> transformPerson(@RequestPayload JAXBElement<String> request) {
        String transformedXml = xsltService.transformXml(request.getValue());
        return new JAXBElement<>(new QName(NAMESPACE_URI, "personResponse"), String.class, transformedXml);
    }
}