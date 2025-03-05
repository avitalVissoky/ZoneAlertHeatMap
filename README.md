
---

# ZoneAlertHeatMap

```markdown
# ZONEALERTHEATMAP

ZONEALERTHEATMAP is an Android application that displays a dynamic heatmap based on location duration data collected by the background tracking service. The app retrieves updated location durations from Firebase and renders them on a Google Map overlay using the Google Maps Android Utilities library.

## Key Features

<h2>Dynamic Heatmap Display:</h2>
Visualizes areas where the user has spent the most time. The heatmap uses a gradient (e.g., green for lower durations, yellow for medium, and red for high durations) to represent the total time spent at each location.

- ** Interactive Data Refresh: **  
  When the user clicks the "Refresh" button, a `RequestStatus` flag is set in Firebase. This flag triggers the forground location tracking service (in ZoneAlert app) to push updated location data into Firebase. The heatmap app listens for changes in Firebase and automatically refreshes the map overlay with the latest data.

- **Interactive Map:**  
  Tap on heatmap points to view detailed statistics such as total duration, number of visits, and average duration per visit.

- **Modern UI:**  
  Features a dark-themed user interface with Material Design components, including a refresh button and a data display area.

## Technologies

- Android SDK
- Google Maps Android API & Google Maps Android Utilities (Heatmap)
- Firebase Realtime Database
- Material Design Components

<h2>Screenshots</h2>
<div >

</div >
