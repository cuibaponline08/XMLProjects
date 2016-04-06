/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.demo.DTO;

/**
 *
 * @author Gunner
 */
public class Student {
    private String firstname;
    private String lasstname;
    private String password;
    private String dob;
    private String id;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public Student() {
    }

    public Student(String firstname, String lasstname, String password, String dob, String id) {
        this.firstname = firstname;
        this.lasstname = lasstname;
        this.password = password;
        this.dob = dob;
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLasstname() {
        return lasstname;
    }

    public void setLasstname(String lasstname) {
        this.lasstname = lasstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }
    
}
