/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.thegioirubik;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Domain {
    private String domainHash;
    private String domainUrl;
    private boolean activated;
    private Timestamp modified;
    private Timestamp created;

    public String getDomainHash() {
        return domainHash;
    }

    public void setDomainHash(String domainHash) {
        this.domainHash = domainHash;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Domain(String domainHash, String domainUrl) {
        this.domainHash = domainHash;
        this.domainUrl = domainUrl;
    }

    /* 
        use when polulating from database 
    */
    public Domain(String domainHash, String domainUrl, boolean activated, Timestamp modified, Timestamp created) {
        this.domainHash = domainHash;
        this.domainUrl = domainUrl;
        this.activated = activated;
        this.modified = modified;
        this.created = created;
    }
    
    
}
