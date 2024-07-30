package ru.telanors.rest.service;

import java.io.IOException;

public interface SoapClientService {
    String sendRequest(String xmlData) throws IOException;
}
