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
        <h2>menu.menuTitle: ${menu.menuTitle}</h2>
        <h2>menu.logoPath: ${menu.logoPath}</h2>
        <h2>restaurantInfo.restPhone: ${restaurantInfo.restPhone}</h2>
        <h2>restaurantInfo.restStreet: ${restaurantInfo.restStreet}</h2>
        <h2>restaurantInfo.sections: ${restaurantInfo.sections}</h2>
        <h2>Link To Generated PDF: ${redirectPath}</h2>

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
