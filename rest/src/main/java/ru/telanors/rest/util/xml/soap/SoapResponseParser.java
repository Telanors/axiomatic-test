package ru.telanors.rest.util.xml.soap;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SoapResponseParser {
    public String extractXml(String soapResponse, String tagName) {
        String regex = String.format("<%s[^>]*>(.*?)</%s>", tagName, tagName);
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(soapResponse);

        if (matcher.find()) {
            String xmlContent = matcher.group(1);
            return decodeXmlContent(xmlContent);
        } else {
            throw new IllegalArgumentException(String.format("Element %s not found in SOAP response", tagName));
        }
    }

    private String decodeXmlContent(String xmlContent) {
        return xmlContent.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&#13;", "\r");
    }
}
