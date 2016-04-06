/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.servlet;

import com.demo.DTO.Rubik;
import com.demo.DTO.Student;
import com.myapp.Ultil.DOMUlti;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Gunner
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private static String path = "WEB-INF/products.xml";

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
            String searchParam = request.getParameter("txtSearch");
            String realPath = getServletContext().getRealPath("/");
            String xmlPath = realPath + path;
            Document doc = DOMUlti.ParseDOMFromFile(xmlPath);
            XPath xpath = DOMUlti.createXPATHObj();
            System.out.println(searchParam);
            String xpathExp = "//product[contains(productName,'a')]";
            
            String productName = "";
            String productPrice = "";
            String imageUrl = "";
            
            
//            String firstname = "";
//            String lastname = "";
//            String className = "";
//            String password = "";
//            String dob = "";
//            String id = "";
            try {
                NodeList result = (NodeList) xpath.evaluate(xpathExp, doc, XPathConstants.NODESET);
                List<Rubik> rubikList = new ArrayList<Rubik>();
                for (int i = 0; i < result.getLength(); i++) {
                    Node rubik = result.item(i);
                    NodeList childNode = rubik.getChildNodes();
                    for (int j = 0; j < childNode.getLength(); j++) {
                        Node tmp = childNode.item(j);
                        if (tmp.getNodeName().equals("productName")) {
                            productName = tmp.getTextContent();
                                                System.out.println(productName);
                        } else if (tmp.getNodeName().equals("imageUrl")) {
                            imageUrl = tmp.getTextContent();
                        } else if (tmp.getNodeName().equals("productPrice")) {
                            productPrice = tmp.getTextContent();
                        }
                    }
                    Rubik newRubik = new Rubik(productName, imageUrl, productPrice);
                    rubikList.add(newRubik);
                }
                request.setAttribute("rubiks", rubikList);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);

            } catch (XPathExpressionException ex) {
                Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
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
