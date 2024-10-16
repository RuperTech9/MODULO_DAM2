<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>CANCIONES</title>
            </head>
            <body>
                <h1>CANCIONES</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>ANIO</th>
                        <th>TITULO</th>
                        <th>ARTISTA</th>
                        <th>DURACION</th>
                        <th>EsESPANOLA</th>
                    </tr>
                    <xsl:apply-templates select="Canciones/cancion" />
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="cancion">
        <tr>
            <xsl:apply-templates />
        </tr>
    </xsl:template>

    <xsl:template match="id | anio | titulo | artista | duracion | esEspanola ">
        <td>
            <xsl:apply-templates />
        </td>
    </xsl:template>
</xsl:stylesheet>