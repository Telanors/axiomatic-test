package ru.telanors.rest.util.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.io.StringReader;

@Component
public class XmlConverter {
    public <T> String convertDtoToXml(T personDTO, String tagName) {
        var jsonObject = new JSONObject(personDTO);
        return XML.toString(jsonObject, tagName);
    }

    public <T> T convertXsltToDto(Class<T> clazz, String xslt) throws JAXBException {
        var context = JAXBContext.newInstance(clazz);
        var unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xslt));
    }
}