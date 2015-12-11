<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Example page to output menu to verify it is working --%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome</title>
    </head> 
    <body>
        <h1>${headerMessage}</h1>
        <h2>Restaurant Name:${menu.menuTitle}</h2>
        <h2>Logo URL ${menu.logoPath}</h2>
        <h2>Phone: ${menu.phone}</h2>
        <h2>Street: ${menu.street}</h2>
        <h2>City: ${menu.city}</h2>
        <h2>State: ${menu.state}</h2>
        <h2>Zip Code: ${menu.zip}</h2>

        <c:forEach items ="${menu.submenus}" var = "submenus">
            <h3> Menu Section : ${submenus.subMenuTitle}</h3>

            <c:forEach var = "i" begin="1" end = "${submenus.menuItems.size()}" >
                <h3>&nbsp ${submenus.menuItems.get(i-1).name}</h3>
                <h3>&nbsp &nbsp ${submenus.menuItems.get(i-1).description}</h3>
                <h3>&nbsp &nbsp ${submenus.menuItems.get(i-1).price}</h3>
            </c:forEach>
        </c:forEach>
    </body>
</html>
