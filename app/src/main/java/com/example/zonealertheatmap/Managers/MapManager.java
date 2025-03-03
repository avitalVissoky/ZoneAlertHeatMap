package com.example.zonealertheatmap.Managers;
import static com.example.zonealertheatmap.Common.Utils.generateLocationKey;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.widget.Toast;

import com.example.zonealertheatmap.Database.DBController;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.List;

public class MapManager {
    private GoogleMap mMap;
    private TileOverlay mTileOverlay;
    private List<WeightedLatLng> mWeightedLocations;
    private final DBController dbController;
    private final Context context;
    private final OnLocationClickListener locationClickListener;

    public MapManager(Context context, OnLocationClickListener listener) {
        this.context = context;
        this.dbController = new DBController();
        this.locationClickListener = listener;
    }

    public void initializeMap(SupportMapFragment mapFragment) {
        mapFragment.getMapAsync(googleMap -> {
            this.mMap = googleMap;
            loadHeatMap();
            setupClickListener();
        });
    }

    private void loadHeatMap() {
        dbController.getHeatMapData(weightedLocations -> {
            if (!weightedLocations.isEmpty()) {
                this.mWeightedLocations = weightedLocations;
                displayHeatMap(weightedLocations);
            }
        });
    }

    private void setupClickListener() {
        mMap.setOnMapClickListener(latLng -> {
            LatLng nearestPoint = findNearestPoint(latLng);
            if (nearestPoint != null) {
                String locationKey = generateLocationKey(nearestPoint.latitude, nearestPoint.longitude);
                locationClickListener.onLocationClicked(locationKey);
            } else {
                Toast.makeText(context, "No data available at this location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private LatLng findNearestPoint(LatLng clickedLatLng) {
        float minDistance = Float.MAX_VALUE;
        LatLng bestPoint = null;

        if (mWeightedLocations != null) {
            for (WeightedLatLng point : mWeightedLocations) {
                LatLng currentPoint = mercatorToLatLng(point.getPoint().x, point.getPoint().y);
                float[] results = new float[1];
                Location.distanceBetween(clickedLatLng.latitude, clickedLatLng.longitude,
                        currentPoint.latitude, currentPoint.longitude, results);

                if (results[0] < 100 && results[0] < minDistance) {
                    minDistance = results[0];
                    bestPoint = currentPoint;
                }
            }
        }
        return bestPoint;
    }

    private void displayHeatMap(List<WeightedLatLng> weightedLocations) {
        Gradient gradient = new Gradient(
                new int[]{Color.GREEN, Color.YELLOW, Color.RED},
                new float[]{0.2f, 0.5f, 1f}
        );

        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .weightedData(weightedLocations)
                .gradient(gradient)
                .radius(30)
                .build();

        if (mMap != null) {
            if (mTileOverlay != null) {
                mTileOverlay.clearTileCache();
            }
            mTileOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
        }
    }

    private LatLng mercatorToLatLng(double x, double y) {
        double longitude = x * 360.0 - 180.0;
        double latitude = Math.toDegrees(Math.atan(Math.sinh(Math.PI * (1 - 2 * y))));
        return new LatLng(latitude, longitude);
    }

    public interface OnLocationClickListener {
        void onLocationClicked(String locationKey);
    }
}
