/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.pagecollector;

import com.anc.databaseUtil.DatabaseUtil;
import com.xproject.data.Enum.ProductTypeEnum;
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

                        String productType = productElement.getElementsByAttributeValue(
                                "data-test-element", "advert-type").text().trim();

                        String availability = productElement.getElementsByAttributeValue(
                                "itemprop", "availability").text().trim();
                        boolean isFixedPrice = false;
                        if ("No Offers".equals(availability)) {
                            isFixedPrice = true;
                        }

                        Product product = new Product();
                        //TODO: HardCode
                        product.setCategoryId(1);

                        product.setCustomerAddress(productElement.select(
                                "span.is-location").text());
                        product.setImageSourceUrl(productElement.select(
                                "img.js-defer.media-item.is-media.js-loaded").attr("src"));
                        product.setProductType(productType);

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
                            String addingInformation = "";
                            if (productDetailElements == null) {
                                continue;
                            }

                            String imgUrl = "";
                            for (Element productDetailElement : productDetailElements) {
                                if (productDetailElement != null && !productDetailElement.equals("")) {
                                    productDescription = productDetailElement.
                                            select("#classified-description").text();

                                    Elements imgElements = productDetailElement.getElementsByTag("li");
                                    if (imgElements != null && !imgElements.equals("")) {
                                        int tmpIndex = 0;
                                        for (Element imgElement : imgElements) {
                                            String imgText = imgElement.
                                                    select("img").
                                                    attr("data-src");
                                            if (imgText.equals("")) {
                                                continue;
                                            }

                                            if (tmpIndex == 0) {
                                                imgUrl += imgText;
                                            } else {
                                                imgUrl += "|" + imgText;
                                            }
                                            tmpIndex++;
                                        }
                                    }

                                    Elements addingInfoElements = productDetailElement.getElementsByTag(
                                            "dl");
                                    if (addingInfoElements != null && !addingInfoElements.equals("")) {
                                        int tmpIndex = 0;
                                        for (Element addingInfoElement : addingInfoElements) {
                                            String addingInfoText = addingInfoElement.
                                                    select("span.ellipsis").text();
                                            if (addingInfoText.equals("")) {
                                                continue;
                                            }

                                            if (tmpIndex == 0) {
                                                addingInformation += addingInfoText;
                                            } else {
                                                addingInformation += "|" + addingInfoText;
                                            }
                                            tmpIndex++;
                                        }
                                    }
                                }
                            }

                            product.setImageSourceUrl(imgUrl);
                            product.setDescription(productDescription);
                            product.setAddingInformation(addingInformation);
                            product.setIsFixedPrice(isFixedPrice);
                        }
                        product.setProductDetailUrl(preloved + detailUrl);

                        product.setProductName(productName);
                        product.setProductPrice(productPrice);
                        product.setCurrency(currency);

                        productList.add(product);
                        count++;
                        System.out.println(count);
                    } else {
                        continue;
                    }
                }
            }
            XMLUtil.xmlWriter(products, productDestinationPath);
        }
    }

    private static void insertProductsToDB() {
        String databaseServer = "DUYDTSE61187";
        String databaseInstance = "DUYDT";
        String databaseName = "XProject";
        String username = "sa";
        String password = "123456";

        DatabaseUtil dbUtil = new DatabaseUtil();
        dbUtil.createConnection(databaseServer, databaseInstance, databaseName, username, password);

        Products products = XMLUtil.xmlReader(Products.class, productDestinationPath);
        if (products == null) {
            return;
        }
        
        int count = 0;
        for (Product product : products.getProduct()) {
            int productType = getProductTypeInt(product.getProductType());

            ProductDTO productDTO = new ProductDTO();
            productDTO.setAddingInformation(product.getAddingInformation());
            productDTO.setCategoryId(product.getCategoryId());
            productDTO.setDescription(product.getDescription());
            productDTO.setLocation(product.getCustomerAddress());
            productDTO.setPicUrl(product.getImageSourceUrl());
            productDTO.setPrice(product.getProductPrice());
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductSourceUrl(product.getProductDetailUrl());
            productDTO.setCurrency(product.getCurrency());
            productDTO.setProductType(productType);
            productDTO.setIsFixedPrice(product.isIsFixedPrice());

            //TODO: Hard code
            productDTO.setProductStatus(1);
            productDTO.setCustomerId(1);

            int insertToTable = dbUtil.insertToTable("Product", getNewValues(productDTO));
        }
    }

    private static String[] getNewValues(ProductDTO entity) {
        int a = entity.getProductType();
        String[] result = {"N'" + entity.productName.replaceAll("\"", "\"").replaceAll("'", "''")
            + "'", String.valueOf(entity.productType),
            String.valueOf(entity.price), "N'" + entity.picUrl.replaceAll("\"", "\"").replaceAll("'", "''")
            + "'", String.valueOf(entity.categoryId),
            parseBooleanToString(entity.isFixedPrice), "N'" + entity.description.replaceAll("\"", "\"").replaceAll("'", "''") + "'", "N'"
            + entity.addingInformation.replaceAll("\"", "\"").replaceAll("'", "''") + "'",
            "N'" + entity.location.replaceAll("\"", "\"").replaceAll("'", "''")
            + "'", String.valueOf(entity.productStatus), String.valueOf(entity.customerId), "N'"
            + entity.productSourceUrl.replaceAll("\"", "\"").replaceAll("'", "''") + "'",
            "N'" + entity.currency.replaceAll("\"", "\"").replaceAll("'", "''") + "'"
        };

        return result;
    }
    
    private static String parseBooleanToString(boolean bool){
        return bool ? "1" : "0";
    }

    private static int getProductTypeInt(String productType) {
        int result = ProductTypeEnum.Other.ordinal();
        switch (productType) {
            case "For Sale":
                result = ProductTypeEnum.ForSale.ordinal();
                break;
            case "Wanted":
                result = ProductTypeEnum.Wanted.ordinal();
                break;
            case "To Rent":
                result = ProductTypeEnum.ToRent.ordinal();
                break;
            case "For Loan":
                result = ProductTypeEnum.ForLoan.ordinal();
                break;
            case "Swap":
                result = ProductTypeEnum.Swap.ordinal();
                break;
            case "Event":
                result = ProductTypeEnum.Event.ordinal();
                break;
            case "Service":
                result = ProductTypeEnum.Service.ordinal();
                break;
            case "Lost":
                result = ProductTypeEnum.Lost.ordinal();
                break;
            case "Found":
                result = ProductTypeEnum.Found.ordinal();
                break;
            case "For Stud":
                result = ProductTypeEnum.ForStud.ordinal();
                break;
            default:
                result = ProductTypeEnum.Other.ordinal();
        }
        
        return result;
    }
}
