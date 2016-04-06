/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.pagecollector;

import com.anc.databaseUtil.DatabaseUtil;
import com.xproject.dto.ProductDTO;
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

    private static String productDetailDescriptionPath
            = "span.u-display--b.classified__description.classified__meta.js-toggle-content";
//    private static String `

    public static void main(String[] args) {
//        getProducts();
        insertProductsToDB();
    }

    private static void getProducts() {
        Products products = new Products();
        int count = 1;
        int totalPage = 2;

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
                            Elements productDetailElements = XMLUtil.getElements(preloved + detailUrl,
                                    "div#classified-p-content");
                            String productDescription = "";
                            for (Element productDetailElement : productDetailElements) {
                                if (productDetailElement != null && !productDetailElement.equals("")) {
                                    productDescription = productDetailElement.
                                            select("#classified-description").text();

                                }
                            }
                            Elements imgElements = XMLUtil.getElements(preloved + detailUrl, "li.js-media-carousel-item.classified__media__item");
                            String imgUrl = "";
                            int a = imgElements.size();
                            if (imgElements != null && !imgElements.equals("")) {
                                for (Element imgElement : imgElements) {
                                    imgUrl += imgElement.
                                            select("img").
                                            attr("data-src") + "|";
                                }
                            }

                            product.setImageSourceUrl(imgUrl);
                            product.setDescription(productDescription);
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

    private static void insertProductsToDB() {
        String databaseServer = "CUIBAP";
        String databaseInstance = "DUYDT";
        String databaseName = "XProject";
        String username = "sa";
        String password = "123456";

        DatabaseUtil dbUtil = new DatabaseUtil();
        dbUtil.createConnection(databaseServer, databaseInstance, databaseName, username, password);

        Products products = XMLUtil.xmlReader(Products.class, productDestinationPath);
        int count = 0;
        for (Product product : products.getProduct()) {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setAddingInformation("");
            productDTO.setCategoryId(product.getCategoryId());
            productDTO.setCustomerId(1);
            productDTO.setDescription(product.getDescription());
            productDTO.setIsFixedPrice(true);
            productDTO.setLocation(product.getCustomerAddress());
            productDTO.setPicUrl(product.getImageSourceUrl());
            productDTO.setPrice(product.getProductPrice());
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductStatus(1);
            productDTO.setProductType(1);

            int insertToTable = dbUtil.insertToTable("Product", getNewValues(productDTO));
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    private static String[] getNewValues(ProductDTO entity) {
        int a = entity.getProductType();
        String aa = String.valueOf(a);
        String[] result = {"'" + entity.productName + "'", String.valueOf(entity.productType),
            String.valueOf(entity.price), "'" + entity.picUrl + "'", String.valueOf(entity.categoryId),
            "1", "'" + entity.description + "'", "'" + entity.addingInformation + "'",
            "'" + entity.location + "'", String.valueOf(entity.productStatus), String.valueOf(entity.customerId)
        };

        return result;
    }
}
