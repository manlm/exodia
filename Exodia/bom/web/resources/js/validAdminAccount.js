/**
 * Created by manlm1 on 10/6/2015.
 */

function validUsername(mess1) {
    var result = commonValidUsername();
    if (result == 1) {
        $('#username-error').val(mess1);
        return false;
    } else {
        $('#username-error').val('');
        return true;
    }
}

function validEmail(mess1, mess2, regex) {
    var result = commonValidEmail(regex);
    if (result == 1) {
        $('#email-error').val(mess1);
        return false;
    } else if (result == 2) {
        $('#email-error').val(mess2);
        return false;
    } else {
        $('#email-error').val('');
        return true;
    }
}

function validPassword(mess1, mess2, mess3, regex) {
    var result = commonValidPassword(regex);
    if (result == 1) {
        $('#password-error').val(mess1);
        return false;
    } else if (result == 2) {
        $('#password-error').val(mess2);
        return false;
    } else if (result == 3) {
        $('#password-error').val(mess3);
        return false;
    } else {
        $('#password-error').val('');
        return true;
    }
}

function validConfirmPassword(mess1) {
    var result = commonValidConfirmPassword();
    if (result == 1) {
        $('#confirm-password-error').val(mess1);
        return false;
    } else {
        $('#confirm-password-error').val('');
        return true;
    }
}

function validOldPassword(mess1, mess2, mess3, regex) {
    var result = commonValidOldPassword(regex);
    if (result == 1) {
        $('#old-password-error').val(mess1);
        return false;
    } else if (result == 2) {
        $('#old-password-error').val(mess2);
        return false;
    } else if (result == 3) {
        $('#old-password-error').val(mess3);
        return false;
    } else {
        $('#old-password-error').val('');
        return true;
    }
}