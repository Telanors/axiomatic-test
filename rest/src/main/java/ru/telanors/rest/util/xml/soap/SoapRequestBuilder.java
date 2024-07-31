package ru.telanors.rest.util.xml.soap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
public class SoapRequestBuilder {
    public String createEnvelope(String body) throws JAXBException {
        SoapEnvelope.SoapBody soapBody = new SoapEnvelope.SoapBody();
        SoapEnvelope envelope = new SoapEnvelope();

        soapBody.setRequestBody(body);
        envelope.setBody(soapBody);

        JAXBContext jaxbContext = JAXBContext.newInstance(SoapEnvelope.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(envelope, sw);

        return sw.toString();
    }
}
