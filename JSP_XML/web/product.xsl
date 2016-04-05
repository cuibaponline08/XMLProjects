<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : product.xsl
    Created on : April 4, 2016, 3:34 PM
    Author     : cuiba
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>product.xsl</title>
            </head>
            <body>
                <table border="1">
                    <xsl:apply-templates select="products"/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="products">
        <xsl:apply-templates select="product"/>
    </xsl:template>
    <xsl:template match="product">
        <tr>
            <td>
                <xsl:value-of select="code"/>
            </td>
            <td>
                <xsl:value-of select="productName"/>
            </td>
            <td>
                <xsl:value-of select="productPrice"/>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
