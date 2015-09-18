/**
 * Created by manlm1 on 9/14/2015.
 */

var isValidUsername = 0;
var isValidPassword = 0;

function validEmail() {
    var email = document.getElementById()
    var pattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    return pattern.test(emailAddress);
}

function validUsername(mess1) {
    var result = commonValidUsername();
    if (result == 1) {
        document.getElementById("div-username").style.marginBottom = "0px";
        document.getElementById("username-error").style.display = "block";
        document.getElementById("username-error").innerHTML = mess1;
        isValidUsername = 0;
        document.getElementById("btn-login").disabled = true;
    } else {
        document.getElementById("div-username").style.marginBottom = "25px";
        document.getElementById("username-error").style.display = "none";
        document.getElementById("username-error").innerHTML = "";
        isValidUsername = 1;
        if (isValidPassword == 1) {
            document.getElementById("btn-login").disabled = false;
        }
    }
}

function validPassword(mess1, mess2, mess3, regex) {
    var result = commonValidPassword(regex);
    if (result == 1) {
        document.getElementById("div-password").style.marginBottom = "0px";
        document.getElementById("password-error").style.display = "block";
        document.getElementById("password-error").innerHTML = mess1;
        isValidPassword = 0;
        document.getElementById("btn-login").disabled = true;
    } else if (result == 2) {
        document.getElementById("div-password").style.marginBottom = "0px";
        document.getElementById("password-error").style.display = "block";
        document.getElementById("password-error").innerHTML = mess2;
        isValidPassword = 0;
        document.getElementById("btn-login").disabled = true;
    } else if (result == 3) {
        document.getElementById("div-password").style.marginBottom = "0px";
        document.getElementById("password-error").style.display = "block";
        document.getElementById("password-error").innerHTML = mess3;
        isValidPassword = 0;
        document.getElementById("btn-login").disabled = true;
    } else {
        document.getElementById("div-password").style.marginBottom = "25px";
        document.getElementById("password-error").style.display = "none";
        document.getElementById("password-error").innerHTML = "";
        isValidPassword = 1;
        if (isValidUsername == 1) {
            document.getElementById("btn-login").disabled = false;
        }
    }
}