package cl.vicentepc.miappprueba3.login;

import cl.vicentepc.miappprueba3.data.CurrentUser;

public class UserValidation {

    private UserCallback callback;

    public UserValidation(UserCallback callback) {
        this.callback = callback;
    }

    public void validateUser(){

        if(new CurrentUser().getCurrentUser()!= null){

            callback.userLogged();

        }else{

            callback.userSignUp();

        }

    }

}
