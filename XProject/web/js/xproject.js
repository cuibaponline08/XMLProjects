firstimeSearch = true;

function loadProducts(currentPage) {
    document.getElementById("main-logo").className = "main-logo-searched";
    document.getElementById('currentProductDetail').value = "";

    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('main-content').innerHTML = xhttp.responseText;
        }
    };
    
    var url = './' + 'CenterServlet?action=SearchProductServlet&currentPage=' +
            currentPage + '&keyword=' + document.getElementById('txtSearch').value;
    xhttp.open("POST", url, true);
    xhttp.send();
    
    var stateObj = {foo: "bar"};
    history.pushState(stateObj, "page 2", url);
}

function pageClick(currentPage) {
    loadProducts(currentPage);

    var prevPageNum = document.getElementById('currentPage').value;
    var totalPages = parseInt(document.getElementById('totalPages').value);
    var lastPage = parseInt(document.getElementById('lastPage').value);
    var firstPage = parseInt(document.getElementById('firstPage').value);
    var prevPageId = "page" + prevPageNum;
    document.getElementById(prevPageId).className = "page-number";

    if (currentPage >= ((lastPage + firstPage) / 2)) {
        lastPage = currentPage + 5;
        firstPage = currentPage - 4;
        if (lastPage >= totalPages) {
            lastPage = totalPages;
            firstPage = totalPages - 9;
            if (firstPage < 1) {
                firstPage = 1;
            }
        }
        document.getElementById('lastPage').value = lastPage;
        document.getElementById('firstPage').value = firstPage;

        var output = document.getElementById('pagination-items');
        output.innerHTML = '';
        for (var i = firstPage; i <= lastPage; i++) {
            output.innerHTML += '<li>'
                    + "<button id='page" + i + "' class='page-number' onclick='pageClick(" + i + ")'>" + i + "</button>"
                    + '</li>'
        }
    } else if (currentPage < ((lastPage + firstPage) / 2)) {
        lastPage = currentPage + 5;
        firstPage = currentPage - 4;
        if (firstPage < 1) {
            firstPage = 1;
            if (lastPage >= totalPages) {
                lastPage = totalPages;
            } else {
                lastPage = 10;
            }
        }
        document.getElementById('lastPage').value = lastPage;
        document.getElementById('firstPage').value = firstPage;

        var output = document.getElementById('pagination-items');
        output.innerHTML = '';
        for (var i = firstPage; i <= lastPage; i++) {
            output.innerHTML += '<li>'
                    + "<button id='page" + i + "' class='page-number' onclick='pageClick(" + i + ")'>" + i + "</button>"
                    + '</li>'
        }
    }

    var currPageId = "page" + currentPage;
    document.getElementById(currPageId).className += " bold";

    document.getElementById('currentPage').value = currentPage;
}

function loadPages() {
    var currentPage = 1;
    var url = 'CenterServlet?action=SearchProductServlet&currentPage=' +
            currentPage + '&keyword=' + document.getElementById('txtSearch').value;
    ancAjax('GET', url,
            'footer');
}

function searchProduct(event) {
    var keyword = document.getElementById('txtSearch').value;

    if (keyword === "") {
        document.getElementById('txtSearch').className = "text-search-empty";
    } else {
        document.getElementById('txtSearch').className = "text-searched";
    }

    if (event.keyCode === 13) {
        loadProducts(1);
        loadPages();
        if (firstimeSearch === true) {
            giveAGift();
            firstimeSearch = false;

        }
    }


}

function giveAGift() {
    var url = './' + "CenterServlet?action=GiftServlet";

    window.open(url);
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
        var xhttp = new XMLHttpRequest();

        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                if (xhttp.responseText === "") {
                    loadProducts(1);
                } else {
                    document.getElementById(id).innerHTML = xhttp.responseText;
                    document.getElementById('currentProductDetail').value = id;
                    loadDistance();
                }
            }
        };
        var url = './' + "CenterServlet?action=ProductDetailServlet&productId=" + productId +
                "&currentLocation=" + document.getElementById("currentLocation").value;
        xhttp.open("GET", url, true);
        xhttp.send();
    }
}

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        document.getElementById("currentLocation").value = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    document.getElementById("currentLocation").value = position.coords.latitude +
            ", " + position.coords.longitude;
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

function loadDistance() {
//        var bounds = new google.maps.LatLngBounds;
//        var markersArray = [];

    var origin1 = document.getElementById("currentLocation").value;
    var address = document.getElementById('currentProductDetail').value.replace("product", "location");
    var destinationA = document.getElementById(address).innerHTML;

//        var destinationIcon = 'https://chart.googleapis.com/chart?' +
//                'chst=d_map_pin_letter&chld=D|FF0000|000000';
//        var originIcon = 'https://chart.googleapis.com/chart?' +
//                'chst=d_map_pin_letter&chld=O|FFFF00|000000';
//        var map = new google.maps.Map(document.getElementById('map'), {
//            center: {lat: 55.53, lng: 9.4},
//            zoom: 10
//        });
    // var geocoder = new google.maps.Geocoder;

    var service = new google.maps.DistanceMatrixService;
    service.getDistanceMatrix({
        origins: [origin1],
        destinations: [destinationA],
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC,
        avoidHighways: false,
        avoidTolls: false
    }, function (response, status) {
        if (status !== google.maps.DistanceMatrixStatus.OK) {
            alert('Error was: ' + status);
        } else {
            var originList = response.originAddresses;
            var destinationList = response.destinationAddresses;

            var outputId = address.replace("location", "distance");
            var outputDiv = document.getElementById(outputId);
            outputDiv.innerHTML = '| ';
//                deleteMarkers(markersArray);

            for (var i = 0; i < originList.length; i++) {
                var results = response.rows[i].elements;
                for (var j = 0; j < results.length; j++) {
                    outputDiv.innerHTML += results[j].distance.text + ' in ' +
                            results[j].duration.text;
                }
            }
        }
    });
}
