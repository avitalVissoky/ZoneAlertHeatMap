package com.example.zonealertheatmap.Common;

public class Utils {
    public static String generateLocationKey(double lat, double lon) {
        lat = roundToPrecision(lat, 7);
        lon = roundToPrecision(lon, 7);

        return String.valueOf(lat).replace(".", "_") + "-" +
                String.valueOf(lon).replace(".", "_");
    }

    private static double roundToPrecision(double value, int precision) {
        double scale = Math.pow(10, precision);
        return Math.round(value * scale) / scale;
    }
}
