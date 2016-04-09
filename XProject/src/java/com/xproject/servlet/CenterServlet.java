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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cuiba
 */
public class CenterServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            List<ProductDTO> list = productService.getAllProductsWithOnePic();

//            request.setAttribute("LIST", list);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } finally {
            out.close();
        }
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

        int itemInPage = 15;

        String sCurrentPage = request.getParameter("currentPage");
        int pageNumber = ANCParser.parseInt(sCurrentPage);
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        String orderBy = "ProductId";
        List<ProductDTO> list = productService.getProductsWtih1Pic();
        int totalItems = list.size();
        int totalPages = list.size() / itemInPage;
        if ((totalItems % itemInPage) > 0) {
            totalPages++;
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        for (int i = (itemInPage * (pageNumber - 1)); i < totalItems && i <= (itemInPage * (pageNumber)); i++) {
            ProductDTO product = list.get(i);
            response.getWriter().println(
                    //<editor-fold defaultstate="collapsed" desc="html result">
                    "<div class=\"item\">\n"
                    + "                                    <img src='" + product.getDefaultPic() + "' style=\"width: 367px; height: 260px;\"/>\n"
                    + "                                    <div class=\"item-overlay\">\n"
                    + "                                        <!--                                    <a href=\"#\" class=\"item-button play\"><i class=\"play\"></i></a>\n"
                    + "                                                                            <a href=\"#\" class=\"item-button share share-btn\"><i class=\"play\"></i></a>-->\n"
                    + "                                        <div class=\"sale-tag\"><span>SALE</span></div>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"item-content\">\n"
                    + "                                        <div class=\"item-top-content\">\n"
                    + "                                            <div class=\"item-top-content-inner\">\n"
                    + "                                                <div class=\"item-product\">\n"
                    + "                                                    <div class=\"item-top-title panel-heading\">\n"
                    + "                                                        <h2 class=\"product-name\"><b>" + product.getProductName() + "</b></h2>\n"
                    + "                                                        <!--                                                    <p class=\"subdescription panel-heading\">\n"
                    + "                                                        " + product.getDescription() + "\n"
                    + "                                                    </p>-->\n"
                    + "                                                    </div>\n"
                    + "                                                </div>\n"
                    + "                                                <div class=\"item-product-price\">\n"
                    + "                                                    <span class=\"price-num\">" + product.getPrice() + "</span>\n"
                    + "                                                    <!--<p class=\"subdescription\">$36</p>-->\n"
                    + "                                                    <!--<div class=\"old-price\"></div>-->\n"
                    + "                                                </div>\n"
                    + "                                            </div>	\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"item-add-content\">\n"
                    + "                                            <div class=\"item-add-content-inner\">\n"
                    + "                                                <!-- <div class=\"section\">\n"
                    + "                                                        <h4>Sizes</h4>\n"
                    + "                                                        <p>40,41,42,43,44,45</p>\n"
                    + "                                                </div> -->\n"
                    + "                                                <div class=\"section\">\n"
                    + "                                                    <a href=\"#\" class=\"btn buy expand\">Chi tiết</a>\n"
                    + "                                                </div>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div> <!-- end div item -->"
            //</editor-fold>
            );
        }

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
