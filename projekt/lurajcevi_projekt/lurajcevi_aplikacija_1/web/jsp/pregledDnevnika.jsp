<%-- 
    Document   : pregledDnevnika
    Created on : Jun 3, 2013, 5:16:12 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pregled dnevnika socket servera!</h1>
         <sql:setDataSource
            var="dnevnik_podaci"
            driver="${applicationScope.BP_Konfiguracija.driver_database}"
            url="${BP_Konfiguracija.server_database}${BP_Konfiguracija.admin_database}"
            user="${BP_Konfiguracija.admin_username}"
            password="${BP_Konfiguracija.admin_password}"
            />
        
         
         
        <sql:transaction dataSource="${dnevnik_podaci}">
            <sql:query var="ispis">
                SELECT id, naredba FROM lurajcevi_dnevnik_servera
            </sql:query>
            <display:table name="${ispis.rows}" pagesize="10" >
                <display:column property="id" />
                <display:column property="naredba" />
            </display:table>     
        </sql:transaction>
    </body>
</html>
