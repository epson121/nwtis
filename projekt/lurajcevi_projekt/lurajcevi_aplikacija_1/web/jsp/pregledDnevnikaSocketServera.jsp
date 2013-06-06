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
         Filtriraj zipove:
        <form method="POST" action="${pageContext.servletContext.contextPath}/PregledDnevnikaSocketServeraFilter">
            Filtriraj prema statusu:
            <input name="status"/>
            <input type="submit" value="Dohvati"/>
        </form>
            <display:table name="${dnevnik_socket_servera}" pagesize="10" >
                <display:column property="id"  sortable="true"/>
                <display:column property="komanda" />
                <display:column property="status" />
                <display:column property="korisnik" />
                <display:column property="datum" sortable="true" />
            </display:table>     
        
        <br><br>
        <a href="${pageContext.servletContext.contextPath}/index.jsp">Početna</a><br>
    </body>
</html>
