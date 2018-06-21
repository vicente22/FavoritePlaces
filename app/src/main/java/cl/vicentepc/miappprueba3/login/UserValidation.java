package cl.vicentepc.miappprueba3.login;

import cl.vicentepc.miappprueba3.data.User;

public class UserValidation {

    private UserCallback callback;

    public UserValidation(UserCallback callback) {
        this.callback = callback;
    }

    public void validateUser(){

        if(new User().getUser()!= null){

            callback.userLogged();

        }else{

            callback.userSignUp();

        }

    }

}
