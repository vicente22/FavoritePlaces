package cl.vicentepc.miappprueba3.views.results;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cl.vicentepc.miappprueba3.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String resultNameRute = getIntent().getStringExtra("routeName");
        String resultRouteDate = getIntent().getStringExtra("routeDate");
        String resultRouteAddress = getIntent().getStringExtra("routeAddress");
        String resultRouteDescription = getIntent().getStringExtra("routeDescription");

        Log.d("DATA", resultNameRute);

    }
}
