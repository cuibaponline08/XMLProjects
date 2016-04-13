<%-- 
    Document   : _productItemList
    Created on : Apr 13, 2016, 2:48:08 PM
    Author     : cuiba
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set var="xmlDoc" value="${requestScope.xmlString}"/>
<c:if test="${not empty xmlDoc}">
    <c:import charEncoding="UTF-8" url="WEB-INF/loadProductItems.xsl" var="xsl"/>
    <x:transform doc="${xmlDoc}" xslt="${xsl}"/>
</c:if>