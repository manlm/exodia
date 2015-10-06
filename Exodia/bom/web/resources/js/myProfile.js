/**
 * Created by manlm1 on 10/6/2015.
 */

function validOnSubmit(mess1, mess2, regex1, mess3, mess4, mess5, regex2, mess6) {
    if (!validEmail(mess1, mess2, regex1)) {
        $("#email").focus();
    } else if (!validOldPassword(mess3, mess4, mess5, regex2)) {
        $("#old-password").focus();
    } else if (!validNewPassword(mess3, mess4, mess5, regex2)) {
        $("#new-password").focus();
    } else if (!validConfirmPassword(mess6)) {
        $("#confirm-password").focus();
    } else {
        $("#profile-form").submit();
    }
}