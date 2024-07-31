package ru.telanors.rest.service;

public interface SoapClientService {
    String sendRequest(String xmlData) throws Exception;
}
