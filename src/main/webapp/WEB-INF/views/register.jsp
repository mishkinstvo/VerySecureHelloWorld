<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:baseTemplate>
    <div class="my-md-4 pt-4 pb-2 text-center">
        <img src="<c:url value="/resources/images/suspicious.gif" />" alt="Suspicious doomface" />
        <h1>Get over here!</h1>
    </div>

    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>

    <form id="register" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="form-group">
            <label for="login">Your login</label>
            <input type="text" class="form-control" id="login" name="login" placeholder="Enter your login" value="${login}" />
        </div>

        <div class="form-group">
            <label for="password">Your password &nbsp;<span id="strength"></span></label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" autocomplete="new-password" value="${password}" />
        </div>
        <br/>
        <button type="submit" class="btn btn-primary">Hurt me plenty</button>
        <a class="btn" href="<c:url value="/" />">I&rsquo;m too young to die</a>
    </form>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://terrylinooo.github.io/jquery.disableAutoFill/assets/js/jquery.disableAutoFill.min.js"></script>

    <script>
        function displayStrengthRate(strength) {
            var message = '<span class="badge badge-danger">is too weak, maybe try something longer?</span>';

            if (strength === 'STRONG') {
                message = '<span class="badge badge-success">is strong enough, great job!</span>';
            }
            if (strength === 'ACCEPTABLE') {
                message = '<span class="badge badge-warning">is acceptable, but you can do better :)</span>';
            }

            $('#strength').html(message);
            $('form#register button[type=submit]').attr('disabled', strength === 'WEAK');
        }

        function rateStrength() {
            $.get("passwords/strength", {'password': $('#password').val()}, function(data) {
                displayStrengthRate(data);
            });
        }

        $('#password').on('change input', function () {
            rateStrength();
        });

        rateStrength();
    </script>
</t:baseTemplate>
