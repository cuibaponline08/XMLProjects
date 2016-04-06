<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Animated hover box text form beneath</title>
        <link rel="stylesheet" href="xproject.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

        <!--[if gte IE 9]>
          <style type="text/css">
            .gradient {
               filter: none;
            }
          </style>
        <![endif]-->
    </head>
    <body id="body" class="preload">
        <div class="wrapper">
            <section class="row">
                <div class="container-item" style="top: 200px">
                    <ul>
                        <c:forEach items="${requestScope.LIST}" var="product" >
                        <li>
                            <div class="item">
                                <img src="${product.picUrl}" style="width: 367px; height: 260px;"/>
                                <div class="item-overlay">
<!--                                    <a href="#" class="item-button play"><i class="play"></i></a>
                                    <a href="#" class="item-button share share-btn"><i class="play"></i></a>-->
                                    <div class="sale-tag"><span>SALE</span></div>
                                </div>
                                <div class="item-content">
                                    <div class="item-top-content">
                                        <div class="item-top-content-inner">
                                            <div class="item-product">
                                                <div class="item-top-title">
                                                    <h2>${product.productName}</h2>
                                                    <p class="subdescription">
                                                        Low skateshoes - Grey
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="item-product-price">
                                                <span class="price-num">${product.price}</span>
                                                <!--<p class="subdescription">$36</p>-->
                                                <!--<div class="old-price"></div>-->
                                            </div>
                                        </div>	
                                    </div>
                                    <div class="item-add-content">
                                        <div class="item-add-content-inner">
                                            <!-- <div class="section">
                                                    <h4>Sizes</h4>
                                                    <p>40,41,42,43,44,45</p>
                                            </div> -->
                                            <div class="section">
                                                <a href="#" class="btn buy expand">Buy now</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </section>
        </div>
    </body>
</html>

<script>
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