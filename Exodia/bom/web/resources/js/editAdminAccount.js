/**
 * Created by manlm1 on 10/7/2015.
 */

function validOnSubmit(mess1, mess2, regex, btn) {
    if (!validEmail(mess1, mess2, regex)) {
        $("#email").focus();
    } else {
        $("#btn-update").val(btn);
        $("#btn-update").prop('disabled', true);
        $("#update-form").submit();
    }
}