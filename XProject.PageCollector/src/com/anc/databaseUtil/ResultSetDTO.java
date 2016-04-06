/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.databaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ResultSetDTO {
    private List<ResultDTO> resultSet = new ArrayList<ResultDTO>();

    public List<ResultDTO> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<ResultDTO> resultSet) {
        this.resultSet = resultSet;
    }
    
    public ResultDTO getResultByIndex(int index){
        return this.resultSet.get(index);
    }
    
    public void addResult(ResultDTO result){
        this.resultSet.add(result);
    }
}
