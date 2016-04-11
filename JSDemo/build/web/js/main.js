/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global undefine */

function changeVisibility(tr) {
    if (document.getElementById(tr).style.visibility === "hidden") {
        document.getElementById(tr).style.visibility = "visible";
    } else {
        document.getElementById(tr).style.visibility = "hidden";
    }
}

function changeDisplay(tr){
    if (document.getElementById(tr).style.display === "none") {
        document.getElementById(tr).style.display = "block";
    } else {
        document.getElementById(tr).style.display = "none";
    }
}


function changeSkin(newSkin) {
    this.skin = newSkin;
}

function changeOnwer(newOnwer) {
    this.owner = newOnwer;
}

function showDog() {
    document.writeln("Kind: " + this.kind + " Name: " + this.name +
            " Skin: " + this.skin + " Onwer: " + this.owner);
}

function dog() {
    this.kind = "Ngao";
    this.name = "Tây tạng";
    this.skin = "Tử";
    this.owner = ["D", "U", "Y"];

    this.changeOnwer = changeOnwer;
    this.changeSkin = changeSkin;
    this.showDog = showDog;
}



function outputArray(intro, array) {
    for (var i in array) {
        for (var j in array[i]) {
            for (var k in array[i][j]) {
                document.writeln("a[" + i + "][" + j + "][" + k + "] = " + array[i][j][k] + "</br>");
            }
        }
    }
}

function changeValue(value) {
    value = "safsaas";
}

function compare1(value1, value2) {
    return parseInt(value1) - parseInt(value2);
}

function compare2(value1, value2) {
    return parseInt(value2) - parseInt(value1);
}

window.onload = function () {
    var el = document.getElementsByTagName("h1")[0];
    el.style.color = "red";
}

function validate() {
    var regexPatern = "/[\w%_.]+@[\w-.]+\.[\w]{2,n}/";
    var email = document.getElementById('email').value;

    var regex = new RegExp(regexPatern);
    var isValid = regex.test(email);
    if (isValid) {
        alert("Right");
    } else {
        alert("Wrong");
    }
}

function demo() {
    'use strict';
//    document.appendChild("<h2>ahihi đồ ngốk</h2>");
    var input = document.getElementsByTagName("input")[0];
    var textArea = document.getElementsByTagName("textarea")[0];

    var value = "5";
    var nullValue = null;
    var undefineValue;

    if (undefineValue > 4) {
        textArea.value = undefineValue;
    }

//    var newArray = [];
    var newArray = new Array();
    newArray.push("cat");
    newArray.push("dog");
    newArray.push("bird");
    newArray.push("lion");

    var newValue = new Array();
    newValue["A"] = "Hello";

    if (true) {
        textArea.value = newValue["A"];
    }

    validate();
//    
//    if (validate()) {
//        input.value = "Hello this is right";
//    }
}