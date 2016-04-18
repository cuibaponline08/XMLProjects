/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.repository;

import com.anc.databaseUtil.DatabaseUtil;
import com.anc.databaseUtil.ResultDTO;
import com.anc.databaseUtil.ResultSetDTO;
import com.xproject.dto.ProductDTO;
import com.xproject.infrastructure.ANCParser;
import com.xproject.infrastructure.IRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cuiba
 */

public class ProductRepository implements IRepository<ProductDTO> {

    private DatabaseUtil dbUtil;

    public ProductRepository() {
        String databaseServer = "CUIBAP";
        String databaseInstance = "DUYDT";
        String databaseName = "XProject";
        String username = "sa";
        String password = "123456";
        dbUtil = new DatabaseUtil();
        dbUtil.createConnection(databaseServer, databaseInstance, databaseName, username, password);
    }

    @Override
    public boolean add(ProductDTO entity) {
        try {
            String[] values = getNewValues(entity);
            int records = dbUtil.insertToTable("Product", values);

            if (records == 0) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(ProductDTO entity) {
        String[] newValues = getNewValues(entity);
        String condition = "ProductId = " + entity.getProductId();
        int record = dbUtil.updateTable("Product", newValues, condition);

        return record != 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductDTO getById(int id) {
        ResultSetDTO rs = dbUtil.getById("Product", id);
        List<ProductDTO> list = getProductList(rs);
        if (list.size() > 0) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<ProductDTO> getAll() {
        ResultSetDTO rs = dbUtil.selectFromTable("Product");
        List<ProductDTO> list = getProductList(rs);
        return list;
    }

    @Override
    public List<ProductDTO> search(String condition) {
        ResultSetDTO rs = dbUtil.selectFromTable("Product", condition);
        List<ProductDTO> list = getProductList(rs);
        return list;
    }
    
    public List<ProductDTO> searchBetween(float min, float max) {
        String condition = "Price >= " + min + " AND Price <= " + max;
        ResultSetDTO rs = dbUtil.selectFromTableWhere("Product", condition);
        List<ProductDTO> list = getProductList(rs);
        return list;
    }
    
    public List<ProductDTO> searchPrice(float price) {
        String condition = "Price >= " + (price * 0.9) + " AND Price <= " + (price * 1.1);
        ResultSetDTO rs = dbUtil.selectFromTableWhere("Product", condition);
        List<ProductDTO> list = getProductList(rs);
        return list;
    }

    /*
        return list product from ResultSetDTO
     */
    private List<ProductDTO> getProductList(ResultSetDTO rs) {
        List<ProductDTO> list = new ArrayList<ProductDTO>();

        for (ResultDTO result : rs.getResultSet()) {
            ProductDTO newProduct = new ProductDTO();

            //<editor-fold defaultstate="collapsed" desc="Map data from ResultDTO to ProductDTO">
            newProduct.setProductId(ANCParser.parseInt(result.getValueFromColumn(0)));
            newProduct.setProductName(result.getValueFromColumn(1));
            newProduct.setProductType(ANCParser.parseInt(result.getValueFromColumn(2)));
            newProduct.setPrice(ANCParser.parseFloat(result.getValueFromColumn(3)));

            String stringPicJoined = result.getValueFromColumn(4);
            String[] picUrl = stringPicJoined.split("\\|");
            newProduct.setDefaultPic(picUrl[0]);
            newProduct.setPicUrlList(picUrl);

            newProduct.setCategoryId(ANCParser.parseInt(result.getValueFromColumn(5)));
            newProduct.setIsFixedPrice(ANCParser.parseBoolean(result.getValueFromColumn(6)));
            newProduct.setDescription(result.getValueFromColumn(7));

            String infoJoined = result.getValueFromColumn(8);
            String[] infoArray = infoJoined.split("\\|");
            newProduct.setAddingInformation(infoJoined);
            newProduct.setAddingInformationList(infoArray);

            newProduct.setLocation(result.getValueFromColumn(9));
            newProduct.setProductStatus(ANCParser.parseInt(result.getValueFromColumn(10)));
            newProduct.setCustomerId(ANCParser.parseInt(result.getValueFromColumn(11)));
            newProduct.setProductSourceUrl(result.getValueFromColumn(12));
            newProduct.setCurrency(result.getValueFromColumn(13));
//</editor-fold>

            list.add(newProduct);
        }

        return list;
    }

    private String[] getNewValues(ProductDTO entity) {
        String picUrl = String.join("|", entity.picUrlList);

        String[] result = {entity.productName, String.valueOf(entity.productType),
            String.valueOf(entity.price), picUrl, String.valueOf(entity.categoryId),
            String.valueOf(entity.isFixedPrice), entity.description, entity.addingInformation,
            entity.location, String.valueOf(entity.productStatus), String.valueOf(entity.customerId),
            String.valueOf(entity.productSourceUrl), entity.currency
        };

        return result;
    }
}
