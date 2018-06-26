package cl.vicentepc.miappprueba3.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Nodes {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    public DatabaseReference users(){

        return root.child("users");

    }

    public DatabaseReference places(){

        return root.child("places");

    }

    public DatabaseReference user(String key){

        return users().child(key);

    }

    public DatabaseReference place(String key){

        return places().child(key);

    }

}
