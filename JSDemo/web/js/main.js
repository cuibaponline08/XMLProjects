/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global undefine */

window.onload = function () {
    var el = document.getElementsByTagName("h1")[0];
    el.style.color = "red";
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
    
    var newValue =  new Array();
    newValue["A"] = "Hello";
    
    if (true) {
        textArea.value = newValue["A"];
    }
}