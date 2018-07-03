package cl.vicentepc.miappprueba3.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import cl.vicentepc.miappprueba3.PlacesListeners;
import cl.vicentepc.miappprueba3.R;
import cl.vicentepc.miappprueba3.models.Place;

public class PlacesAdapter extends FirebaseRecyclerAdapter<Place, PlacesAdapter.PlaceHolder>{

    private PlacesListeners placesListeners;

    private boolean first = true;

    public PlacesAdapter(PlacesListeners placesListeners, @NonNull FirebaseRecyclerOptions<Place> options) {
        super(options);
        this.placesListeners = placesListeners;
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_place, parent,false);
        return new PlaceHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PlaceHolder holder, int position, @NonNull Place model) {
        holder.routeName.setText(model.getRute());
        holder.routeDate.setText(model.getDate());
        holder.routeAddress.setText(model.getAddress());
        holder.routeDescription.setText(model.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place auxPlace = getItem(holder.getAdapterPosition());
                placesListeners.clicked(auxPlace);
            }
        });
    }

    @Override
    public void onDataChanged() {
        if(first){

            first = false;
            placesListeners.dataChanged();

        }
    }

    public static class PlaceHolder extends RecyclerView.ViewHolder {

        private TextView routeName, routeDate, routeAddress, routeDescription;

        public PlaceHolder(View itemView) {
            super(itemView);

            routeName = itemView.findViewById(R.id.resultRouteName);
            routeDate = itemView.findViewById(R.id.resultRouteDate);
            routeAddress = itemView.findViewById(R.id.resultRouteAddress);
            routeDescription = itemView.findViewById(R.id.resultRouteDescription);

        }
    }

}

