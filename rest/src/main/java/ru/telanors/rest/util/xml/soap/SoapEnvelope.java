
package ru.telanors.rest.util.xml.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import static ru.telanors.rest.util.xml.soap.SoapConstants.*;

@Data
@XmlRootElement(name = ELEMENT_ENVELOPE, namespace = ENVELOPE_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapEnvelope {

    @XmlElement(name = ELEMENT_BODY, namespace = ENVELOPE_NAMESPACE)
    private SoapBody body;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SoapBody {
        @XmlElement(name = PERSON_REQUEST_TAG, namespace = REQUEST_NAMESPACE)
        private String requestBody;
    }
}