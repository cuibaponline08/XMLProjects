/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.databaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

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

    public ResultSetDTO selectFromTable(String tableName, String[] columnNames) {
        ResultSetDTO resultSetDTO = new ResultSetDTO();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();

            String select = getSelectColumnQuery(columnNames);

            rs = stm.executeQuery("SELECT " + select + " FROM [" + tableName + "]");

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                ResultDTO resultDTO = new ResultDTO();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    resultDTO.addValue(rs.getString(i));
                }
                resultSetDTO.addResult(resultDTO);
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
        return resultSetDTO;
    }

    public ResultSetDTO selectFromTable(String tableName, String[] columnNames, String condition) {
        ResultSetDTO resultSetDTO = new ResultSetDTO();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();

            String select = getSelectColumnQuery(columnNames);

            rs = stm.executeQuery("SELECT " + select + " FROM [" + tableName + "] WHERE " + condition);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                ResultDTO resultDTO = new ResultDTO();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    resultDTO.addValue(rs.getString(i));
                }

                resultSetDTO.addResult(resultDTO);
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
        return resultSetDTO;
    }

    public ResultSetDTO selectFromTable(String tableName) {
        ResultSetDTO resultSetDTO = new ResultSetDTO();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();

            rs = stm.executeQuery("SELECT * FROM [" + tableName + "]");
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                ResultDTO resultDTO = new ResultDTO();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    resultDTO.addValue(rs.getString(i));
                }
                resultSetDTO.addResult(resultDTO);
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

        return resultSetDTO;
    }

    public ResultSetDTO selectFromTable(String tableName, String condition) {
        ResultSetDTO resultSetDTO = new ResultSetDTO();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();

            rs = stm.executeQuery("SELECT * FROM [" + tableName + "] WHERE " + condition);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                ResultDTO resultDTO = new ResultDTO();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    resultDTO.addValue(rs.getString(i));
                }
                resultSetDTO.addResult(resultDTO);
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

        return resultSetDTO;
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
            records = stm.executeUpdate("UPDATE [" + tableName + "] SET " + setter + " WHERE " + condition);

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
            records = stm.executeUpdate("UPDATE [" + tableName + "] SET " + setter);

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

    private String getSelectColumnQuery(String[] columnNames) {
        String select = "*";
        for (int i = 0; i < columnNames.length; i++) {
            if (i == 0) {
                select = columnNames[i];
            } else {
                select += ", " + columnNames[i];
            }
        }
        return select;
    }

    public int insertToTable(String tableName, String[] newValues) {
        int records = 0;
        String DUYTEST = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            stm = conn.createStatement();
            String setter = "";
            for (int i = 0; i < newValues.length; i++) {
                if (i == 0 && newValues[0] != null && !newValues[0].isEmpty()) {
                    setter = newValues[0];
                } else {
                    setter += ", " + newValues[i];
                }
            }
            DUYTEST = setter;

            records = stm.executeUpdate("INSERT INTO [" + tableName + "] VALUES (" + setter + ")");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("FFF" + ex);
            System.out.println("FFF" + DUYTEST);
        } finally {
            try {
                rs.close();
                stm.close();
                conn.close();;
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
        }

        return records;
    }
}
