/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.dto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Account implements Serializable {
    public String AccountId;
    public String AccountPassword;
    public String StaffName;
    public String Role;
    public boolean IsUsed;

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String AccountId) {
        this.AccountId = AccountId;
    }

    public String getAccountPassword() {
        return AccountPassword;
    }

    public void setAccountPassword(String AccountPassword) {
        this.AccountPassword = AccountPassword;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public boolean isIsUsed() {
        return IsUsed;
    }

    public void setIsUsed(boolean IsUsed) {
        this.IsUsed = IsUsed;
    }
    
    
}
