package com.example.zonealertheatmap.Activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zonealertheatmap.DBController;
import com.example.zonealertheatmap.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.material.button.MaterialButton;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MaterialButton btnRefresh;
    DBController dbController;
    private TileOverlay mTileOverlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbController= new DBController();
        findViews();
        btnRefresh.setOnClickListener(v->{
            dbController.getDataFromDbRequest();
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    private void findViews(){
        btnRefresh = findViewById(R.id.btnRefresh);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        dbController.getHeatMapData(weightedLocations->{
            if(!weightedLocations.isEmpty()){
                displayHeatMap(weightedLocations);
            }
        });
    }

    private void displayHeatMap(List<WeightedLatLng> weightedLocations) {
        Gradient gradient = new Gradient(
                new int[]{Color.GREEN, Color.YELLOW, Color.RED},
                new float[]{0.2f, 0.5f, 1f}
        );

        int customRadius = 30;
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .weightedData(weightedLocations)
                .gradient(gradient)
                .radius(customRadius)
                .build();

        if (mMap != null) {
            if (mTileOverlay != null) {
                mTileOverlay.clearTileCache();
            }
            mTileOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
        }
    }
}