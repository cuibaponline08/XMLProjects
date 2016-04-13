<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <xsl:call-template name="list">
        </xsl:call-template>
    </xsl:template>
    <xsl:template name="list">
        <xsl:for-each select="products/product">
            <div class='item'>
                <img src='{defaultPic}' style='width: 367px; height: 260px;'/>
                <div class='item-overlay'>
                            
                </div>
                <div class='item-content'>
                    <div class='item-top-content'>
                        <div class='item-top-content-inner'>
                            <div class='item-product'>
                                <div class='item-top-title panel-heading'>
                                    <h2 class='product-name'>
                                        <b>
                                            <xsl:value-of select="name"/>
                                        </b>
                                    </h2>
                                </div>
                            </div>
                            <div class='item-product-price'>
                                <span class='price-num'>
                                    <xsl:value-of select="currency"/>
                                    <xsl:value-of select="price"/>
                                </span>
                                <p class='subdescription'>
                                    <xsl:value-of select="productType"/>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class='item-add-content'>
                        <div class='item-add-content-inner'>
                            <div class='section'>
                                <span onclick='loadProductDetail({id})' class='btn buy expand'>
                                    <xsl:text>View Details</xsl:text>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id='productId{id}' class='product-detail-info'></div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
