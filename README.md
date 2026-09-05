# Mubeen's Maps App — Android 4.4.4 / KitKat

Standalone Google Maps prototype for the vehicle tablet.

## Compatibility
- Minimum Android: 4.4 / API 19
- Target Android: API 19
- Maps SDK for Android: 18.2.0 (last client library listed by Google for KitKat)
- Java only; no AndroidX/AppCompat

## IMPORTANT — API key
Before the map can display, replace `YOUR_GOOGLE_MAPS_API_KEY` in:
`app/src/main/AndroidManifest.xml`
with an API key from your Google Cloud project, with Maps SDK for Android enabled.

## Build
The GitHub Actions workflow uses Java 17 and Gradle 7.5.

## Current prototype
- Full-screen landscape Google Maps
- Map pan/zoom/compass
- My Location layer
- Starts centered over Pakistan
- Designed for the Android 4.4.4 vehicle tablet

Turn-by-turn navigation can be added as a separate next stage after this basic map is tested on the tablet.
