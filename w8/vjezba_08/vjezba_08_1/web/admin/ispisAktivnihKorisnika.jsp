<%-- 
    Document   : ispisAktivnihKorisnika
    Created on : Apr 18, 2013, 8:22:11 AM
    Author     : nwtis_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NWTIS - ispis aktivnih korisnika</title>
    </head>
    <body>
        <h1>NWTIS - ispis aktivnih korisnika</h1>
        <c:forEach items="${aktivniKorisnici}" var="korisnik">
            Korisničko ime: ${korisnik.korisnik} <br>
            Ime: ${korisnik.ime} <br>
            Prezime: ${korisnik.prezime} <br>
            <hr>
        </c:forEach>
    </body>
</html>
