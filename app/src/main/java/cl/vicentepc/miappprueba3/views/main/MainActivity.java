package cl.vicentepc.miappprueba3.views.main;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.data.Nodes;
import cl.vicentepc.miappprueba3.models.Place;
import cl.vicentepc.miappprueba3.views.results.ResultActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRouteName;
    private EditText editTextDate;
    private EditText editTextAddress;
    private EditText editTextDescription;

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

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

                String routeName = editTextRouteName.getText().toString();
                String routeDate = editTextDate.getText().toString();
                String routeAddress = editTextAddress.getText().toString();
                String routeDescription = editTextDescription.getText().toString();

                Place place = new Place();
                place.setRute(routeName);
                try {
                    place.setDate(format.parse(routeDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                place.setAddress(routeAddress);
                place.setDescription(routeDescription);

                Toast.makeText(MainActivity.this, "Tu ruta favorita ha sido guardada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("routeName", routeName);
                intent.putExtra("routeDate", routeDate);
                intent.putExtra("routeAddress", routeAddress);
                intent.putExtra("routeDescription", routeDescription);
                startActivity(intent);


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.photoFloating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

}
