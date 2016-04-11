/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.pagecollector;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import xproject.utils.XMLUtil;

/**
 *
 * @author Admin
 */
public class XProjectPageCollector {

    private static String sourceUrl = "http://www.chotot.vn/tp-ho-chi-minh/mua-ban-xe-may#";
    private static String selectPath = "div.listing_thumbs";
    private static String selectCategoryPath = "div.menu_block.geosection > ul";
    private static String selectProductPath = "div.listing_thumbs div.chotot-list-row";
    private static String categoryDestinationPath = "src/xproject/xml/category.xml";
    private static String productDestinationPath = "src/xproject/xml/product.xml";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            getCategories();
            
            getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getCategories() {
//        Elements categoryElements = XMLUtil.getElements(sourceUrl, selectCategoryPath);
//
//        if (categoryElements != null) {
//            Categories categories = new Categories();
//            List<Category> categoryList = categories.getCategory();
//
//            String categorySelectPath = "li > span.nohistory > a";
//            BigInteger count = BigInteger.ONE;
//            for (Element categoryNode : categoryElements.first().children()) {
//                String categoryName = categoryNode.select(categorySelectPath).text();
//                String categorySourcePath = categoryNode.select(categorySelectPath).attr("href");
//
//                if (categoryName.equals("")) {
//                    continue;
//                } else {
//                    Category category = new Category();
//                    category.setCategoryId(count);
//                    category.setCategoryName(categoryName);
//                    category.setCategorySourcePath(categorySourcePath);
//
//                    categoryList.add(category);
//                    count = count.add(BigInteger.ONE);
//                }
//            }
//            XMLUtil.xmlWriter(categories, categoryDestinationPath);
//        }
    }

    private static void getProducts() {
//        Products products = new Products();
//        List<Product> productList = products.getProduct();
//        Categories categories = XMLUtil.xmlReader(Categories.class, categoryDestinationPath);
//
//        List<Category> categoryList = categories.getCategory();
//        BigInteger count = BigInteger.ONE;
//        for (Category category : categoryList) {
//            Elements productElements = XMLUtil.getElements(category.getCategorySourcePath(), selectProductPath);
//            if (productElements != null) {
//                for (Element productElement : productElements) {
//                    BigDecimal productPrice = BigDecimal.ZERO;
//                    String priceWithoutCurrency = productElement.select("div.thumbs_subject > div.ad-price").text().
//                            replace("đ", "").replace(".", "").trim();
//                    
//                    if (priceWithoutCurrency != null && !priceWithoutCurrency.equals("")) {
//                        productPrice = new BigDecimal(priceWithoutCurrency);
//                    }
//
//                    Product product = new Product();
//                    product.setCategoryId(category.getCategoryId());
//                    product.setImageSourceUrl(productElement.select("img.thumbnail").text());
//                    product.setProductId(count);
//                    product.setProductName(productElement.select("div.thumbs_subject a").text());
//                    product.setProductDetailUrl(productElement.select("div.thumbs_subject a").attr("href"));
//                    product.setProductPrice(productPrice);
//                    product.setShortDescription(productElement.select("div.extra_img-b a img").attr("title"));
//                    
//                    productList.add(product);
//                    count = count.add(BigInteger.ONE);
//                }
//            }
//        }
//        XMLUtil.xmlWriter(products, productDestinationPath);
    }
}
