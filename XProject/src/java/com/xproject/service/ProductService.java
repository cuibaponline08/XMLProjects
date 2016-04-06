/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.service;

import com.xproject.dto.ProductDTO;
import com.xproject.repository.ProductRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductService {
    private ProductRepository productRepository = new ProductRepository();
    
    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> list = productRepository.getAll();
        for (ProductDTO productDTO : list) {
            if (productDTO.getPicUrl() != null && !productDTO.getPicUrl().equals("")) {
                
                productDTO.setPicUrl(productDTO.getPicUrl().replace("|", ""));
            }
        }
        
        return list;
    }
}
