<%-- 
    Document   : home
    Created on : Nov 29, 2015, 11:33:21 PM
    Author     : Anthony Brignano
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="eng">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>Home Page</title>
    </head>
    <body class="text-center">
        <h1>Hello ${user.getUsername()}!
            <br/><small>Welcome back!</small></h1>
            <input type="button" class="btn btn-default btn-success" onclick="location.href='menucreation'" value="Create a Menu!"/>
    </body>
</html>
