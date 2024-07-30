package ru.telanors.soap.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.telanors.soap.service.XsltService;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XsltServiceImpl implements XsltService {
    public String transformXml(String xml) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Source xslt = new StreamSource(new ClassPathResource("transform.xslt").getInputStream());
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new StringReader(xml));
            StringWriter writer = new StringWriter();
            transformer.transform(text, new StreamResult(writer));

            return writer.toString();
        } catch (IOException | TransformerException exception) {
            throw new RuntimeException("Error transformation XML to XSLT: " + exception.getMessage());
        }
    }
}
