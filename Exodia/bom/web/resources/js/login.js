/**
 * Created by manlm1 on 9/14/2015.
 */

function validOnSubmit(mess1, mess2, mess3, mess4, regex) {
    var isValidUsername = validUsername(mess1);
    var isValidPassword = validPassword(mess2, mess3, mess4, regex);
    if (!isValidUsername) {
        $("#login-username").focus();
    } else if (!isValidPassword) {
        $("#login-password").focus();
    } else {
        $("#loginform").submit();
    }
}

function validUsername(mess1) {
    var result = commonValidUsername();
    if (result == 1) {
        document.getElementById("div-username").style.marginBottom = "0px";
        document.getElementById("username-error").style.display = "block";
        document.getElementById("username-error").innerHTML = mess1;
        return false;
    } else {
        document.getElementById("div-username").style.marginBottom = "25px";
        document.getElementById("username-error").style.display = "none";
        document.getElementById("username-error").innerHTML = "";
        return true;
    }
}

function validPassword(mess1, mess2, mess3, regex) {
    var result = commonValidPassword(regex);
    if (result == 1) {
        document.getElementById("div-password").style.marginBottom = "0px";
        document.getElementById("password-error").style.display = "block";
        document.getElementById("password-error").innerHTML = mess1;
        return false;
    } else if (result == 2) {
        document.getElementById("div-password").style.marginBottom = "0px";
        document.getElementById("password-error").style.display = "block";
        document.getElementById("password-error").innerHTML = mess2;
        return false;
    } else if (result == 3) {
        document.getElementById("div-password").style.marginBottom = "0px";
        document.getElementById("password-error").style.display = "block";
        document.getElementById("password-error").innerHTML = mess3;
        return false;
    } else {
        document.getElementById("div-password").style.marginBottom = "25px";
        document.getElementById("password-error").style.display = "none";
        document.getElementById("password-error").innerHTML = "";
        return true;
    }
}