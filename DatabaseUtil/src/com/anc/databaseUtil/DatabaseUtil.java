/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.databaseUtil;

import com.anc.dto.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DatabaseUtil {

    private String connectionString = "jdbc:sqlserver://CUIBAP\\DUYDT;databaseName=PointOfSaleDB_TTH;user=sa;password=123456";

    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public void createConnection(String databaseServer, String databaseInstance, String databaseName, String username, String password) {
        connectionString = "jdbc:sqlserver://" + databaseServer + "\\"
                + databaseInstance + ";databaseName=" + databaseName
                + ";user=" + username + ";password=" + password;
    }

    public void selectFromTable(String tableName, String[] columnNames) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                for (String columnName : columnNames) {
                    System.out.println(columnName + ": " + rs.getString(columnName));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                conn.close();;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void selectFromTable(String tableName, String[] columnNames, String condition) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();

            rs = stm.executeQuery("SELECT * FROM " + tableName + " WHERE " + condition);
            while (rs.next()) {
                for (String columnName : columnNames) {
                    System.out.println(columnName + ": " + rs.getString(columnName));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                conn.close();;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void selectFromTable(String tableName) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();

            rs = stm.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount(); i++) {
                    System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i));
                }
                System.out.println("=========================================");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                conn.close();;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public int updateTable(String tableName, String[] newValues, String condition) {
        int records = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();
            String setter = "";
            for (int i = 0; i < newValues.length; i++) {
                if (i == 0 && newValues[0] != null && !newValues[0].isEmpty()) {
                    setter = newValues[0];
                } else {
                    setter += ", " + newValues;
                }
            }
            records = stm.executeUpdate("UPDATE " + tableName + " SET " + setter + " WHERE " + condition);

            System.out.println("Update (" + records + ") success(s).");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                conn.close();;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return records;
    }
    
    public int updateTable(String tableName, String[] newValues) {
        int records = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();
            String setter = "";
            for (int i = 0; i < newValues.length; i++) {
                if (i == 0 && newValues[0] != null && !newValues[0].isEmpty()) {
                    setter = newValues[0];
                } else {
                    setter += ", " + newValues;
                }
            }
            records = stm.executeUpdate("UPDATE " + tableName + " SET " + setter);

            System.out.println("Update (" + records + ") success(s).");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                conn.close();;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return records;
    }

}
