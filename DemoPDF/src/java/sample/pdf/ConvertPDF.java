/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;


/**
 *
 * @author Trong Khanh
 */
public class ConvertPDF {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OutputStream ous = null;
        try {
            FopFactory ff = FopFactory.newInstance();
            FOUserAgent fua = ff.newFOUserAgent();       
            File pdf = new File("src/java/sample/pdf/grammarDoc.pdf");
            ous = new FileOutputStream(pdf);
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, ous);
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer trans = tff.newTransformer();
            File fo = new File("src/java/sample/pdf/grammarDoc.fo");
            Source src = new StreamSource(fo);
            Result result = new SAXResult(fop.getDefaultHandler());
            trans.transform(src, result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ConvertPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FOPException ex) {
            Logger.getLogger(ConvertPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ConvertPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ous.close();
            } catch (IOException ex) {
                Logger.getLogger(ConvertPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
