function appValidate(){
    var name = document.forms["app-add-form"]["name"].value;
    var type = document.forms["app-add-form"]["type"].value;
    var description = document.forms["app-add-form"]["description"].value;
    var flag = true;


    if(!lengthValidate(name,5)){
        document.getElementById("name").style.backgroundColor = "red";
        document.getElementById("name-err").innerHTML = "Name has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("name").style.backgroundColor = "white";
        document.getElementById("name-err").innerHTML = "";
    }

    if(!lengthValidate(type,3)){
        document.getElementById("type").style.backgroundColor = "red";
        document.getElementById("type-err").innerHTML = "Type has to be at least length 3";
        flag = false;
    }else{
        document.getElementById("type").style.backgroundColor = "white";
        document.getElementById("type-err").innerHTML = "";
    }

    if(!lengthValidate(description,20)){
        document.getElementById("description").style.backgroundColor = "red";
        document.getElementById("description-err").innerHTML = "Description has to be at least length 20";
        flag = false;
    }else{
        document.getElementById("description").style.backgroundColor = "white";
        document.getElementById("description-err").innerHTML = "";
    }


    return flag;
}

function lengthValidate(text, length){
    var regex = new RegExp(/[A-Za-z0-9._-]{5}.*/);
    if(length===3){
        regex = new RegExp(/[A-Za-z0-9._-]{3}.*/);
    }else if(length===20){
        regex = new RegExp(/[A-Za-z0-9._-]{20}.*/);
    }

    return regex.test(text);
}