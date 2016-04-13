<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:pro="http://xml.netbeans.org/schema/products">
    <xsl:output method="html" omit-xml-declaration="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
    <xsl:template match="pro:categories">
        <form action="CenterServlet">
            <div class="page-header">
                <div class="main-header">
                    <h1 id="page-logo">
                        <a href="home.jsp">Shop Trái Cây</a>
                    </h1>
                    <h4 id="login">
                        <a href="#">Đăng nhập</a>
                    </h4>
                    <h4 id="register">
                        <a href="#">Đăng kí</a>
                    </h4>
                </div>
                <div class="page-main-menu">
                    <ul class="navigation">
                        <li>
                            <a href="home.jsp">TRANG CHỦ</a>
                        </li>
                        <li>
                            <a href="#">GIỚI THIỆU</a>
                        </li>
                        <li class="selected">
                            <div class="category-dropdown">
                                <a href="CenterServlet?btnAction=ViewAllProduct">SẢN PHẨM</a>
                                <div class="category-dropdown-content">
                                    <xsl:for-each select="pro:category">
                                        <!--<c:url var="ViewProductInCategory" value="CenterServlet">
                                            <c:param name="btnAction" value="ViewProductInCategory"/>
                                            <c:param name="selectedCategory" value="{@name}"/>
                                        </c:url>-->
                                        <input type="text" name="selectedCategory" value="{@name}"/>
                                        <a id="category-dropdown-item" 
                                           href="CenterServlet?btnAction=ViewProductInCategory&amp;selectedCategory={@name}">
                                            <xsl:value-of select="@name"/>
                                        </a>
                                    </xsl:for-each>
                                </div>
                            </div>
                        </li>
                        <li>
                            <a href="#">TIN TỨC</a>
                        </li>
                        <li>
                            <a href="#">HƯỚNG DẪN MUA HÀNG</a>
                        </li>
                        <li>
                            <a href="#">LIÊN HỆ</a>
                        </li>
                        <li>
                            <a href="CenterServlet?btnAction=ViewCart">
                                <img src="image/shopping-cart.png" alt="shopping-cart.png"/>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="page-body">
                <div class="all-product-body">
                    <h2>Sản phẩm</h2>
                    <ul class="all-products-details" id="results">
                        <xsl:for-each select="pro:category">
                            <!--<xsl:template match="pro:products">-->
                                <xsl:for-each select="pro:products/pro:product">
                                    <li class="product-details">
                                        <a class="product-images" 
                                       href="CenterServlet?btnAction=ViewProductDetail&amp;productId={pro:id}">
                                            <img src="{pro:image}" />
                                        </a>
                                        <h3 class="product-titles">
                                            <a href="CenterServlet?btnAction=ViewProductDetail&amp;productId={pro:id}">
                                                <input type="hidden" name="txtProductName" value="{pro:name}"/>
                                                <xsl:value-of select="pro:name"/>
                                            </a>
                                        </h3>
                                        <h4>
                                            <input type="hidden" name="txtProductPrice" value="{pro:price}"/>
                                            <xsl:value-of select="pro:price"/>
                                            <span class="price-unit">VNĐ</span>
                                        </h4>
                                        <xsl:if test="pro:quantity != 0">
<!--                                            <c:url var="AddToCart" value="CenterServlet">
                                                <c:param name="btnAction" value="AddToCart"/>
                                                <c:param name="txtQuantity" value="1"/>
                                                <c:param name="txtProductId" value="${product.id}"/>
                                                <c:param name="txtProductName" value="${product.name}"/>
                                                <c:param name="txtProductPrice" value="${product.price}"/>
                                                <c:param name="txtProductImage" value="${product.image}"/>
                                                <c:param name="txtProductQuantityDB" value="${product.quantity}"/>
                                                <c:param name="txtProductDescription" value="${product.description}"/>
                                                <c:param name="txtProductContent" value="${product.content}"/>
                                                <c:param name="txtProductDateAdded" value="${product.dateadded}"/>
                                                <c:param name="txtProductBoughttimes" value="${product.boughttimes}"/>
                                            </c:url>-->
                                            <a class="add-to-cart-link" href="#">
                                                <div class="add-to-cart">
                                                    <span class="add-to-cart-plus">+</span>
                                                    <span class="add-to-cart-text">THÊM VÀO GIỎ</span>
                                                </div>
                                            </a>
                                        </xsl:if>
                                    </li>
                                </xsl:for-each>
                            <!--</xsl:template>-->
                        </xsl:for-each>
                    </ul>
                </div>
            </div>
        </form>
    </xsl:template>
</xsl:stylesheet>
