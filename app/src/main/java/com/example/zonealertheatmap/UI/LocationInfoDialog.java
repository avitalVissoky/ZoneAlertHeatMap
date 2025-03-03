package com.example.zonealertheatmap.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.zonealertheatmap.Entities.LocationData;
import com.example.zonealertheatmap.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LocationInfoDialog {
    private final Context context;

    public LocationInfoDialog(Context context) {
        this.context = context;
    }

    public void show(LocationData locationData) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_location_info, null);
        bottomSheetDialog.setContentView(sheetView);

        TextView txtTotalDuration = sheetView.findViewById(R.id.txtTotalDuration);
        TextView txtTotalVisits = sheetView.findViewById(R.id.txtTotalVisits);
        TextView txtAvgDuration = sheetView.findViewById(R.id.txtAvgDuration);

        txtTotalDuration.setText("Total visits duration: " + formatTime(locationData.getTotalDuration()));
        txtTotalVisits.setText("Total visits: " + locationData.getTotalVisits());
        txtAvgDuration.setText("Average visit duration: " + formatTime((long) locationData.getAverageDuration()));

        bottomSheetDialog.show();
    }

    private String formatTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60));

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds); // HH:MM:SS
        } else {
            return String.format("00:%02d:%02d", minutes, seconds);
        }
    }
}
