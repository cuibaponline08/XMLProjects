/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.servlet;

import com.myapp.Ultil.DOMUlti;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Gunner
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private static String path = "WEB-INF/students.xml";

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
            String id = request.getParameter("txtID");
            String firstName = request.getParameter("txtFirstName");
            String lastName = request.getParameter("txtLastName");
            String className = request.getParameter("txtClassName");
            String birthDay = request.getParameter("txtDOB");
            String realpath = getServletContext().getRealPath("/");
            String xmlPath = realpath + path;
            System.out.println("update");
            Document doc = DOMUlti.ParseDOMFromFile(xmlPath);
            XPath xpath = DOMUlti.createXPATHObj();
            String xpathExp = "//student[@id='" + id + "']";
            try {
                Node student = (Node) xpath.evaluate(xpathExp, doc, XPathConstants.NODE);
                NodeList childs = student.getChildNodes();
                for (int i = 0; i < childs.getLength(); i++) {
                    Node tmp = childs.item(i);
                    if (tmp.getNodeName().equals("firstname")) {
                        tmp.setTextContent(firstName);
                    } else if (tmp.getNodeName().equals("lastname")) {
                        tmp.setTextContent(lastName);
                    } else if (tmp.getNodeName().equals("class")) {
                        tmp.setTextContent(className);
                    } else if (tmp.getNodeName().equals("dateofbirth")) {
                        tmp.setTextContent(birthDay);
                    }

                }

            } catch (XPathExpressionException ex) {
                Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            DOMUlti.tranformDOMtoStream(doc, xmlPath);
            String url = "CenterServlet?btnAction=Search&txtSearch=";
            response.sendRedirect(url);
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
