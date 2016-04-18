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
    private static String sourceUrlPreloved = "http://www.preloved.co.uk/adverts/list/3316/motorcycles.html";
    private static String selectPath = "ul#search-results-list";
    private static String selectProductPath = "li.search-result";
    private static String productDestinationPath = "src/xproject/xml/productPreloved.xml";
    private static String gotoPageUrl = "?page=";
    private static String productDetailDescriptionPath
            = "span.u-display--b.classified__description.classified__meta.js-toggle-content";
//    private static String `

    private static String gumTree = "https://www.gumtree.com";
    private static String sourceUrlGumtree = "https://www.gumtree.com/motorbikes-scooters/uk/page";
    // private static String sourceUrlGumtreeLink = "https://www.gumtree.com/motorbikes-scooters/uk/page2?search_time=1460741189897";
    private static String selectPathGumtree1 = "ul#clearfix border-t";
    private static String selectProductPathGumtree = "ul.clearfix.list-listing-mini  li";
    private static String productDetailDescriptionPathGumtree = "a.listing-link";
    private static String productDestinationPathGumtree = "src/xproject/xml/productGumtree.xml";

    static String databaseServer = "DUYDTSE61187";
    static String databaseInstance = "DUYDT";
    static String databaseName = "XProject";
    static String username = "sa";
    static String password = "123456";

    public static void main(String[] args) {
    //        getProductFromPreloved();
    //        getProductFromGumtree();
        deleteTableContent();
        insertProductsToDB(productDestinationPathGumtree);
        insertProductsToDB(productDestinationPath);
    }

    private static void getProductFromPreloved() {
        Products products = new Products();
        int count = 1;
        int totalPage = 89;

        List<String> productDetailUrlList = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {
            Elements productElements = XMLUtil.getElements(sourceUrlPreloved + gotoPageUrl + i, selectProductPath);

            if (productElements != null) {
                List<Product> productList = products.getProduct();

                for (Element productElement : productElements) {
                    if (!productElement.equals("")) {
                        float productPrice = 0;

                        String priceString = productElement.getElementsByAttributeValue("itemprop", "price").
                                text().trim();
                        String priceWithoutCurrency = priceString.
                                replace(".00", "").
                                replaceAll(",", "").
                                replace("£", "").
                                replace("€", "")
                                .trim();

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
                                            String addingInfoTitle = addingInfoElement.
                                                    select("dt span.ellipsis").text();
                                            String addingInfoText = addingInfoElement.
                                                    select("dd span.ellipsis").text();
                                            if (addingInfoText.equals("")) {
                                                continue;
                                            }

                                            if (tmpIndex == 0) {
                                                addingInformation += addingInfoTitle + "~" + addingInfoText;
                                            } else {
                                                addingInformation += "|" + addingInfoTitle + "~" + addingInfoText;
                                            }
                                            tmpIndex++;
                                        }
                                    }
                                }
                            }

                            if ("".equals(imgUrl.trim())) {
                                imgUrl = "http://frankmedilink.com//wp-content/uploads/2013/11/no-preview-big1.jpg";
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

    private static void getProductFromGumtree() {
        Products products = new Products();
        int count = 1;
        int totalPage = 50;

        List<String> productDetailUrlList = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {
            Elements productElements = XMLUtil.getElements(sourceUrlGumtree + i, selectProductPathGumtree);

            if (productElements != null) {
                List<Product> productList = products.getProduct();

                for (Element productElement : productElements) {
                    if (!productElement.equals("")) {
                        String detailUrl = productElement.select("a.listing-link").attr("href");
                        Elements productDetailElements = XMLUtil.getElements(gumTree + detailUrl,
                                "main.grid-row.space-ptm");
                        if (productDetailElements == null) {
                            continue;
                        }

                        for (Element productDetailElement : productDetailElements) {
                            float productPrice = 0;
                            String productName = productDetailElement.select("div.grid-col-12.grid-col-l-8 > header > h1").text();
                            String address = productDetailElement.select("div.grid-col-12.grid-col-l-8 > header > strong").text();
                            String priceString = productDetailElement.select(""
                                    + "span.h1.set-right.space-mvn strong").text().trim();

                            String priceWithoutCurrency = priceString.
                                    replace(".00", "").
                                    replaceAll(",", "").
                                    replace("£", "").
                                    replace("€", "")
                                    .trim();

                            String currency = "";
                            if (!priceString.equals("")) {
                                currency = String.valueOf(priceString.charAt(0));
                            }

                            if (priceWithoutCurrency != null && !priceWithoutCurrency.equals("")) {
                                productPrice = Float.valueOf(priceWithoutCurrency);
                            }

                            Elements imageElements = productDetailElement.select("ul li");

                            String imgUrl = "";
                            int tmpIndex = 0;
                            // Get all image
                            for (Element imgElement : imageElements) {
                                String imgText = imgElement.
                                        select("img").
                                        attr("src");

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
                            int posted = 0;
                            String addingInfo = "";
                            if ("".equals(imgUrl.trim())) {
                                imgUrl = "http://frankmedilink.com//wp-content/uploads/2013/11/no-preview-big1.jpg";
                            }
                            
                            tmpIndex = 0;
                            // find all Adding information
                            Elements infomationElements = productDetailElement.select("div.grid-col-12.grid-col-l-6");
                            for (Element infomationElement : infomationElements) {
                                Elements titleElements = infomationElement.getElementsByTag("dt");
                                Elements textElements = infomationElement.getElementsByTag("dd");
                                for (int j = 0; j < titleElements.size() && j < textElements.size(); j++) {
                                    // if not posted time => add to addingInfo
                                    // else if posted time => parse to seconds and save to posted
                                    String infoTitle = titleElements.get(j).text();
                                    String infoText = textElements.get(j).text();
                                    if (!"Posted".equals(infoTitle)) {
                                        if (tmpIndex == 0) {
                                            addingInfo += infoTitle + "~" + infoText;
                                        } else {
                                            addingInfo += "|" + infoTitle + "~" + infoText;
                                        }
                                        tmpIndex++;
                                    } else if (!infoText.equals("")) {
                                        String sTime = infoText.split(" ")[0];
                                        if (sTime.contains("Just")) {
                                            posted = 60 * 60 * 24;
                                        } else {
                                            int time = Integer.parseInt(sTime);
                                            if (infoText.contains("sec")) {
                                                posted = 60 * 60 * 24;
                                            } else if (infoText.contains("min")) {
                                                posted = (60 * 60 * 24);
                                            } else if (infoText.contains("hour")) {
                                                posted = (60 * 60 * 24);
                                            } else if (infoText.contains("day")) {
                                                posted = (time * 60 * 60 * 24);
                                            } else if (infoText.contains("month")) {
                                                posted = (time * 60 * 60 * 24 * 30);
                                            } else if (infoText.contains("year")) {
                                                posted = (time * 60 * 60 * 24 * 365);
                                            }
                                        }
                                    }
                                }
                            }

                            String productDescription = productDetailElement.select("p.ad-description").text();

                            Product product = new Product();
                            product.setAddingInformation(addingInfo);
                            product.setCategoryId(2);
                            product.setCurrency(currency);
                            product.setCustomerAddress(address);
                            product.setDescription(productDescription);
                            product.setImageSourceUrl(imgUrl);
                            product.setIsFixedPrice(false);
                            product.setPostted(posted);
                            product.setProductDetailUrl(gumTree + detailUrl);
                            product.setProductName(productName);
                            product.setProductPrice(productPrice);
                            product.setProductType("For Sale");
                            productList.add(product);
                        }
                    } else {
                        continue;
                    }
                }
            }
            XMLUtil.xmlWriter(products, productDestinationPathGumtree);
        }
    }

    private static void insertProductsToDB(String sourcePath) {

        DatabaseUtil dbUtil = new DatabaseUtil();
        dbUtil.createConnection(databaseServer, databaseInstance, databaseName, username, password);

        Products products = XMLUtil.xmlReader(Products.class, sourcePath);
        if (products == null) {
            return;
        }

        int count = 0;
        for (Product product : products.getProduct()) {
            boolean isValid = XMLUtil.validateJAXBObject(product, "src/xproject/infrastructure/product.xsd");
            if (!isValid) {
                continue;
            }
            int productType = getProductTypeInt(product.getProductType());

            ProductDTO productDTO = new ProductDTO();
            productDTO.setAddingInformation(product.getAddingInformation());
            productDTO.setCategoryId(product.getCategoryId());
            productDTO.setDescription(product.getDescription());
            productDTO.setLocation(product.getCustomerAddress());
            productDTO.setPicUrl(product.getImageSourceUrl());
            productDTO.setPrice(product.getProductPrice());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductSourceUrl(product.getProductDetailUrl());
            productDTO.setCurrency(product.getCurrency());
            productDTO.setProductType(productType);
            productDTO.setIsFixedPrice(product.isIsFixedPrice());
            productDTO.setPosted(product.getPostted());

            //TODO: Hard code
            productDTO.setProductStatus(1);
            productDTO.setCustomerId(1);

            int insertToTable = dbUtil.insertToTable("Product", getNewValues(productDTO));
        }
    }

    private static void deleteTableContent() {
        DatabaseUtil dbUtil = new DatabaseUtil();
        dbUtil.createConnection(databaseServer, databaseInstance, databaseName, username, password);

        dbUtil.deleteAllTableContent();
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
            "N'" + entity.currency.replaceAll("\"", "\"").replaceAll("'", "''") + "'",
            String.valueOf(entity.posted)};

        return result;
    }

    private static String parseBooleanToString(boolean bool) {
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

//    WITH CTE AS(
//   SELECT ProductName, Price,
//       RN = ROW_NUMBER()OVER(PARTITION BY ProductName ORDER BY ProductName)
//   FROM dbo.Product
//)
//DELETE FROM CTE WHERE RN > 1
}
