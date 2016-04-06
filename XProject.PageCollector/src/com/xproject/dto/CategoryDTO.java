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
public class CategoryDTO {
    public int categoryId;
    public String name;
    public int type;
    public int status;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryId, String name, int type, int status) {
        this.categoryId = categoryId;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
