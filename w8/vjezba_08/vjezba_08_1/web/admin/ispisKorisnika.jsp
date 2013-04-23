<%-- 
    Document   : ispisKorisnika
    Created on : Apr 18, 2013, 8:22:00 AM
    Author     : Luka Rajcevic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/displaytag.css" />
        <title>NWTiS Vjezba 08-1 </title>
    </head>
    <body>
        <sql:setDataSource
            var="NWTiS"
            driver="${applicationScope.BP_Konfiguracija.driver_database}"
            url="${BP_Konfiguracija.server_database}${BP_Konfiguracija.user_database}"
            user="${BP_Konfiguracija.user_username}"
            password="${BP_Konfiguracija.user_password}"
            />

        <sql:transaction dataSource="${NWTiS}">
            <sql:query var="ispis">
                SELECT ime, prezime FROM polaznici
            </sql:query>
               
                <display:table name="${ispis.rows}" pagesize="6">
                    <display:column property="ime" />
                    <display:column property="prezime" />
                </display:table>    
                
            <%--
            <table border="1">
                <tr>
                    <th>Prezime</th>
                    <th>Ime</th>
                </tr>
                <c:forEach var = "red" items="${ispis.rows}">
                    <tr>
                        <td>${red.prezime}</td>
                        <td>${red.ime}</td>
                    </tr>
                </c:forEach>
            </table>
                --%>

        </sql:transaction>

    </body>
</html>

