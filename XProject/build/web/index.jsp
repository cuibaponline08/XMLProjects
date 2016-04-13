<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta content="charset=UTF-8">
        <title>XProject</title>
        <link rel="stylesheet" href="css/xproject.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
        <script src="js/xproject.js"></script>
    </head>
    <body id="body">
        <div class="body-main">
            <div class="wrapper" id="search">
                <section class="row">
                    <input id="txtSearch" onkeypress="searchProduct()" type="text" placeholder="Search..."/>
                </section>
            </div>
            <div class="wrapper" id="main-content">

            </div>
            <div class="wrapper" id="footer">

            </div>
        </div>
        <input type="hidden" value='' id='currentProductDetail' />
    </body>
</html>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script>
    $('document').ready(function () {
        loadProducts(1);
        loadPages();
    });

</script>

<!-- USING XML data from GOOGLE API combine with JSP -->