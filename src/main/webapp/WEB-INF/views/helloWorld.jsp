<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:baseTemplate>
    <div class="my-md-4 pt-4 pb-2 text-center">
        <h1>Hello, World!</h1>
        <h5>Congratulations! You have just become a witness of a very secured information. Enjoy yourself!</h5>
        <br/>
        <img src="<c:url value="/resources/images/success.png" />" alt="Happy Doomface" />
    </div>
</t:baseTemplate>