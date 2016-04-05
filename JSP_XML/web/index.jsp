<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="products" class="com.anc.saxProcessor.DemoSAXProcessor" scope="request"/>
<c:set var="list" value="${products}"/>
<c:if test="${not empty list}">
    <?xml-stylesheet type="text/xsl" href="product.xsl" ?>
    <products>
        <c:forEach var="product" items="${list.products}">
            <product>
                <code>${product.code}</code>
                <productName>${product.productName}</productName>
                <productPrice>${product.productPrice}</productprice>
            </product>
        </c:forEach>
    </products>
</c:if>
