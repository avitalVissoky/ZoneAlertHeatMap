
---

# ZoneAlertHeatMap

```markdown
# ZONEALERTHEATMAP

ZONEALERTHEATMAP is an Android application that displays a dynamic heatmap based on location duration data collected by the background tracking service. The app retrieves updated location durations from Firebase and renders them on a Google Map overlay using the Google Maps Android Utilities library.

## Key Features

- **Dynamic Heatmap Display:**
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

## Screenshots
<img src="https://github.com/user-attachments/assets/71f9afd5-6552-41c5-a4ca-3b9933233534" style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/a11d1606-c5f5-4771-9806-490c1701d9cd" style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/9450181d-30d2-4919-a14e-e99fb04073f4" style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/785b44bd-63c1-4772-a9b1-12399dede414" style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/18e7a574-dd49-4278-a4f1-8f12c6ae03fa" style="height:400px;"/>




