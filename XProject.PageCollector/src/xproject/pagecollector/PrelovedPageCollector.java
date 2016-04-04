/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.pagecollector;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.netbeans.xml.schema.products.Product;
import org.netbeans.xml.schema.products.Products;
import xproject.utils.XMLUtil;

/**
 *
 * @author cuiba
 */
public class PrelovedPageCollector {

    private static String sourceUrl = "http://www.preloved.co.uk/adverts/list/3316/motorcycles.html";
    private static String selectPath = "ul#search-results-list";
//    private static String selectCategoryPath = "div.menu_block.geosection > ul";
    private static String selectProductPath = "li.search-result";
//    private static String categoryDestinationPath = "src/xproject/xml/category.xml";
    private static String productDestinationPath = "src/xproject/xml/productPreloved.xml";
    
    private static void getProducts() {
//        Elements productElements = XMLUtil.getElements(sourceUrl, selectProductPath);

        if (productElements != null) {
//            Product categories = new Categories();
////            List<Category> categoryList = categories.getCategory();
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
}
