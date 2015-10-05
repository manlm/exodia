/**
 * Created by manlm1 on 10/5/2015.
 */

function validOnSubmit(mess1, mess2, mess3, regex1, mess4, mess5, mess6, regex2, mess7) {
    if (!validUsername(mess1)) {
        $("#username").focus();
    } else if (!validEmail(mess2, mess3, regex1)) {
        $("#email").focus();
    } else if (!validPassword(mess4, mess5, mess6, regex2)) {
        $("#password").focus();
    } else if (!validConfirmPassword(mess7)) {
        $("#confirmPassword").focus();
    } else {
        $("#add-form").submit();
    }
}

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