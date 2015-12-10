<%-- 
    Document   : login
    Created on : Nov 29, 2015, 11:29:56 PM
    Author     : Anthony Brignano
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="eng">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>

    <body>
        <div id="mainWrapper">
            
            <!--Login header-->
            <h1 class= "text-center">Login</h1>
            
            <!--
                Login container begins
            -->
            <div class="login-container">
                
                <div class="login-card">
                    
                    <div class="login-form" >
                        <!--
                            Establish variable loginURL below (set to /login by default)
                        -->
                        <c:url var="loginUrl" value="/login" />
                        <form action="login" method="post" class="form-horizontal">
                            
                            <!--
                                Example of how to check if username & password 
                                are valid- if not, return an alert danger
                            -->
                            <c:if test="${error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            
                            <!--
                                Example of how to check to see if user was redirected 
                                to the page after logging out- if so, alert success
                            -->
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            
                            <!--Username input text box-->
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                                <input type="username" class="form-control" id="username" name="username" placeholder="Enter Username" required>
                            </div>
                            
                            <!--Password input text box-->
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                            </div>

                            <!--Used to prevent against CSRF attacks-->
                            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                               
                            
                            <!--
                                Button group below: contains Login & Register buttons
                            -->
                            <div class="text-center">
                                <div class="btn-group inline">
                                    
                                    <!--Login button-->
                                    <div class="btn">
                                        <input type="submit"
                                               class="btn btn-block btn-primary btn-success " value="Login">
                                    </div>
                                    
                                    <!--Register button-->
                                    <div class="btn">
                                        <input type="button" class="btn btn-block btn-primary btn-default"
                                               onclick="location.href = 'register'" value="Register" >
                                    </div>
                                    
                                </div>
                                
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>