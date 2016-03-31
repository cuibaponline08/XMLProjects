/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.databaseUtil;

import com.anc.dto.Account;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        String databaseServer = "CUIBAP";
        String databaseInstance = "DUYDT";
        String databaseName = "PointOfSaleDB_TTH";
        String username = "sa";
        String password = "123456";
        DatabaseUtil dbUtil = new DatabaseUtil();
        dbUtil.createConnection(databaseServer, databaseInstance, databaseName, username, password);
        String[] columnNames = {"Code", "ProductName", "Price"};
        dbUtil.selectFromTable("Product", columnNames, "Price > 40000");
        
        String[] newValues = {"Price = 40000"};
        dbUtil.updateTable("Product", newValues, "Price > 45000");
        dbUtil.selectFromTable("Product", columnNames, "Price >= 40000");
        dbUtil.selectFromTable("Account");
    }
}
