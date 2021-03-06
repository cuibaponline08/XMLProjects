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
            response.getWriter().println("<section class=\"row\">");

            response.getWriter().println("<div id=\"watch-header\" class=\"yt-card yt-card-has-padding\">"
                    + "     <div>"
                    + "            <h1>"
                    + product.getProductName()
                    + "           </h1>"
                    + "     </div>"
                    + "<div><h3>" + product.getCurrency()
                    + ANCParser.moneyFormat(product.getPrice()) + "</h3></div>"
                    + "</div>");

            response.getWriter().println("</section>");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Generate Images">
            response.getWriter().println("<section class=\"row\">"
                    + "<div class=\"slider\">");

            String[] productImages = product.getPicUrlList();
// render list images
            for (int i = 0; i < productImages.length; i++) {
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

            response.getWriter().println("</div>"
                    + "</section>");
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Generate Description">
            String addingInfomation = "<table>";

            for (String info : product.getAddingInformationList()) {
                addingInfomation += "<tr>"
                        + "<td>"
                        + info
                        + "</td>"
                        + "</tr>";
            }
            addingInfomation += "</table>";
            response.getWriter().println("<section class=\"row\">");

            response.getWriter().println("<div id=\"watch-content\" class=\"yt-card yt-card-has-padding\">"
                    + "     <div>"
                    + "            <h3>Description</h3>"
                    + "     </div>"
                    + "     <div><p>" + product.getDescription() + "</p></div>"
                    + "     <div><h3>Adding Information</h3></div>"
                    + "     <div>" + addingInfomation + "</div>"
                    + "</div>");
            response.getWriter().println("</section>");
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Generate Adding Info">
            response.getWriter().println("<section class=\"row\">");

            response.getWriter().println(
                    "<input type=\"button\" value='hello' class=\"button_active\" onclick=\"location.href='"
                    + product.getProductSourceUrl() + "';\" />");

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
