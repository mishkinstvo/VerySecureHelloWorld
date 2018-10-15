<%@tag description="Base Template" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" />
    <style>
        body {font-family: 'Lato', sans-serif;}
    </style>
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-light bg-light my-md-4">
            <a class="navbar-brand" href="#">Very Secure Hello World Page, v1.0</a>
            <div class="text-right">
                <security:authorize access="isAuthenticated()">
                    <form class="form-inline" action="<c:url value="/logout" />" method="post">
                        <span style="margin-right: 1em;">Welcome, <%= request.getUserPrincipal().getName() %>!</span>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button class="btn">Log out</button>
                    </form>
                </security:authorize>
            </div>
        </nav>

        <div class="container"><jsp:doBody /></div>

        <footer class="pt-4 my-md-5 pt-md-4 border-top text-center">
            <div class="row">
                <div class="col-12 col-md">
                    <small class="d-block mb-3 text-muted">&copy; Mykhailo Skrypkin, 2018</small>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>