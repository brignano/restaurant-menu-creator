<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Example page to output menu to verify it is working --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<html>
    <head>
        <meta charset="utf-8">
        <title>Menu Editing Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </head> 
    <body class="text-center">
        <h2>Edit Your Menu Information.</h2>
        <form:form  method ="post" modelAttribute ="menu" action ="updatemenu" >
            <form:label path="menuTitle">Title: </form:label>
            <form:input path ="menuTitle" value ="${menu.menuTitle}"/>
            <br/>
            <form:label path="phone">Phone: </form:label>
            <form:input path ="phone" value = "${menu.phone}" />
            <br/>
            <form:label path="street">Street: </form:label>
            <form:input path ="street" value = "${menu.street}" />
            <br/>
            <form:label path="city">City: </form:label>
            <form:input path ="city" value = "${menu.city}" />
            <br/>
            <form:label path="state">State: </form:label>
            <form:input path ="state" value = "${menu.state}" />
            <br/>
            <form:label path="zip">Zip: </form:label>
            <form:input path ="zip" value = "${menu.zip}" />
            <br/>
            <form:label path="logoPath">Logo Path: </form:label>
            <form:input path ="logoPath" value ="${menu.logoPath}"/>

            <form:input path ="id" value="${menu.id}" hidden ="true"/>
            <form:label path ="id"></form:label>

            <br/>

            <button type="submit" class="btn btn-default btn-success">Update Menu</button>
            <button type ="submit" class="btn btn-default btn-danger" formaction="deletemenu">Delete Menu</button>
        </form:form>

    </body>
</html>