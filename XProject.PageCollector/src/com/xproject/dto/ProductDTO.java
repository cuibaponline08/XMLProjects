/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.dto;

/**
 *
 * @author cuiba
 */
public class ProductDTO {
    public int productId;
    public String productName;
    public int productType;
    public float price;
    public String picUrl;
    public int categoryId;
    public boolean isFixedPrice;
    public String description;
    public String addingInformation;
    public String location;
    public int productStatus;
    public int customerId;
    public String productSourceUrl;
    public String currency;
    public int posted;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, int productType, float price, String picUrl, int categoryId, boolean isFixedPrice, String description, String addingInformation, String location, int productStatus, int customerId, String productSourceUrl, String currency, int posted) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.picUrl = picUrl;
        this.categoryId = categoryId;
        this.isFixedPrice = isFixedPrice;
        this.description = description;
        this.addingInformation = addingInformation;
        this.location = location;
        this.productStatus = productStatus;
        this.customerId = customerId;
        this.productSourceUrl = productSourceUrl;
        this.currency = currency;
        this.posted = posted;
    }

    public int getPosted() {
        return posted;
    }

    public void setPosted(int posted) {
        this.posted = posted;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isIsFixedPrice() {
        return isFixedPrice;
    }

    public void setIsFixedPrice(boolean isFixedPrice) {
        this.isFixedPrice = isFixedPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddingInformation() {
        return addingInformation;
    }

    public void setAddingInformation(String addingInformation) {
        this.addingInformation = addingInformation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProductSourceUrl() {
        return productSourceUrl;
    }

    public void setProductSourceUrl(String productSourceUrl) {
        this.productSourceUrl = productSourceUrl;
    }

   
}
