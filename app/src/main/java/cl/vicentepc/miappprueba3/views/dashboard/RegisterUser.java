package cl.vicentepc.miappprueba3.views.dashboard;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import cl.vicentepc.miappprueba3.data.CurrentUser;
import cl.vicentepc.miappprueba3.data.Nodes;
import cl.vicentepc.miappprueba3.models.LocalUser;

public class RegisterUser {

    private Context context;

    public RegisterUser(Context context){
        this.context = context;
    }

    public void firebaseUserRegister(){

        final CurrentUser currentUser = new CurrentUser();

        LocalUser localuser = new LocalUser();
        localuser.setName(currentUser.getCurrentUser().getDisplayName());
        localuser.setEmail(currentUser.email());
        localuser.setUid(currentUser.uid());
        String key = currentUser.sanitizedEmail(currentUser.email());
        new Nodes().user(key).setValue(localuser);
        FirebaseDatabase.getInstance().getReference().child("users").child(key).setValue(localuser);

    }

}
