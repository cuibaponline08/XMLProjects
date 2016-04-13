<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:pro="http://xml.netbeans.org/schema/products">
    <xsl:output method="html" omit-xml-declaration="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="pro:product">
        <section class='row product-detail'>
            <div id='watch-header' class='yt-card yt-card-has-padding product-detail'>
                <div>
                    <h1>
                        <xsl:value-of select="pro:name"/>
                    </h1>
                </div>
                <div>
                    <h3>
                        <xsl:value-of select="pro:currency"/>
                        <xsl:value-of select="pro:price"/>
                    </h3>
                </div>
                <div>
                    <h3 id='location{pro:id}'>
                        <xsl:value-of select="pro:location"/>
                    </h3>
                    <h3 id='distance{pro:id}'></h3>
                </div>
            </div>
            <div class='slider product-detail'>
                <xsl:for-each select="pro:product">

                </xsl:for-each>
                <input type='radio' name='slide_switch'
                       checked='checked' id='pic" + i '/>
                <label for='pic" + i '>
                    <img src='" + productImages[i] ' width='100'/>
                </label>
                <img src='" + productImages[i] '/>
            </div>
        </section>
    </xsl:template>
</xsl:stylesheet>
