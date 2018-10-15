<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:baseTemplate>
    <div class="my-md-4 pt-4 pb-2 text-center">
        <img src="<c:url value="/resources/images/guy.png" />" alt="Welcome!" />
        <h1>Welcome!</h1>
        <p>To proceed to hello world page, please follow this <b><a href="<c:url value="/helloWorld" />">link</a></b>.</p>
    </div>
</t:baseTemplate>