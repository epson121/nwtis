<%-- 
    Document   : ispisPodataka
    Created on : Apr 18, 2013, 8:10:35 AM
    Author     : nwtis_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NWTiS Vjezba 08_1 - ispis podataka</title>
    </head>
    <body>
        <h1>Aktivni korisnik:</h1>
        Korisničko ime: ${sessionScope.korisnik.korisnik} <br>
        Ime: ${sessionScope.korisnik.ime} <br>
        Prezime: ${sessionScope.korisnik.prezime} <br>
        IP : ${sessionScope.korisnik.ip_adresa} <br>
        Vrsta:  ${sessionScope.korisnik.vrsta} <br>
    </body>
</html>
