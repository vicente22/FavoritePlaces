package cl.vicentepc.miappprueba3.views.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import cl.vicentepc.miappprueba3.PlacesListeners;
import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.adapters.PlacesAdapter;
import cl.vicentepc.miappprueba3.data.Nodes;
import cl.vicentepc.miappprueba3.models.Place;
import cl.vicentepc.miappprueba3.views.results.PlaceInfoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultPlaceFragment extends Fragment implements PlacesListeners{

    public static final String PLACE  = "cl.vicentepc.miappprueba3.KEY.PLACE";

    private ProgressDialog progressDialog;

    public ResultPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_place, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.show();

        RecyclerView recyclerView = view.findViewById(R.id.resultPlaceRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        FirebaseRecyclerOptions<Place> options = new FirebaseRecyclerOptions.Builder<Place>()
                .setQuery(new Nodes().places(), Place.class)
                .setLifecycleOwner(this)
                .build();

        PlacesAdapter placesAdapter = new PlacesAdapter(this,options);

        recyclerView.setAdapter(placesAdapter);


    }

    @Override
    public void clicked(Place place) {

        Intent intent = new Intent(getContext(), PlaceInfoActivity.class);
        intent.putExtra(PLACE, place);
        startActivity(intent);

    }

    @Override
    public void dataChanged() {
        Toast.makeText(getContext(), "SINCRONIZANDO", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }
}
