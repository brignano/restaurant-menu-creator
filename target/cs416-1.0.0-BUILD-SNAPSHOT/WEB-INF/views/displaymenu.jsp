<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Example page to output menu to verify it is working --%>
<html>
    <head>
        <meta charset="utf-8">
        <title>View Menu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </head> 
    <body class="text-center">
        <h1>${headerMessage}</h1>
<!--        <h2>Restaurant Name: ${menu.menuTitle}</h2>
        <h2>Logo URL: ${menu.logoPath}</h2>
        <h2>Phone: ${menu.phone}</h2>
        <h2>Street: ${menu.street}</h2>
        <h2>City: ${menu.city}</h2>
        <h2>State: ${menu.state}</h2>
        <h2>Zip Code: ${menu.zip}</h2>-->
        <form:label path="menuTitle"><strong>Title: </strong></form:label>
            <mark path ="menuTitle">${menu.menuTitle}</mark>
            <br/>
            <form:label path="phone"><strong>Phone: </strong></form:label>
            <mark path ="phone">${menu.phone}</mark>
            <br/>
            <form:label path="street"><strong>Street: </strong></form:label>
            <mark path ="menuTitle">${menu.street}</mark>
            <br/>
            <form:label path="city"><strong>City: </strong></form:label>
            <mark path ="menuTitle">${menu.city}</mark>
            <br/>
            <form:label path="state"><strong>State: </strong></form:label>
            <mark path ="menuTitle">${menu.state}</mark>
            <br/>
            <form:label path="zip"><strong>Zip: </strong></form:label>
            <mark path ="menuTitle">${menu.zip}</mark>
            <br/>
            <form:label path="logoPath"><strong>Logo Path: </strong></form:label>
            <mark path ="menuTitle">${menu.logoPath}</mark>
            <br/>
            <br/>

        <c:forEach items ="${menu.submenus}" var = "submenus">
            <h3> Menu Section : ${submenus.subMenuTitle}</h3>

            <c:forEach var = "i" begin="1" end = "${submenus.menuItems.size()}" >
                <h3>&nbsp ${submenus.menuItems.get(i-1).name}</h3>
                <h3>&nbsp &nbsp ${submenus.menuItems.get(i-1).description}</h3>
                <h3>&nbsp &nbsp ${submenus.menuItems.get(i-1).price}</h3>
            </c:forEach>
        </c:forEach>
                 <input type="button" class="btn btn-primary btn-default"
                                               onclick="location.href = 'home'" value="Home Page" >
    </body>
</html>
