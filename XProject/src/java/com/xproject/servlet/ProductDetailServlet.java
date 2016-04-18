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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ProductDetailServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private static String lazyLoadUrl = "https://sa.gumtree.com/1/resources/assets/rwd/images/orphans/a37b37d99e7cef805f354d47.noimage_thumbnail.png";

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

        String sId = request.getParameter("productId");
        String currentLocation = request.getParameter("currentLocation");

        int productId = ANCParser.parseInt(sId);
        ProductDTO product = productService.getProductById(productId);

        if (product == null) {
//            // Cannot find product match the productId requested
//            // ==> return homepages
//            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//            rd.forward(request, response);
        } else {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            //<editor-fold defaultstate="collapsed" desc="Generate Header">
            response.getWriter().println("<section class=\"row product-detail box-shadow\">");
            response.getWriter().println("<div id=\"watch-header\" class=\"yt-card yt-card-has-padding product-detail\">"
                    + "     <div>"
                    + "            <h1 class='product-name'>"
                    + product.getProductName()
                    + "           </h1>"
                    + "     </div>"
                    + "<div class='location'><h4 class='location' id='location" + productId + "'>" + product.getLocation() + " </h4>"
                    + "<h4 class='distance' id='distance" + productId + "'></h4></div>"
                    + "<div><h3 class='money'>" + product.getCurrency()
                    + ANCParser.moneyFormat(product.getPrice()) + "</h3></div>"
                    + "</div>");

//            response.getWriter().println("</section>");
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Generate Images">
//            response.getWriter().println("<section class=\"row product-detail\">"
            response.getWriter().println("<div class=\"slider product-detail\">");
            String addingInfomation = "";
            if (!product.getAddingInformation().equals("")) {
                addingInfomation = "<h3 class='title'>Adding Information</h3>"
                        + "<table class='table-info'>";

                for (String info : product.getAddingInformationList()) {
                    String[] tmp = info.split("~");
                    String titleInfo = tmp[0];
                    String textInfo = tmp[1];
                    addingInfomation += "<tr class='row-info'>"
                            + "<td class='title'>"
                            + titleInfo
                            + "</td>"
                            + "<td class='info'>"
                            + textInfo
                            + "</td>"
                            + "</tr>";
                }
                addingInfomation += "</table>";
            }
            String[] productImages = product.getPicUrlList();
// render list images
            for (int i = 0; i < productImages.length; i++) {
                if (productImages[i].equals(lazyLoadUrl)) {
                    continue;
                }
                if (i == 0) {
                    response.getWriter().println("<input type=\"radio\" name=\"slide_switch\" "
                            + "checked=\"checked\" id=\"pic" + i + "\"/>"
                            + "                        <label for=\"pic" + i + "\">"
                            + "<img src=\"" + productImages[i] + "\" width=\"100\"/>"
                            + "                        </label>"
                            + "                        <img src=\"" + productImages[i] + "\"/>");
                } else {
                    response.getWriter().println("<input type=\"radio\" name=\"slide_switch\" "
                            + "id=\"pic" + i + "\"/>"
                            + "                        <label for=\"pic" + i + "\">"
                            + "<img src=\"" + productImages[i] + "\" width=\"100\"/>"
                            + "                        </label>"
                            + "                        <img src=\"" + productImages[i] + "\"/>");
                }
            }

            response.getWriter().println(""
                    + "</div>"
                    + "     <div class='adding-info-area'>" + addingInfomation + "</div>");
//            response.getWriter().println("</section>");
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Generate Description">
//            response.getWriter().println("<section class=\"row product-detail\">");
            response.getWriter().println("<div id=\"watch-content\" class=\"yt-card yt-card-has-padding product-detail\">"
                    + "     <div>"
                    + "            <h3 class='title'>Description</h3>"
                    + "     </div>"
                    + "     <div><p class='description'>" + product.getDescription() + "</p></div>"
                    + "</div>");
//            response.getWriter().println("</section>");
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Generate Source button">
//            response.getWriter().println("<section class=\"row product-detail\">");
            response.getWriter().println(
                    "<button class='button button_active button_center' onclick=\"window.open('"
                    + product.getProductSourceUrl() + "')\" />Go to source page</button>");

            response.getWriter().println("</section>");
//</editor-fold>
        }

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

        processRequest(request, response);
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
