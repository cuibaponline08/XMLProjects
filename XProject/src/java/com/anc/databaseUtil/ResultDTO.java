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
public class ResultDTO {
    private List<String> result = new ArrayList<String>();

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
    
    public String getValueFromColumn(int index){
        return result.get(index);
    }
    
    public void addValue(String value){
        this.result.add(value);
    }
    
    public int getLenght(){
        return this.result.size();
    }
}
