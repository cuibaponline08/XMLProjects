/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.servlet;

import com.myapp.Ultil.DOMUlti;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Gunner
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {

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
            String dob = request.getParameter("txtBirthDay");
            String password = request.getParameter("txtPassword");
            String realPath = getServletContext().getRealPath("/");
            String xmlPath = realPath + path;
            System.out.println(xmlPath);
            Document doc = DOMUlti.ParseDOMFromFile(xmlPath);
            Element student = DOMUlti.createNewElement(doc, "student", null);
            DOMUlti.createNewAttr(student, "id", id);
            Element first = DOMUlti.createNewElement(doc, "firstname", firstName);
            Element last = DOMUlti.createNewElement(doc, "lastname", lastName);
            Element classN = DOMUlti.createNewElement(doc, "class", className);
            Element birthD = DOMUlti.createNewElement(doc, "dateofbirth", dob);
            Element pass = DOMUlti.createNewElement(doc, "password", password);
            student.appendChild(first);
            student.appendChild(last);
            student.appendChild(classN);
            student.appendChild(birthD);
            student.appendChild(pass);
            NodeList nodeLst = doc.getElementsByTagName("students");
            nodeLst.item(0).appendChild(student);
            DOMUlti.tranformDOMtoStream(doc, xmlPath);
            response.sendRedirect("index.jsp");
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
