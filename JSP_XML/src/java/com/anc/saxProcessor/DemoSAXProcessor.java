/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.saxProcessor;

import com.anc.constant.ConstantManager;
import com.anc.databaseUtil.DatabaseUtil;
import com.anc.databaseUtil.ResultDTO;
import com.anc.databaseUtil.ResultSetDTO;
import com.anc.dto.ProductDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author cuiba
 */
public class DemoSAXProcessor implements Serializable {

    private Vector products = new Vector();

    public DemoSAXProcessor() {
        DatabaseUtil dbUtil = new DatabaseUtil();
        dbUtil.createConnection(ConstantManager.databaseServer,
                ConstantManager.databaseInstance,
                ConstantManager.databaseName,
                ConstantManager.username,
                ConstantManager.password);
        
        String[] columnNames = {"Code", "ProductName", "Price"};
        ResultSetDTO rs = dbUtil.selectFromTable("Product", columnNames);

        for (ResultDTO result : rs.getResultSet()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.code = (result.getResult().get(0));
            productDTO.productName = (result.getResult().get(1));
            productDTO.productPrice = (result.getResult().get(2));
            
            products.add(productDTO);
        }
    }
    
    public Iterator getProducts(){
        return products.iterator();
    }
}
