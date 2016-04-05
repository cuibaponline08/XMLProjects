/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.infrastructure;

/**
 *
 * @author cuiba
 */
public class ANCParser {
    public static int parseInt(String s){
        int result = 0;
        
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
        }
        
        return result;
    }
    
    public static float parseFloat(String s){
        float result = 0;
        
        try {
            result = Float.parseFloat(s);
        } catch (NumberFormatException ex) {
        }
        
        return result;
    }
    
    public static boolean parseBoolean(String s){
        boolean result = false;
        
        try {
            result = Boolean.parseBoolean(s);
        } catch (Exception ex) {
        }
        
        return result;
    }
}