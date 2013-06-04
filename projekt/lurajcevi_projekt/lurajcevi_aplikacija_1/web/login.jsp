<%-- 
    Document   : PrijavaKorisnika
    Created on : Jun 3, 2013, 4:42:26 PM
    Author     : Luka Rajcevic 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prijava</title>
    </head>
    <body>
        <h1>Prijava korisnika</h1>
        <form method="POST" action="${pageContext.servletContext.contextPath}/ProvjeraKorisnika">
            Korisnicko ime:<br/>
            <input name="kor_ime"/> <br/>
            Lozinka: <br/>
            <input name="lozinka" type="password" /> <br/>
            <input type="submit" value="prijava"/>
        </form>
    </body>
</html>
