<%-- 
    Document   : index
    Created on : May 29, 2013, 4:39:11 PM
    Author     : Luka Rajcevic 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/displaytag.css" />
        <title>Index</title>
    </head>
    <body>
        <h1>Izbornik</h1>
        <a href="${pageContext.servletContext.contextPath}/PrijavaKorisnika">Prijava</a><br>
        <a href="${pageContext.servletContext.contextPath}/PregledMeteoPodataka">Pregled meteo podataka</a><br>
        <a href="${pageContext.servletContext.contextPath}/PregledDnevnikaSocketServera">Pregled dnevnika socket servera</a><br>
        <a href="${pageContext.servletContext.contextPath}/PregledZahtjevaServera">Pregled zahtjeva servera</a><br>
        <a href="${pageContext.servletContext.contextPath}/OdjavaKorisnika">Odjava</a><br>
    </body>
</html>
