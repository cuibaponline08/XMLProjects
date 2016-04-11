
function loadProducts(currentPage) {
    $.ajax({
        type: "POST",
        url: "CenterServlet?action=ShowAllProductServlet&currentPage=" + currentPage,
        data: "",
        contentType: "application/json",
        async: false,
        success: function (result) {
            document.getElementById('main-content').innerHTML = result.toString();
        }
    });
}

function loadPages() {
    $.ajax({
        type: "GET",
        url: "CenterServlet?action=ShowAllProductServlet",
        data: "",
        contentType: "application/json",
        async: true,
        success: function (result) {
            document.getElementById('footer').innerHTML = result.toString();
        }
    });
}

function loadProductDetail(productId) {
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
                document.getElementById('main-content').innerHTML = result.toString();
                document.getElementById('footer').innerHTML = "";
            }
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

