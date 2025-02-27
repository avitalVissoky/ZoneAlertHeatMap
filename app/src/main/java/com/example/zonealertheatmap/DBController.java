package com.example.zonealertheatmap;

import android.util.Log;

import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.List;

public class DBController {

    private FSDurations fsDurations;

    public DBController(){
        fsDurations = new FSDurations();
    }

    public void getDataFromDbRequest(){
        fsDurations.setDataRequested("user1",true, isRequestChanged->{
            if(isRequestChanged){
                Log.d("DBController", "request status set to true in db" );
            }
        });
    }

    public void getHeatMapData(FSDurations.CallBack<List<WeightedLatLng>> callBack) {
        FSDurations.loadHeatMapData(callBack);
    }
}
