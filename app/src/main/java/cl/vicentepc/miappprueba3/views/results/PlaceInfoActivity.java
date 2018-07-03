package cl.vicentepc.miappprueba3.views.results;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.models.Place;
import cl.vicentepc.miappprueba3.views.fragments.ResultPlaceFragment;

public class PlaceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Place place = (Place) getIntent().getSerializableExtra(ResultPlaceFragment.PLACE);
        getSupportActionBar().setTitle(place.getRute());

    }
}
