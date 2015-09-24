/**
 * Created by manlm1 on 9/16/2015.
 */

function validEmail(mess1, mess2, regex) {
    var result = commonValidEmail(regex);
    if (result == 1) {
        document.getElementById("div-email").style.marginBottom = "0px";
        document.getElementById("email-error").style.display = "block";
        document.getElementById("email-error").innerHTML = mess1;
        return false;
    } else if (result == 2) {
        document.getElementById("div-email").style.marginBottom = "0px";
        document.getElementById("email-error").style.display = "block";
        document.getElementById("email-error").innerHTML = mess2;
        return false;
    } else {
        document.getElementById("div-email").style.marginBottom = "25px";
        document.getElementById("email-error").style.display = "none";
        document.getElementById("email-error").innerHTML = "";
        return true;
    }
}

function validOnSubmit(mess1, mess2, regex) {
    var result = validEmail(mess1, mess2, regex);
    if (result == false) {
        $("#email").focus();
    } else {
        $("#forgotPasswordForm").submit();
    }
}