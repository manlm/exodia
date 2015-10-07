/**
 * Created by manlm1 on 10/7/2015.
 */

function validOnSubmit(mess1, mess2, mess3, regex, mess4, btn) {
    if (!validOldPassword(mess1, mess2, mess3, regex)) {
        $("#old-password").focus();
    } else if (!validNewPassword(mess1, mess2, mess3, regex)) {
        $("#new-password").focus();
    } else if (!validConfirmPassword(mess4)) {
        $("#confirm-password").focus();
    } else {
        $("#btn-update").val(btn);
        $("#btn-update").prop('disabled', true);
        $("#first-login-form").submit();
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

function validNewPassword(mess1, mess2, mess3, regex) {
    var result = commonValidNewPassword(regex);
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

function commonValidConfirmPassword2() {
    var password = $(".new-password").val();
    var confirmPassword = $(".confirm-password").val();

    if (password != confirmPassword) {
        return 1;
    }

    return 0;
}

function validConfirmPassword(mess1) {
    var result = commonValidConfirmPassword2();
    if (result == 1) {
        $('#confirm-password-error').val(mess1);
        return false;
    } else {
        $('#confirm-password-error').val('');
        return true;
    }
}