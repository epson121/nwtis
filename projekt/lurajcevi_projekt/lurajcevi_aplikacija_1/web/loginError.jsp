<%-- 
    Document   : loginError
    Created on : Jun 4, 2013, 12:02:31 AM
    Author     : Luka Rajcevic 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Err!!!</h1>
        Uneseni su krivi korisnički podaci.<br>
        Pokusajte  <a href ="${pageContext.servletContext.contextPath}/PrijavaKorisnika">ponovo</a>, 
        ili idite na <a href ="${pageContext.servletContext.contextPath}/Kontroler">početnu stranicu.</a>
        
        
    </body>
</html>
