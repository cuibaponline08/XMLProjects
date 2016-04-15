
function loadProducts(currentPage) {
    document.getElementById('currentProductDetail').value = "";
    ancAjax('POST', 'CenterServlet?action=ShowAllProductServlet&currentPage=' + currentPage,
            'main-content');
}

function loadPages() {
    ancAjax('GET', 'CenterServlet?action=ShowAllProductServlet', 'footer');
}

function searchProduct(event) {
    if (event.keyCode === 13) {
        var currentPage = 1;
        document.getElementById('currentProductDetail').value = "";
        ancAjax('POST', 'CenterServlet?action=SearchProductServlet&currentPage=' +
                currentPage + '&keyword=' + document.getElementById('txtSearch').value,
                'main-content');
    }
}

function loadProductDetail(productId) {
    var id = 'product' + productId;
    var isShowDetail = true;
    if (document.getElementById('currentProductDetail') !== null) {
        var currentProductDetailId = document.getElementById('currentProductDetail').value;
        if (currentProductDetailId != "") {
            document.getElementById(currentProductDetailId).innerHTML = "";
        }

        if (currentProductDetailId === id) {
            document.getElementById(currentProductDetailId).innerHTML = "";
            isShowDetail = false;
            document.getElementById('currentProductDetail').value = "";
        }
    }
    if (isShowDetail) {
        $.ajax({
            type: "GET",
            url: "CenterServlet?action=ProductDetailServlet&productId=" + productId,
            data: "",
            contentType: "application/json",
            async: true,
            success: function (result) {
                if (result === "") {
                    loadProducts(1);
                    loadPages();
                } else {
                    document.getElementById(id).innerHTML = result.toString();
                    document.getElementById('currentProductDetail').value = id;
                    var destinations = document.getElementById('location' + productId).textContent;
                    getDistance(destinations, 'distance' + productId);
                }
            }
        });
    }
}

function getDistance(destinations, outputId) {
    $.ajax({
        type: "GET",
        url: 'https://maps.googleapis.com/maps/api/distancematrix/xml?units=metric&origins=London&destinations=' + destinations + '&key=AIzaSyB0iJvMLVkPYti5b_GaIPutP5IvRQat6B8',
        data: "",
        contentType: "application/json",
        async: true,
        success: function (result) {
            if (result === "") {

            } else {
                document.getElementById(outputId).innerHTML = result.toString();
            }
        }
    });
}

function ancAjax(methodType, url, targetElement) {
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById(targetElement).innerHTML = xhttp.responseText;
        }
    };
    xhttp.open(methodType, './' + url, true);
    xhttp.send();
}
//
//window.onload(function () {
////    document.getElementById('body').class = "";
//    $("#body").removeClass("preload");
//});
//
//$(".share-btn").mouseenter(function () {
//    setTimeout(function () {
//        $(".item-menu").addClass("visible")
//    }, 500);
//});
//$(".share-btn").mouseleave(function () {
//    setTimeout(function () {
//        $(".item-menu").removeClass("visible")
//    }, 500);
//});
//$(".item-menu").hover(function () {
//    $(".item-menu").addClass("visible")
//});
//$(".item-menu").mouseleave(function () {
//    setTimeout(function () {
//        $(".item-menu").removeClass("visible")
//    }, 500);
//});
//$(".container-item").hover(function () {
//    setTimeout(function () {
//        $(".container-item").css("z-index", "1000")
//    }, 500);
//});

