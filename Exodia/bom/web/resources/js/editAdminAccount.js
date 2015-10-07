/**
 * Created by manlm1 on 10/7/2015.
 */

function validOnSubmit(mess1, mess2, regex) {
    if (!validEmail(mess1, mess2, regex)) {
        $("#email").focus();
    } else {
        $("#update-form").submit();
    }
}