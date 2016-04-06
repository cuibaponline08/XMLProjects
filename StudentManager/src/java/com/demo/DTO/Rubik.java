/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.demo.DTO;

/**
 *
 * @author cuiba
 */
public class Rubik {
    public String productName;
    public String imageUrl;
    public String productPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Rubik() {
    }

    public Rubik(String productName, String imageUrl, String productPrice) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.productPrice = productPrice;
    }
}
