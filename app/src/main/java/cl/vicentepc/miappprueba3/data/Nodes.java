package cl.vicentepc.miappprueba3.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Nodes {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();


    public DatabaseReference places(){

        return root.child("places").child(new CurrentUser().sanitizedEmail());

    }

    public DatabaseReference place(String key){

        return places().child(key);

    }

}
