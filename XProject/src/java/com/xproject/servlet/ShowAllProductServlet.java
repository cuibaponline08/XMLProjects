/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.servlet;

import com.xproject.dto.ProductDTO;
import com.xproject.infrastructure.ANCParser;
import com.xproject.service.ProductService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ShowAllProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // this method will return all pages
        int itemInPage = 15;
        String condition = request.getParameter("condition");

         List<ProductDTO> list = new ArrayList<ProductDTO>();
        
        if (!"".equals(condition)) {
            productService.getAllProductsWithOnePic(condition.trim());
        }
        
        int totalItems = list.size();
        int totalPages = list.size() / itemInPage;
        if ((totalItems % itemInPage) > 0) {
            totalPages++;
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println("<section class='row row-paging'>");
        response.getWriter().println("<ul class='pagination'>");
        for (int i = 1; i <= totalPages; i++) {
            response.getWriter().println("<li>");
            response.getWriter().print("<a onclick='loadProducts(" + i + ")'>" + i + "</a>");
            response.getWriter().println("</li>");
        }
        response.getWriter().println("</ul>");
        response.getWriter().println("</section>");

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//          this method will return  products list in selected page
        int itemInPage = 15 - 1;

        String sCurrentPage = request.getParameter("currentPage");
        String condition = request.getParameter("condition");
        int pageNumber = ANCParser.parseInt(sCurrentPage);
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        
        if (!"".equals(condition)) {
            productService.getAllProductsWithOnePic(condition.trim());
        }
        
        int totalItems = list.size();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<products xmlns=\"http://xml.netbeans.org/schema/products\">";
        for (int i = (itemInPage * (pageNumber - 1)); i < totalItems
                && i <= (itemInPage * (pageNumber)); i++) {
//for (int i = 0; i < totalItems; i++){
            ProductDTO product = list.get(i);

            xmlString += "<product>"
                    + "        <id>" + product.getProductId() + "</id>"
                    + "        <defaultPic>" + product.getDefaultPic() + "</defaultPic>"
                    + "        <name>" + product.getProductName() + "</name>"
                    + "        <currency>" + product.getCurrency() + "</currency>"
                    + "        <price>" + ANCParser.moneyFormat(product.getPrice()) + "</price>"
                    + "        <productType>" + ANCParser.parseProductType(
                            product.getProductType()) + "</productType>"
                    + "    </product>";
        }

        xmlString += "</products>";
        System.out.println(xmlString);
        request.setAttribute("xmlString", xmlString.replaceAll("&", "&amp;"));
        RequestDispatcher rd = request.getRequestDispatcher("_productItemList.jsp");
        rd.forward(request, response);
//<editor-fold defaultstate="collapsed" desc="unused code">

//        // this method will return  products list in selected page
//        int itemInPage = 15 - 1;
//
//        String sCurrentPage = request.getParameter("currentPage");
//        int pageNumber = ANCParser.parseInt(sCurrentPage);
//        if (pageNumber == 0) {
//            pageNumber = 1;
//        }
////        String orderBy = "ProductId";
////        List<ProductDTO> list = productService.getProductsWtih1Pic(itemInPage * (pageNumber - 1), itemInPage);
//        List<ProductDTO> list = productService.getAllProductsWithOnePic();
//        int totalItems = list.size();
//
//        response.setContentType("text/html");
////        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");
//
//        for (int i = (itemInPage * (pageNumber - 1)); i < totalItems &&
//                i <= (itemInPage * (pageNumber)); i++) {
////for (int i = 0; i < totalItems; i++){
//            ProductDTO product = list.get(i);
//
//            response.getWriter().println(
//                    //<editor-fold defaultstate="collapsed" desc="html result">
//
//                    //Product items area
//                    "<div class=\"item\">"
//                    + "<img src='" + product.getDefaultPic() + "' style=\"width: 367px; height: 260px;\"/>"
//                    + "<div class=\"item-overlay\">"
//                    + "    <!--<a href=\"#\" class=\"item-button play\"><i class=\"play\"></i></a>"
//                    + "    <a href=\"#\" class=\"item-button share share-btn\"><i class=\"play\"></i></a>-->"
//                    //                    + "    <div class=\"sale-tag\"><span>" + ANCParser.parseProductType(product.getProductType()) + "</span></div>"
//                    + "</div>"
//                    + "<div class=\"item-content\">"
//                    + "    <div class=\"item-top-content\">"
//                    + "        <div class=\"item-top-content-inner\">"
//                    + "            <div class=\"item-product\">"
//                    + "                <div class=\"item-top-title panel-heading\">"
//                    + "                    <h2 class=\"product-name\"><b>" + product.getProductName() + "</b></h2>"
//                    + "                </div>"
//                    + "            </div>"
//                    + "            <div class=\"item-product-price\">"
//                    + "                <span class=\"price-num\">" + product.getCurrency()
//                    + ANCParser.moneyFormat(product.getPrice()) + "</span>"
//                    + "                    <p class=\"subdescription\">"
//                    + "                    " + ANCParser.parseProductType(product.getProductType()) + ""
//                    + "                </p>"
//                    + "                <!--<p class=\"subdescription\">$36</p>-->"
//                    + "                <!--<div class=\"old-price\"></div>-->"
//                    + "            </div>"
//                    + "        </div>	"
//                    + "    </div>"
//                    + "    <div class=\"item-add-content\">"
//                    + "        <div class=\"item-add-content-inner\">"
//                    + "            <!-- <div class=\"section\">"
//                    + "                    <h4>Sizes</h4>"
//                    + "                    <p>40,41,42,43,44,45</p>"
//                    + "            </div> -->"
//                    + "            <div class=\"section\">"
//                    + "                <span onclick=\"loadProductDetail(" + product.getProductId() + ")\" class=\"btn buy expand\">"
//                    + "View Details</span>"
//                    + "            </div>"
//                    + "        </div>"
//                    + "    </div>"
//                    + "</div>"
//                    + "                                </div> <!-- end div item -->"
//                    + "<div id='product" + product.getProductId() + "' class='product-detail-info'></div>"
//            //</editor-fold>
//            );
//        }
//
//        response.getWriter().println("</div> <!-- end div product -->"
//                + "                    </div>"
//                + "                </section>"
//                //Paging area
//                + "<section class=\"row\">"
//                + "                    <div id=\"pageItems\">"
//                + "                        <!-- item will load by AJAX -->"
//                + "                    </div>"
//                + "                </section>");
//
//        processRequest(request, response);
//</editor-fold>
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
