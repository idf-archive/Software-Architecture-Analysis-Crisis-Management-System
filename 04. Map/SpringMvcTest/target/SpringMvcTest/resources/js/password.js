/**
 * Created by Danyang on 10/16/13.
 */
/*password validation javascript*/
/*
Not working, tightly coupled with html
 */
function passwordcheck(){
    var password = document.getElementById("id_password1").value;
    var count1 = 0;
    var count2 = 0;
    var count3 = 0;

    if(password.length<8){
        document.getElementById("passwordcheckError").innerHTML="The password should be at least 8 characters";
        document.getElementById("id_password1").focus();
        document.getElementById("submitButton").disabled=true;
        return false;
    }



    for(var j=0;j<password.length;j++){
                if(/[0-9]/.test(password.charAt(j))){
                    count2++;
            }
        }
    if(count2==0){
                document.getElementById("passwordcheckError").innerHTML="The password must contain at least 1 number.";
        document.getElementById("id_password1").focus();
        document.getElementById("submitButton").disabled=true;
        return false;
    }

    for(var k=0;k<password.length;k++){
                    if(/[a-z]/.test(password.charAt(k))){
                        count3++;
                    }
                }
    if(count3==0){
                    document.getElementById("passwordcheckError").innerHTML="The password must contain at least 1 alphabet.";
        document.getElementById("id_password1").focus();
        document.getElementById("submitButton").disabled=true;
        return false;
    }

    for(var i=0;i<password.length;i++){
        if(/[A-Z]/.test(password.charAt(i))){
            count1++;
        }
    }
    if(count1==0){
            document.getElementById("passwordcheckError").innerHTML="The password must contain at least 1 uppercase character.";
        document.getElementById("id_password1").focus();
        document.getElementById("submitButton").disabled=true;
        return false;
    }
    else{
                    document.getElementById("passwordcheckError").innerHTML="";
                    document.getElementById("submitButton").disabled=false;
        }


}