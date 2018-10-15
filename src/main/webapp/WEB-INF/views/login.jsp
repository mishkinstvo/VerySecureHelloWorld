<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:baseTemplate>
    <div class="my-md-4 pt-4 pb-2 text-center">
        <img src="<c:url value="/resources/images/auth.png" />" alt="Neutral Doomface" />
        <h1>Authenticate yourself, alien!</h1>
    </div>
    <c:if test="${loginMessage != null}">
        <div class="alert alert-success" role="alert">
           ${loginMessage}
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-info" role="alert">
            You have been successfully logged out!
        </div>
    </c:if>
    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">
            Wrong credentials. Your soul shall suffer!
        </div>
    </c:if>
    <form method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="form-group">
            <label for="login">Your login</label>
            <input type="text" class="form-control" id="login" name="username" placeholder="Enter your login" />
        </div>

        <div class="form-group">
            <label for="password">Your password &nbsp;<span id="strength"></span></label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" />
        </div>

        <br/>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Hey, not too rough</button>
            <a href="<c:url value="/register" />" class="btn">Join the navy</a>
        </div>
    </form>
</t:baseTemplate>