<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Example page to output menu to verify it is working --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<html>
    <head>
        <meta charset="utf-8">
        <title>Menu Editing Page</title>
    </head> 
    <body>
        <h2>Edit Your Menu below. Submitting will update the database with the correct values.</h2>
        <form:form  method ="post" modelAttribute ="menu" action ="updatemenu" >
            
            <form:input path ="menuTitle" value ="${menu.menuTitle}"/>
            <br>
            <form:input path ="logoPath" value ="${menu.logoPath}"/>
            <br>
            <form:input path ="id" value="${menu.id}" hidden ="true"/>

            <input type="submit">
        </form:form>

    </body>
</html>