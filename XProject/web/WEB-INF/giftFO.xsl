<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="3.5in"
                                       page-width="5.3in" margin-top="0.5in" margin-bottom="0.5in"
                                       margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.5in" />
                    <fo:region-before extent="1in" />
                    <fo:region-after extent=".75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="sans-serif"
                              line-height="24pt" background-color="#0099ff"
                              space-after.optimum="15pt" text-align="center"
                              padding-top="3pt">
                        Hunter Motor's Gift
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="sans-serif"
                              line-height="24pt" space-after.optimum="15pt"
                              text-align="center" padding-top="3pt">
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <xsl:for-each select="gifts/gift">
                        <fo:block text-align="center">
                            <xsl:value-of select="name"/>
                        </fo:block>
                        <fo:block text-align="center" font-size="25pt" margin-bottom="10pt">
                            <xsl:value-of select="code"/>
                        </fo:block>
                        <fo:block text-align="center" margin-bottom="10pt">
                            <xsl:value-of select="description"/>
                        </fo:block>
                    </xsl:for-each>
                    <fo:block font-size="14pt" font-family="sans-serif"
                              line-height="24pt" background-color="#9999ff"
                              space-after.optimum="15pt" text-align="center"
                              color="#ffffff"
                              padding-top="3pt">
                        Thanks for using Hunter Motor!
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
