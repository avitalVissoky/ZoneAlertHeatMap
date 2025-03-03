package com.example.zonealertheatmap.Database;
import android.util.Log;

import com.example.zonealertheatmap.Entities.LocationData;
import com.google.maps.android.heatmaps.WeightedLatLng;
import java.util.List;

public class DBController {

    private FSLocationData fsLocationData;

    public DBController(){
        fsLocationData = new FSLocationData();
    }

    public void getDataFromDbRequest(){
        fsLocationData.setDataRequested("user1",true, isRequestChanged->{
            if(isRequestChanged){
                Log.d("DBController", "request status set to true in db" );
            }
        });
    }

    public void getHeatMapData(FSLocationData.CallBack<List<WeightedLatLng>> callBack) {
        fsLocationData.loadHeatMapData(callBack);
    }

    public void getLocationData(String locationKey, FSLocationData.CallBack<LocationData> callBack){
        fsLocationData.getLocationData("user1",locationKey,callBack);
    }
}
