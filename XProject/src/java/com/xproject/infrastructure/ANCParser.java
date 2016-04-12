/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xproject.infrastructure;

import com.xproject.data.Enum.ProductTypeEnum;
import java.text.DecimalFormat;

/**
 *
 * @author cuiba
 */
public class ANCParser {

    public static String GOOGLEMAPS_APIKEY = "AIzaSyB0iJvMLVkPYti5b_GaIPutP5IvRQat6B8";
    //https://maps.googleapis.com/maps/api/distancematrix/xml?units=imperial&origins=HoChiMinh+VietNam&destinations=TraVinh+VietNam&key=AIzaSyB0iJvMLVkPYti5b_GaIPutP5IvRQat6B8
    //GOOD//https://maps.googleapis.com/maps/api/distancematrix/xml?units=metric&origins=Ho Chi Minh, Viet Nam&destinations=Vũng Tàu, Bà Rịa&key=AIzaSyB0iJvMLVkPYti5b_GaIPutP5IvRQat6B8
    public static int parseInt(String s) {
        int result = 0;

        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
        }

        return result;
    }

    public static float parseFloat(String s) {
        float result = 0;

        try {
            result = Float.parseFloat(s);
        } catch (NumberFormatException ex) {
        }

        return result;
    }

    public static boolean parseBoolean(String s) {
        boolean result = false;

        try {
            result = Boolean.parseBoolean(s);
        } catch (Exception ex) {
        }

        return result;
    }

    public static String parseProductType(ProductTypeEnum type) {
        String result = "Other";

        switch (type) {
            case Event:
                result = "Event";
                break;
            case ForLoan:
                result = "For Loan";
                break;
            case ForSale:
                result = "For Sale";
                break;
            case ForStud:
                result = "For Stud";
                break;
            case Found:
                result = "Found";
                break;
            case Lost:
                result = "Lost";
                break;
            case Service:
                result = "Service";
                break;
            case Swap:
                result = "Swap";
                break;
            case ToRent:
                result = "To Rent";
                break;
            case Wanted:
                result = "Wanted";
                break;
            default:
                result = "Other";
                break;
        }

        return result;
    }

    public static String parseProductType(int type) {
        String result = "Other";

        switch (type) {
            case 5:
                result = "Event";
                break;
            case 3:
                result = "For Loan";
                break;
            case 0:
                result = "For Sale";
                break;
            case 9:
                result = "For Stud";
                break;
            case 8:
                result = "Found";
                break;
            case 7:
                result = "Lost";
                break;
            case 6:
                result = "Service";
                break;
            case 4:
                result = "Swap";
                break;
            case 2:
                result = "To Rent";
                break;
            case 1:
                result = "Wanted";
                break;
            default:
                result = "Other";
                break;
        }

        return result;
    }

    public static int parseProductTypeInt(String productType) {
        int result = ProductTypeEnum.Other.ordinal();

        if ("For Sale".equals(productType)) {
            result = ProductTypeEnum.ForSale.ordinal();
        } else if ("Wanted".equals(productType)) {
            result = ProductTypeEnum.Wanted.ordinal();
        } else if ("To Rent".equals(productType)) {
            result = ProductTypeEnum.ToRent.ordinal();
        } else if ("For Loan".equals(productType)) {
            result = ProductTypeEnum.ForLoan.ordinal();
        } else if ("Swap".equals(productType)) {
            result = ProductTypeEnum.Swap.ordinal();
        } else if ("Event".equals(productType)) {
            result = ProductTypeEnum.Event.ordinal();
        } else if ("Service".equals(productType)) {
            result = ProductTypeEnum.Service.ordinal();
        } else if ("Lost".equals(productType)) {
            result = ProductTypeEnum.Lost.ordinal();
        } else if ("Found".equals(productType)) {
            result = ProductTypeEnum.Found.ordinal();
        } else if ("For Stud".equals(productType)) {
            result = ProductTypeEnum.ForStud.ordinal();
        } else {
            result = ProductTypeEnum.Other.ordinal();
        }

        return result;
    }

    public static String moneyFormat(float money) {
        DecimalFormat myFormatter = new DecimalFormat("#,###");

        return myFormatter.format(money);
    }
}
