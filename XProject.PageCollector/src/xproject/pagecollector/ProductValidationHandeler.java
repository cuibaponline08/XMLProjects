/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xproject.pagecollector;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 *
 * @author cuiba
 */
public class ProductValidationHandeler implements ValidationEventHandler{

    @Override
    public boolean handleEvent(ValidationEvent event) {
        if (event.getSeverity() == ValidationEvent.FATAL_ERROR ||
                event.getSeverity() == ValidationEvent.ERROR ||
                event.getSeverity() == ValidationEvent.WARNING) {
            ValidationEventLocator locator = event.getLocator();
            System.out.println(event.getMessage());
        }
        
        return false;
    }
}