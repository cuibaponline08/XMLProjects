/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anc.outputfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 *
 * @author cuiba
 */
public class DemoOutputFile {
    public static void main(String[] args){
        OutputStream ops = null;
        try {
            FopFactory ff = FopFactory.newInstance();
            FOUserAgent fua = ff.newFOUserAgent();
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, ops);
            
            File pdf = new File("src/com/anc/outputfile/pdfDemo.pdf");
            ops = new FileOutputStream(pdf);
            
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer trans = tff.newTransformer();
            
            File fo = new File("src/com/anc/outputfile/grammarDoc.fo");
            StreamSource ss = new StreamSource(fo);
            Result result = new SAXResult(fop.getDefaultHandler());
            
            trans.transform(ss, result);
        } catch (Exception e) {
        }
        finally{
            try {
                ops.close();
            } catch (Exception e) {
            }
        }
    }
}
