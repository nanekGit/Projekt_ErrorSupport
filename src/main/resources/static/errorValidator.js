function stateValidate(){
    var state = document.forms["error-form"]["state"].value;
    var flag = true;

    if(state!=="NEW" && state!=="ACTIVE" && state!=="RESOLVED"){
        document.getElementById("state").style.backgroundColor = "red";
        document.getElementById("state-err").innerHTML = "Wrong State: "+ state;
        flag = false;
    }else{
        document.getElementById("state").style.backgroundColor = "white";
        document.getElementById("state-err").innerHTML = "";
    }

    return flag;
}

function reportErrorValidate(){
    var title = document.forms["error-form"]["title"].value;
    var description = document.forms["error-form"]["description"].value;
    var flag = true;

    if(!lengthValidate(title,5)){
        document.getElementById("title").style.backgroundColor = "red";
        document.getElementById("title-err").innerHTML = "Title has to be at least length 5";
        flag = false;
    }else{
        document.getElementById("title").style.backgroundColor = "white";
        document.getElementById("title-err").innerHTML = "";
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
    if(length===20){
        regex = new RegExp(/[A-Za-z0-9._-]{20}.*/);
    }

    return regex.test(text);
}