package cl.vicentepc.miappprueba3.views.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.data.CurrentUser;
import cl.vicentepc.miappprueba3.data.Nodes;
import cl.vicentepc.miappprueba3.models.Place;
import cl.vicentepc.miappprueba3.views.results.ResultActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRouteName;
    private EditText editTextDate;
    private EditText editTextAddress;
    private EditText editTextDescription;

    private DatePickerDialog.OnDateSetListener thisdate;

    private Calendar myCalendar;

    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextRouteName = findViewById(R.id.ediTextRoute);
        editTextDate = findViewById(R.id.editTextVisitDate);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDescription = findViewById(R.id.editTextDescription);

        sendBtn = findViewById(R.id.sendBtn);

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

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, thisdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String routeName = editTextRouteName.getText().toString();
                String routeDate = editTextDate.getText().toString();
                String routeAddress = editTextAddress.getText().toString();
                String routeDescription = editTextDescription.getText().toString();

                CurrentUser currentUser = new CurrentUser();

                String date = new SimpleDateFormat("MM/dd/yy HH:mm:ss", Locale.US).format(new Date());

                Place place = new Place();
                place.setRute(routeName);
                place.setDate(date);
                place.setAddress(routeAddress);
                place.setDescription(routeDescription);

                String key = currentUser.sanitizedEmail(currentUser.email());
                String key_place = new Nodes().place(key).push().getKey();
                new Nodes().place(key).child(key_place).setValue(place);

                Toast.makeText(MainActivity.this, "Tu ruta favorita ha sido guardada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("routeName", routeName);
                intent.putExtra("routeDate", routeDate);
                intent.putExtra("routeAddress", routeAddress);
                intent.putExtra("routeDescription", routeDescription);
                startActivity(intent);


            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy HH:mm:ss"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }

}
