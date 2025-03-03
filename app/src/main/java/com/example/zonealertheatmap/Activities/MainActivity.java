package com.example.zonealertheatmap.Activities;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.zonealertheatmap.Database.DBController;
import com.example.zonealertheatmap.UI.LocationInfoDialog;
import com.example.zonealertheatmap.Managers.MapManager;
import com.example.zonealertheatmap.R;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements MapManager.OnLocationClickListener {

    private MapManager mapManager;
    private DBController dbController;
    private MaterialButton btnRefresh;
    private LocationInfoDialog locationInfoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbController = new DBController();
        locationInfoDialog = new LocationInfoDialog(this);
        mapManager = new MapManager(this, this);

        findViews();
        btnRefresh.setOnClickListener(v -> dbController.getDataFromDbRequest());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapManager.initializeMap(mapFragment);
    }

    private void findViews() {
        btnRefresh = findViewById(R.id.btnRefresh);
    }

    @Override
    public void onLocationClicked(String locationKey) {
        dbController.getLocationData(locationKey, locationData -> {
            if (locationData != null) {
                locationInfoDialog.show(locationData);
            } else {
                Toast.makeText(this, "No data found for this location.", Toast.LENGTH_SHORT).show();
            }
        });
    }



}