/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegioirubik.pagecollector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author Admin
 */
public class ThegioirubikPageCollector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        // TODO code application logic here

        try {
            org.jsoup.nodes.Document sourceDoc = Jsoup.connect("http://www.chotot.vn/tp-ho-chi-minh/mua-ban-xe-may#").get();
            Elements products = sourceDoc.select("div.menu_block.geosection > ul");
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();

            org.w3c.dom.Element root = doc.createElement("products");
            doc.appendChild(root);
            for (int i = 0; i < products.first().children().size(); i++) {
                org.jsoup.nodes.Element product = products.first().child(i);
                String geo = product.select("li > span.nohistory > a").text();
                String productName = product.select("a.ad-subject").text();
                String imageUrl = product.select("img.thumbnail").attr("src");
                String price = product.select("div.ad-price").text();
                String area = product.select("div.ad-price").text();

                org.w3c.dom.Element productElement = doc.createElement("product");

                org.w3c.dom.Element productNameElement = doc.createElement("productName");
                productNameElement.setTextContent(productName);

                org.w3c.dom.Element productImageUrlElement = doc.createElement("imageUrl");
                productImageUrlElement.setTextContent(imageUrl);

                org.w3c.dom.Element productPriceElement = doc.createElement("productPrice");
                productPriceElement.setTextContent(price);
                
                org.w3c.dom.Element areaElement = doc.createElement("geo");
                productPriceElement.setTextContent(geo);

                root.appendChild(productElement);
                productElement.appendChild(productNameElement);
                productElement.appendChild(productImageUrlElement);
                productElement.appendChild(productPriceElement);
                productElement.appendChild(areaElement);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult(
                    new File("src/com/anc/thegioirubik/products.xml"));
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            System.out.println(products.first().childNodeSize()
                    + " products is saved to XML file!");
            System.out.println(products.first().children().size());
//            }
        } catch (IOException ex) {
            Logger.getLogger(ThegioirubikPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
