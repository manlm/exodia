/**
 * Created by manlm1 on 9/14/2015.
 */

$(document).ready(function () {
    $('input').keypress(function (e) {
        if (e.which === 32)
            return false;
    });

    $('.username').bind('keypress', function (event) {
        var regex = new RegExp("^[a-zA-Z0-9]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
            event.preventDefault();
            return false;
        }
    });

    $('input').on("invalid", function (e) {
        e.preventDefault();
    });
});

