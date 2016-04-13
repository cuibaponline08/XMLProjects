<%-- 
    Document   : testXSL
    Created on : Apr 12, 2016, 10:44:51 PM
    Author     : phuclh
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TEST XSL</title>
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <c:set var="productsDoc" value="${sessionScope.ALLPRODUCTSSTRING}"/>

        <c:if test="${not empty productsDoc}">
            <c:import charEncoding="UTF-8" var="xslt" url="WEB-INF/pageHeaderFooter.xsl"/>
            <x:transform doc="${productsDoc}" xslt="${xslt}"/>
        </c:if>
    </body>
</html>
