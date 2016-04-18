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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xeustechnologies.googleapi.spelling.SpellCorrection;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

/**
 *
 * @author Admin
 */
public class SearchProductServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        try {
            int itemInPage = 15 - 1;
            String key = request.getParameter("keyword");
            String pattern1 = "(\\d+)-(\\d+)";
            String pattern2 = "(\\d+)";

            // Create a Pattern object
            Pattern r = Pattern.compile(pattern1);

            // Now create matcher object.
            Matcher m = r.matcher(key.replaceAll(" ", ""));
            List<ProductDTO> list = new ArrayList<ProductDTO>();
            if (m.find()) {
                String[] tmpArray = key.replaceAll(" ", "").split("-");

                float min = ANCParser.parseFloat(tmpArray[0]);
                float max = ANCParser.parseFloat(tmpArray[1]);

                list = productService.searchProductBetween(min, max);
            } else {
                Pattern r2 = Pattern.compile(pattern2);
                Matcher m2 = r2.matcher(key.replaceAll(" ", ""));
                if (m2.find()) {
                    list = productService.searchProductByPrice(ANCParser.parseFloat(key));
                } else {
                    list = productService.searchProductWhere(key);
                }
            }

            int totalItems = list.size();
            int totalPages = list.size() / itemInPage;
            if ((totalItems % itemInPage) > 0) {
                totalPages++;
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("<section class='row row-paging'>");
            response.getWriter().println("<ul id='pagination-items' class='pagination'>");
            int i = 1;
            response.getWriter().println("<li>");
            response.getWriter().print("<button id='page" + i + "' class='page-number bold' onclick='pageClick(" + i + ")'>" + i + "</button>");
            response.getWriter().println("</li>");
            for (i = 2; i <= totalPages && i <= 10; i++) {
                response.getWriter().println("<li>");
                response.getWriter().print("<button id='page" + i + "' class='page-number' onclick='pageClick(" + i + ")'>" + i + "</button>");
                response.getWriter().println("</li>");
            }
            response.getWriter().println("</ul>");
            response.getWriter().println("<input id='totalPages' type='hidden' value='" + totalPages + "'>");
            response.getWriter().println("<input id='currentPage' type='hidden' value='" + 1 + "'>");
            response.getWriter().println("<input id='firstPage' type='hidden' value='" + 1 + "'>");
            response.getWriter().println("<input id='lastPage' type='hidden' value='" + (i - 1) + "'>");
            response.getWriter().println("</section>");

            processRequest(request, response);
        } finally {
            out.close();
        }
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

        PrintWriter out = response.getWriter();
        try {
            int itemInPage = 15 - 1;

            String sCurrentPage = request.getParameter("currentPage");
            int pageNumber = ANCParser.parseInt(sCurrentPage);
            if (pageNumber == 0) {
                pageNumber = 1;
            }
            
            String key = request.getParameter("keyword");
            String pattern1 = "(\\d+)-(\\d+)";
            String pattern2 = "(\\d+)";

            // Create a Pattern object
            Pattern r = Pattern.compile(pattern1);

            // Now create matcher object.
            Matcher m = r.matcher(key.replaceAll(" ", ""));
            List<ProductDTO> list = new ArrayList<ProductDTO>();
            if (m.find()) {
                String[] tmpArray = key.replaceAll(" ", "").split("-");

                float min = ANCParser.parseFloat(tmpArray[0]);
                float max = ANCParser.parseFloat(tmpArray[1]);

                list = productService.searchProductBetween(min, max);
            } else {
                Pattern r2 = Pattern.compile(pattern2);
                Matcher m2 = r2.matcher(key.replaceAll(" ", ""));
                if (m2.find()) {
                    list = productService.searchProductByPrice(ANCParser.parseFloat(key));
                } else {
                    list = productService.searchProductWhere(key);
                }
            }

            int totalItems = list.size();

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                    + "<products xmlns=\"http://xml.netbeans.org/schema/products\">";
            for (int i = (itemInPage * (pageNumber - 1)); i < totalItems
                    && i <= (itemInPage * (pageNumber)); i++) {
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
            request.setAttribute("xmlString", xmlString.replaceAll("&", "&amp;"));
            RequestDispatcher rd = request.getRequestDispatcher("_productItemList.jsp");
            rd.forward(request, response);
        } finally {
            out.close();
        }
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
