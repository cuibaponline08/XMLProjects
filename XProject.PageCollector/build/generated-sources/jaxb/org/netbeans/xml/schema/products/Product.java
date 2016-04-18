//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.17 at 05:26:03 PM ICT 
//


package org.netbeans.xml.schema.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Product complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imageSourceUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productDetailUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addingInformation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isFixedPrice" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="postted" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *       &lt;attribute name="categoryId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
    "productName",
    "imageSourceUrl",
    "productPrice",
    "currency",
    "customerAddress",
    "productDetailUrl",
    "description",
    "addingInformation",
    "productType",
    "isFixedPrice",
    "postted"
})
public class Product {

    @XmlElement(required = true)
    protected String productName;
    @XmlElement(required = true)
    protected String imageSourceUrl;
    protected float productPrice;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(required = true)
    protected String customerAddress;
    @XmlElement(required = true)
    protected String productDetailUrl;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String addingInformation;
    @XmlElement(required = true)
    protected String productType;
    protected boolean isFixedPrice;
    protected int postted;
    @XmlAttribute(name = "categoryId", required = true)
    protected int categoryId;

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the imageSourceUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageSourceUrl() {
        return imageSourceUrl;
    }

    /**
     * Sets the value of the imageSourceUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageSourceUrl(String value) {
        this.imageSourceUrl = value;
    }

    /**
     * Gets the value of the productPrice property.
     * 
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * Sets the value of the productPrice property.
     * 
     */
    public void setProductPrice(float value) {
        this.productPrice = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the customerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets the value of the customerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerAddress(String value) {
        this.customerAddress = value;
    }

    /**
     * Gets the value of the productDetailUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDetailUrl() {
        return productDetailUrl;
    }

    /**
     * Sets the value of the productDetailUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDetailUrl(String value) {
        this.productDetailUrl = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the addingInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddingInformation() {
        return addingInformation;
    }

    /**
     * Sets the value of the addingInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddingInformation(String value) {
        this.addingInformation = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the isFixedPrice property.
     * 
     */
    public boolean isIsFixedPrice() {
        return isFixedPrice;
    }

    /**
     * Sets the value of the isFixedPrice property.
     * 
     */
    public void setIsFixedPrice(boolean value) {
        this.isFixedPrice = value;
    }

    /**
     * Gets the value of the postted property.
     * 
     */
    public int getPostted() {
        return postted;
    }

    /**
     * Sets the value of the postted property.
     * 
     */
    public void setPostted(int value) {
        this.postted = value;
    }

    /**
     * Gets the value of the categoryId property.
     * 
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     */
    public void setCategoryId(int value) {
        this.categoryId = value;
    }

}
