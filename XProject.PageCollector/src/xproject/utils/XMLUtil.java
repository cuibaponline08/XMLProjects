/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.utils;



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
            Logger.getLogger(XProjectPageCollector.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(XProjectPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> T xmlReader(Class<T> entityClass, String xmlSourcePath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(entityClass);
            File sourceFile = new File(xmlSourcePath);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            T result = (T) unmarshaller.unmarshal(sourceFile);

            return result;
        } catch (JAXBException ex) {
            Logger.getLogger(XProjectPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
