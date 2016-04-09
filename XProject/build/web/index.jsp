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
    </head>
    <body id="body" class="preload">
        <div class="body-main">
            <div class="wrapper">
                <section class="row">
                    <input type="text" class="textbox-search"/>
                    <input type="button" class="submit-button" value="Search"/>
                </section>
            <!--</div>-->
            <!--<div class="wrapper">-->
                <section class="row">
                    <div class="container-item">
                        <div class="product" id="productList">
                            <!-- item will load by AJAX -->
                        </div> <!-- end div product -->
                    </div>
                </section>
            <!--</div>-->
            <!--<div class="wrapper-paging">-->
                <section class="row">
                    <div id="pageItems">
                        <!-- item will load by AJAX -->
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>

<script>
    $(document).ready(function () {
        loadProduct(1);
        loadPages();
    });

    function loadPages() {
        $.ajax({
            type: "POST",
            url: "PagingServlet",
            data: "",
            contentType: "application/json",
            async: false,
            success: function (result) {
                document.getElementById('pageItems').innerHTML = result.toString();
            }
        });
    }

    function loadProduct(currentPage) {
        $.ajax({
            type: "POST",
            url: "CenterServlet?currentPage=" + currentPage,
            data: "",
            contentType: "application/json",
            async: false,
            success: function (result) {
                document.getElementById('productList').innerHTML = result.toString();
            }
        });
    }

    $("window").load(function () {
        $("#body").removeClass("preload");
    });

    $(".share-btn").mouseenter(function () {
        setTimeout(function () {
            $(".item-menu").addClass("visible")
        }, 500);
    });
    $(".share-btn").mouseleave(function () {
        setTimeout(function () {
            $(".item-menu").removeClass("visible")
        }, 500);
    });
    $(".item-menu").hover(function () {
        $(".item-menu").addClass("visible")
    });
    $(".item-menu").mouseleave(function () {
        setTimeout(function () {
            $(".item-menu").removeClass("visible")
        }, 500);
    });
    $(".container-item").hover(function () {
        setTimeout(function () {
            $(".container-item").css("z-index", "1000")
        }, 500);
    });


</script>