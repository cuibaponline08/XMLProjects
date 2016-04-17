<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta content="charset=UTF-8">
        <title>XProject</title>
        <link rel="stylesheet" href="css/xproject.css">
        <script src="js/xproject.js"></script>
    </head>
    <body id="body" onload="getLocation()">
        <div class="body-main">
            <div class="wrapper" id="logo">
                <img id="main-logo" class="main-logo" src="http://i.imgur.com/KvxrF5c.png" />

                <input lang="en" spellcheck="true" autocorrect="true" autocomplete="true" class="text-search-empty" id="txtSearch" onkeypress="searchProduct(event)" type="text" placeholder="Search..."/>
            </div>
            <div class="wrapper" id="main-content">

            </div>
            <div class="wrapper" id="footer">

            </div>
        </div>
        <input type="hidden" value='' id='currentProductDetail' />
        <input type="hidden" value='' id='currentLocation' />
    </body>
</html>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB0iJvMLVkPYti5b_GaIPutP5IvRQat6B8">
</script>
