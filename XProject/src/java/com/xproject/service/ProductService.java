/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.service;

import com.xproject.dto.ProductDTO;
import com.xproject.repository.ProductRepository;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Admin
 */
public class ProductService {
    private ProductRepository productRepository = new ProductRepository();
    
    public List<ProductDTO> getAllProductsWithOnePic(String condition){
        List<ProductDTO> list = productRepository.search(condition);
        return list;
    }
    
    public List<ProductDTO> getProductsWtih1Pic(int skip, int take){
        List<ProductDTO> list = productRepository.getProductSkipTake(skip, take);
        return list;
    }
    
    public ProductDTO getProductById(int productId){
        return productRepository.getById(productId);
    }
}
