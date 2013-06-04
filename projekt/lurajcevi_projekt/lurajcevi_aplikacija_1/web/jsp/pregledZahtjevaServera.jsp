<%-- 
    Document   : pregledZahtjevaServera
    Created on : Jun 3, 2013, 5:15:43 PM
    Author     : Luka Rajcevic 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/displaytag.css" />
        <title>Zahtjevi prema serveru</title>
    </head>
    <body>
        <h1>Pregled zahtjeva prema serveru</h1>
        
        <display:table name="${pregled_zahtjeva}" pagesize="10" >
            <display:column property="id" sortable="true"/>
            <display:column property="zahtjev" />
            <display:column property="trajanje" />
        </display:table>     
    
        <br><br>
        <a href="${pageContext.servletContext.contextPath}/index.jsp">Početna</a><br>
    </body>
</html>
