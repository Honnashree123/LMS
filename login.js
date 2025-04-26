function myfunction(){
    var x=document.getElementById("pass");
    
    if(x.type === "password")
    {
        x.type = "text";
    }

    else{
        x.type= "password";
    }

    var password = document.getElementById("pass");
    var length = document.getElementById("length");

    if(password.value.length >=8)
    {
        alert("Login successfull");
        return false;
    
    }
    else{
        alert("Login failed");
    }
}