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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Node;

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
import cl.vicentepc.miappprueba3.views.dashboard.DashboardFragment;
import cl.vicentepc.miappprueba3.views.results.ResultActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRouteName, editTextDate, editTextAddress, editTextDescription;

    private Button pick_a_date;

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

        TextView textViewPlaceList = findViewById(R.id.textViewPlaceList);

        pick_a_date = findViewById(R.id.dateBtn);

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

        pick_a_date.setOnClickListener(new View.OnClickListener() {
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

                if (editTextRouteName.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "CAMPO NOMBRE RUTA ESTÁ VACÍO", Toast.LENGTH_LONG).show();
                } else if (editTextDate.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "CAMPO FECHA ESTÁ VACÍO(*)", Toast.LENGTH_LONG).show();
                } else if (editTextAddress.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "CAMPO FECHA ESTÁ VACÍO(*)", Toast.LENGTH_LONG).show();
                } else if (editTextDescription.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "CAMPO DESCRIPCIÓN ESTÁ VACÍO(*)", Toast.LENGTH_LONG).show();
                } else {

                    String routeName = editTextRouteName.getText().toString();
                    String routeDate = editTextDate.getText().toString();
                    String routeAddress = editTextAddress.getText().toString();
                    String routeDescription = editTextDescription.getText().toString();

                    CurrentUser currentUser = new CurrentUser();

                    String date = new SimpleDateFormat("MM/dd/yy", Locale.US).format(new Date());

                    Place place = new Place();
                    place.setRute(routeName);
                    place.setDate(date);
                    place.setAddress(routeAddress);
                    place.setDescription(routeDescription);

                    String key = currentUser.sanitizedEmail(currentUser.email());
                    String key_place = new Nodes().place(key).push().getKey();

                    DatabaseReference duplication = new Nodes().places().child("places");

                    String duplicate_key_place = duplication.push().getKey();

                    place.setKey_place(duplicate_key_place);

                   //duplication.child(duplicate_key_place).setValue(place);

                    new Nodes().place(key).setValue(place);

                    Toast.makeText(MainActivity.this, "TU RUTA HA SIDO GUARDADA", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("key", place);
                    startActivity(intent);

                }

            }
        });

        textViewPlaceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTextDate.setText(sdf.format(myCalendar.getTime()));

    }

}
