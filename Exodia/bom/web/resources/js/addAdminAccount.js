/**
 * Created by manlm1 on 10/5/2015.
 */

function validOnSubmit(mess1, mess2, mess3, regex1) {
    if (!validUsername(mess1)) {
        $("#username").focus();
    } else if (!validEmail(mess2, mess3, regex1)) {
        $("#email").focus();
    } else {
        $("#add-form").submit();
    }
}