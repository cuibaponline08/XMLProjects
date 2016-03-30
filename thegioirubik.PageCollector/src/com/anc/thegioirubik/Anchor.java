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
public class Anchor {
    private Domain domain;
    private String anchorHash;
    private String anchorUrl;
    private int scanStatus;
    private boolean activated;
    private Timestamp modified;
    private Timestamp created;

    public Domain getDomain() {
        return domain;
    }

    public String getAnchorHash() {
        return anchorHash;
    }

    public String getAnchorUrl() {
        return anchorUrl;
    }

    public int getScanStatus() {
        return scanStatus;
    }

    public boolean isActivated() {
        return activated;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Anchor(Domain domain, String anchorHash, String anchorUrl) {
        this.domain = domain;
        this.anchorHash = anchorHash;
        this.anchorUrl = anchorUrl;
    }

    /*
    
    */
    public Anchor(Domain domain, String anchorHash, String anchorUrl, int scanStatus, boolean activated, Timestamp modified, Timestamp created) {
        this.domain = domain;
        this.anchorHash = anchorHash;
        this.anchorUrl = anchorUrl;
        this.scanStatus = scanStatus;
        this.activated = activated;
        this.modified = modified;
        this.created = created;
    }
}
