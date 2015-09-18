/**
 * Created by manlm1 on 9/13/2015.
 */
function commonValidUsername() {
    var username = $(".username").val();
    if (username.length < 1) {
        return 1;
    }
    return 0;
}

function commonValidPassword(regex) {
    var password = $(".password").val();
    if (password.length < 1) {
        return 1;
    } else if (password.length < 8) {
        return 2;
    } else if (!password.match(regex)) {
        return 3;
    }
    return 0;
}

function commonValidEmail(regex) {
    var email = $(".email").val();
    if (email.length < 1) {
        return 1;
    } else if (!email.match(regex)) {
        return 2;
    }
    return 0;
}