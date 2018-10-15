function displayStrengthRate(strength) {
    var message = '<span class="badge badge-danger">is too weak, maybe try something longer?</span>';

    if (strength === 'STRONG') {
        message = '<span class="badge badge-success">is strong enough, great job!</span>';
    }
    if (strength === 'ACCEPTABLE') {
        message = '<span class="badge badge-warning">is acceptable, but you can do better :)</span>';
    }

    $('form#register #strength').html(message);
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