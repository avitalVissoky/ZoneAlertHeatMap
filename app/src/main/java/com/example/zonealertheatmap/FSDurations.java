package com.example.zonealertheatmap;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.ArrayList;
import java.util.List;

public class FSDurations {

    public interface CallBack<T> {
        void res(T res);
    }

    public static void setDataRequested(String userId,boolean isDataRequested, CallBack<Boolean> callBack){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference requestStatusRef = database.getReference("RequestStatus").child(userId);

        requestStatusRef.setValue(isDataRequested).addOnCompleteListener(task -> {
            if(task.isSuccessful())
                callBack.res(true);
        });
    }

    public static void loadHeatMapData(CallBack<List<WeightedLatLng>> callBack) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("durations").child("user1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<WeightedLatLng> weightedLocations = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {
                    String locKey = data.getKey();
                    LatLng latLng = getLatLonFromLocationKey(locKey);
                    long duration = data.getValue(Long.class);
                    weightedLocations.add(new WeightedLatLng(latLng, duration));
                }
                callBack.res(weightedLocations);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error loading heatmap data", error.toException());
            }
        });
    }

    public static LatLng getLatLonFromLocationKey(String locationKey){
        String normalizedKey = locationKey.replace("_", ".");
        String[] parts = normalizedKey.split("-");

        double lat = Double.parseDouble(parts[0]);
        double lon = Double.parseDouble(parts[1]);
        return new LatLng(lat,lon);
    }
}
