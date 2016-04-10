<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta content="charset=UTF-8">
        <title>Animated hover box text form beneath</title>
        <link rel="stylesheet" href="css/xproject.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
        <script src="js/xproject.js"></script>
    </head>
    <body id="body" class="preload">
        <div class="body-main">
            <div class="wrapper" id="search">
                <section class="row">
                    <input type="text" />
                </section>
            </div>
            <div class="wrapper" id="main-content">

            </div>
            <div class="wrapper" id="footer">

            </div>
        </div>
    </body>
</html>

<script>
    $(document).ready(function () {
        loadProducts(1);
        loadPages();
    });

</script>

<!-- USING XML data from GOOGLE API combine with JSP -->