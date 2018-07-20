package cl.vicentepc.miappprueba3.views.results;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.data.Nodes;
import cl.vicentepc.miappprueba3.models.Place;
import cl.vicentepc.miappprueba3.views.fragments.ResultPlaceFragment;

public class PlaceInfoActivity extends AppCompatActivity {

    private EditText routeNameEt, routeDateEt, routeAddressEt, routeDescriptionEt;

    private Button dateInfoBtn;

    private DatePickerDialog.OnDateSetListener thisdate;

    private Calendar myCalendar;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        final Place place = (Place) getIntent().getSerializableExtra(ResultPlaceFragment.PLACE);

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        getSupportActionBar().setTitle(place.getRute());

        dateInfoBtn = findViewById(R.id.dateInfoBtn);

        routeNameEt = findViewById(R.id.routeNameEt);
        routeDateEt = findViewById(R.id.routeDateEt);
        routeAddressEt = findViewById(R.id.routeAddressEt);
        routeDescriptionEt = findViewById(R.id.routeDescriptionEt);

        Button saveBtn = findViewById(R.id.saveBtn);

        String routeNameText = place.getRute();
        String routeDateText = place.getDate();
        String routeAddressText = place.getAddress();
        String routeDescriptionText = place.getDescription();


        if (routeNameText != null) {

            routeNameEt.setText(routeNameText);
            routeDateEt.setText(routeDateText);
            routeAddressEt.setText(routeAddressText);
            routeDescriptionEt.setText(routeDescriptionText);

        }

        routeNameText = routeNameEt.getText().toString();
        routeDateText = routeDateEt.getText().toString();
        routeAddressText = routeAddressEt.getText().toString();
        routeDescriptionText = routeDescriptionEt.getText().toString();

        place.setRute(routeNameText);
        place.setDate(routeDateText);
        place.setAddress(routeAddressText);
        place.setDescription(routeDescriptionText);

        myCalendar = Calendar.getInstance();

        thisdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlaceInfoActivity.this, thisdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        updateLabel();
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (routeNameEt.getText().toString().trim().length() <= 0) {
                    Toast.makeText(PlaceInfoActivity.this, "CAMPO RUTA ESTÁ VACÍO", Toast.LENGTH_LONG).show();
                } else if (routeDateEt.getText().toString().trim().length() <= 0) {
                    Toast.makeText(PlaceInfoActivity.this, "CAMPO FECHA ESTÁ VACÍO(*)", Toast.LENGTH_LONG).show();
                } else if (routeAddressEt.getText().toString().trim().length() <= 0) {
                    Toast.makeText(PlaceInfoActivity.this, "CAMPO FECHA ESTÁ VACÍO(*)", Toast.LENGTH_LONG).show();
                } else if (routeDescriptionEt.getText().toString().trim().length() <= 0) {
                    Toast.makeText(PlaceInfoActivity.this, "CAMPO DESCRIPCIÓN ESTÁ VACÍO(*)", Toast.LENGTH_LONG).show();
                } else {

                    //new Nodes().place(place.getKey_place()).setValue(place);

                    Query updateQuery = new Nodes().place(place.getKey_place());

                    updateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Objects.requireNonNull(dataSnapshot.getRef().getParent()).setValue(place);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });

                    Toast.makeText(PlaceInfoActivity.this, "RUTA EDITADA CON ÉXITO", Toast.LENGTH_LONG).show();
                    onBackPressed();

                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        routeDateEt.setText(sdf.format(myCalendar.getTime()));

    }

}
