/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;



/**
 *
 * @author phuclh
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException{
        crawlData();
    }

    public static void crawlData() throws FailingHttpStatusCodeException, IOException {
//        String webPage_link = "https://www.google.com.vn/";
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        HtmlPage page = webClient.getPage("https://www.thegioididong.com/");
       

        System.out.println(page.asXml());

//
//        List<HtmlElement> productElements = (List<HtmlElement>) 
//                                page.getByXPath("//ul[@class='mobilecate']/li");
//
//        List<HtmlElement> productElements = (List<HtmlElement>) 
                                page.getByXPath("html/body/div[@id='wrapper']/div[@id='container']/div[@id='content']/"
                                        + "div[@id='main']/"
                                        + "div[@id='product-gallery']/"
                                        + "div[@id='woocommerce']/"
                                        + "ul[@class='products']/"
                                        + "li");
//        List<HtmlElement> productElements = (List<HtmlElement>) 
//                                page.getByXPath("html/body/div/div[3]/div[3]/div[1]/div/div/ul/li[4]/a[1]/h3");
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.newDocument();
//
//            Element root = doc.createElement("products");
//            doc.appendChild(root);
//
//            for (HtmlElement products : productElements) {
//                DomText productName = products.getFirstByXPath(".//text()");
////                DomText productName = products.getFirstByXPath(".//a/h3/text()");
////                DomText productPrice = products.getFirstByXPath(".//a/strong/text()");
////                DomAttr productImage = products.getFirstByXPath(".//a/img/@src");
////                DomAttr productLink = products.getFirstByXPath(".//a/@href");
////
//                Element productElement = doc.createElement("product");
//                Element productNameElement = doc.createElement("name");
//                productNameElement.setTextContent(productName.toString());
////
////                Element productPriceElement = doc.createElement("price");
////                productPriceElement.setTextContent(productPrice.toString());
////
////                Element productImageElement = doc.createElement("image");
////                productImageElement.setTextContent(productImage.getValue());
////
////                Element productLinkElement = doc.createElement("link");
////                productLinkElement.setTextContent(webPage_link + 
////                                                    productLink.getValue());
////                root.appendChild(productElement);
////                productElement.appendChild(productNameElement);
////                productElement.appendChild(productPriceElement);
////                productElement.appendChild(productImageElement);
////                productElement.appendChild(productLinkElement);
//            }
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer trans = tf.newTransformer();
//            trans.setOutputProperty(OutputKeys.INDENT, "yes");
//
//            StreamResult result = new StreamResult(
//                    new File(".\\src\\webcrawler\\products.xml"));
//            DOMSource source = new DOMSource(doc);
//            trans.transform(source, result);
//            System.out.println(productElements.size() + 
//                                            " products is saved to XML file!");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}
