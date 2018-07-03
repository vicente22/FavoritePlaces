package cl.vicentepc.miappprueba3.views.results;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.models.Place;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
}
