/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.pagecollector;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.netbeans.xml.schema.products.Product;
import org.netbeans.xml.schema.products.Products;
import xproject.utils.XMLUtil;

/**
 *
 * @author cuiba
 */
public class PrelovedPageCollector {

    private static String preloved = "http://www.preloved.co.uk";
    private static String sourceUrl = "http://www.preloved.co.uk/adverts/list/3316/motorcycles.html";
    private static String selectPath = "ul#search-results-list";
//    private static String selectCategoryPath = "div.menu_block.geosection > ul";
    private static String selectProductPath = "li.search-result";
//    private static String categoryDestinationPath = "src/xproject/xml/category.xml";
    private static String productDestinationPath = "src/xproject/xml/productPreloved.xml";

    private static String gotoPageUrl = "?page=";

    private static String productDetailDescriptionPath = 
            "span.u-display--b.classified__description.classified__meta.js-toggle-content";
//    private static String `

    public static void main(String[] args) {
        getProducts();
    }

    private static void getProducts() {
        Products products = new Products();
        int count = 1;
        int totalPage = 87;

        List<String> productDetailUrlList = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {
            Elements productElements = XMLUtil.getElements(sourceUrl + gotoPageUrl + i, selectProductPath);

            if (productElements != null) {
                List<Product> productList = products.getProduct();

                for (Element productElement : productElements) {
                    if (!productElement.equals("")) {
                        float productPrice = 0;

                        String priceString = productElement.getElementsByAttributeValue("itemprop", "price").
                                text().trim();
                        String priceWithoutCurrency = priceString.
                                replace("£", "").
                                replace("€", "").
                                replace(".", "").trim();

                        String currency = "";
                        if (!priceString.equals("")) {
                            currency = String.valueOf(priceString.charAt(0));
                        }

                        if (priceWithoutCurrency != null && !priceWithoutCurrency.equals("")) {
                            productPrice = Float.valueOf(priceWithoutCurrency);
                        }

                        Product product = new Product();
                        product.setCategoryId(1);
                        product.setCustomerAddress(productElement.select(
                                "span.is-location").text());
                        product.setImageSourceUrl(productElement.select(
                                "img.js-defer.media-item.is-media.js-loaded").attr("src"));

                        product.setProductId(count);
                        String productName = productElement.select(
                                "a.search-result__title.is-title span").text();
                        if (productName.isEmpty()) {
                            continue;
                        }

                        String detailUrl = productElement.select(
                                "a.search-result__media").attr("href");
                        if (detailUrl.trim().equals("")) {
                            detailUrl = productElement.select("a.search-result__media-link").attr("href");
                        }
                        if (!detailUrl.trim().equals("")) {
                            Elements productDetailElements = XMLUtil.
                                    getElements(detailUrl,
                                            "");

                            for (Element productDetailElement : productDetailElements) {
                                if (!productDetailElement.equals("")) {

                                }
                            }
                        }
                        product.setProductDetailUrl(preloved + detailUrl);

                        product.setProductName(productName);
                        product.setProductPrice(productPrice);
                        product.setCurrency(currency);

                        productList.add(product);
                        count++;
                    } else {
                        continue;
                    }
                }
            }
            XMLUtil.xmlWriter(products, productDestinationPath);
        }
    }
}
