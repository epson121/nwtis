<%-- 
    Document   : pregledMeteoPodataka
    Created on : Jun 3, 2013, 5:15:21 PM
    Author     : Luka Rajcevic 
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/displaytag.css" />
        <title>Pregled meteo podataka</title>
    </head>
    <body>
        <h1>Pregled meteoroloških podataka</h1>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/MeteoPodaciStranicenje">
            <select id="broj_stranica" name="broj_stranica">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="0">Svi</option>
            </select>
            <input type="submit" value="Submit" />
        </form>
        Filtriraj zipove:
        <form method="POST" action="${pageContext.servletContext.contextPath}/MeteoPodaciZipFilter">
            Zip kod:
            <input name="zip"/>
            <input type="submit" value="Dohvati"/>
        </form>
        Filtriraj zip po datumu (Format datuma: dd.MM.yyyy hh.mm.ss)
        <form method="POST" action="${pageContext.servletContext.contextPath}/MeteoPodaciInterval">
            Zip kod:
            <input name="zip"/>
            &nbsp&nbsp&nbsp 
            Datum1:
            <input name="datum1"/>
            &nbsp&nbsp&nbsp 
            Datum2:
            <input name="datum2"/>
            <input type="submit" value="Dohvati"/>
        </form>
            
            
        <display:table name="${meteo_podaci}" pagesize="${broj_stranica}" >
            <display:column property="zip_trazeni" sortable="true"/>
            <display:column property="grad" />
            <display:column property="temperatura" />
            <display:column property="geo_duzina" />
            <display:column property="geo_sirina" />
            <display:column property="datum" sortable="true"/>
        </display:table>     
        <br><br>
        <a href="${pageContext.servletContext.contextPath}/index.jsp">Početna</a><br>
    </body>
</html>
