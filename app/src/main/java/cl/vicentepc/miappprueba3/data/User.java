package cl.vicentepc.miappprueba3.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {

    public User(){

    }

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getUser() {
        return user;
    }

    public String email(){
        return getUser().getEmail();
    }

}
