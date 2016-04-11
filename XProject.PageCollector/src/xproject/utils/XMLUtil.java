/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xproject.pagecollector.ProductValidationHandeler;

/**
 *
 * @author Admin
 */
public class XMLUtil {

    public static Elements getElements(String sourceUrl, String selectPath) {
        try {
            org.jsoup.nodes.Document sourceDoc = Jsoup.connect(sourceUrl).get();
            Elements resultSet = sourceDoc.select(selectPath);

            return resultSet;
        } catch (IOException ex) {
        }
        return null;
    }

    public static <T> void xmlWriter(T entity, String xmlDestinationPath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(entity.getClass());
            File destinationFile = new File(xmlDestinationPath);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entity, destinationFile);
        } catch (JAXBException ex) {
        }
    }

    public static <T> T xmlReader(Class<T> entityClass, String xmlSourcePath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(entityClass);
            File sourceFile = new File(xmlSourcePath);
            File schemaFile = new File("src/xproject/infrastructure/productPreloved.xsd");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            T result = (T) unmarshaller.unmarshal(sourceFile);
//
//            SchemaFactory sf = SchemaFactory.newInstance(
//                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(schemaFile);
//            
//            Validator validator = schema.newValidator();
//            InputSource inputFile = new InputSource(new BufferedReader(
//                    new FileReader(sourceFile)));
//            validator.validate(new SAXSource(inputFile));
            
            // Set validator
            unmarshaller.setEventHandler(new ProductValidationHandeler());

            return result;
        } catch (JAXBException ex) {
            ex.printStackTrace();
//        } catch (SAXException ex) {
//            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
