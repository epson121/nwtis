<%-- 
    Document   : login
    Created on : Apr 16, 2013, 5:04:02 PM
    Author     : nwtis_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NWTiS vjezba 08 - prijava</title>
    </head>
    <body>
        <h1>NWTiS vjezba 08 - prijava</h1>
        <form method="POST" action="${pageContext.servletContext.contextPath}/ProvjeraKorisnika">
            Korisnicko ime:<br/>
            <input name="kor_ime"/> <br/>
            Lozinka: <br/>
            <input name="lozinka" type="password" /> <br/>
            <input type="submit" value="prijava"/>
        </form>
    </body>
</html>
