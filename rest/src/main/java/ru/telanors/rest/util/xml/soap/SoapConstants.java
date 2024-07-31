package ru.telanors.rest.util.xml.soap;

public class SoapConstants {
    public static final String SOAP_URL = "http://localhost:8081/soap";

    public static final String REQUEST_NAMESPACE = "http://telanors.ru/soap/";
    public static final String ENVELOPE_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/";

    public static final String PERSON_REQUEST_TAG = "personRequest";
    public static final String PERSON_RESPONSE_TAG = "personResponse";

    public static final String ELEMENT_ENVELOPE = "Envelope";
    public static final String ELEMENT_BODY = "Body";
    public static final String ELEMENT_Header = "Header";
    public static final String ELEMENT_Fault = "Fault";
}