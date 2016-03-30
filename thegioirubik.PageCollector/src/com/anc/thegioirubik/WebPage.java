/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.thegioirubik;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import thegioirubik.pagecollector.ThegioirubikPageCollector;

/**
 *
 * @author Admin
 */
public class WebPage {

    private Anchor anchor;
    private String webPageHash;
    private int anchorParseStatus;
    private int emailParseStatus;

    private Document document;

    public WebPage(Anchor anchor) {
        this.anchor = anchor;
    }

    private void getDocumentFromWeb() {
        try {
            document = Jsoup.connect(anchor.getAnchorUrl()).get();
            
        } catch (IOException ex) {
            Logger.getLogger(ThegioirubikPageCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
