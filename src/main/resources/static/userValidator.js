function registerValidate(){
    var login = document.forms["register-form"]["login"].value;
    var pass = document.forms["register-form"]["pass"].value;
    var pass2 = document.forms["register-form"]["pass2"].value;
    var flag = true;


    if(!lengthValidate(login)){
        document.getElementById("login").style.backgroundColor = "red";
        document.getElementById("login-err").innerHTML = "Login has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("login").style.backgroundColor = "white";
        document.getElementById("login-err").innerHTML = "";
    }

    if(!lengthValidate(pass)){
        document.getElementById("pass").style.backgroundColor = "red";
        document.getElementById("pass-err").innerHTML = "Password has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("pass").style.backgroundColor = "white";
        document.getElementById("pass-err").innerHTML = "";
    }

    if(!lengthValidate(pass2)){
        document.getElementById("pass2").style.backgroundColor = "red";
        document.getElementById("pass2-err").innerHTML = "Repeat Password has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("pass2").style.backgroundColor = "white";
        document.getElementById("pass2-err").innerHTML = "";
    }

    if(pass!==pass2){
        document.getElementById("pass").style.backgroundColor = "red";
        document.getElementById("pass2").style.backgroundColor = "red";
        document.getElementById("equal-err").innerHTML = "Password and Repeat Password have to be the same";
        flag = false;
    }else if(flag){
        document.getElementById("pass").style.backgroundColor = "white";
        document.getElementById("pass2").style.backgroundColor = "white";
        document.getElementById("equal-err").innerHTML = "";
    }else{
        document.getElementById("equal-err").innerHTML = "";
    }


    return flag;
}

function loginValidate(){
    var login = document.forms["login-form"]["login"].value;
    var pass = document.forms["login-form"]["pass"].value;
    var flag = true;


    if(!lengthValidate(login)){
        document.getElementById("login").style.backgroundColor = "red";
        document.getElementById("login-err").innerHTML = "Login has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("login").style.backgroundColor = "white";
        document.getElementById("login-err").innerHTML = "";
    }

    if(!lengthValidate(pass)){
        document.getElementById("pass").style.backgroundColor = "red";
        document.getElementById("pass-err").innerHTML = "Password has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("pass").style.backgroundColor = "white";
        document.getElementById("pass-err").innerHTML = "";
    }


    return flag;
}

function lengthValidate(text){
    var regex = new RegExp(/[\wąćęłńóśźż.-]{5}.*/);

    return regex.test(text);
}