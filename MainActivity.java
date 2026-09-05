package com.example.mapsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * Standalone Google Maps screen for Android 4.4.4 / API 19.
 * Uses the last Google Maps SDK client library that supports KitKat.
 */
public class MainActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        try {
            // Explicitly request the legacy renderer for old KitKat hardware.
            MapsInitializer.initialize(this, MapsInitializer.Renderer.LEGACY,
                    new MapsInitializer.OnMapsSdkInitializedCallback() {
                        @Override
                        public void onMapsSdkInitialized(MapsInitializer.Renderer renderer) {
                            MapFragment fragment = (MapFragment) getFragmentManager()
                                    .findFragmentById(R.id.map);
                            if (fragment != null) fragment.getMapAsync(MainActivity.this);
                        }
                    });
        } catch (Exception e) {
            MapFragment fragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            if (fragment != null) fragment.getMapAsync(this);
        }

        Button myLocation = (Button) findViewById(R.id.btn_my_location);
        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleMap != null) {
                    try {
                        googleMap.setMyLocationEnabled(true);
                        Toast.makeText(MainActivity.this,
                                "Location layer enabled", Toast.LENGTH_SHORT).show();
                    } catch (SecurityException e) {
                        Toast.makeText(MainActivity.this,
                                "Location permission is unavailable on this device",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        hideSystemUi();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        try {
            googleMap.setMyLocationEnabled(true);
        } catch (SecurityException ignored) {
            // The map still works if location access is unavailable.
        }

        // Start over Pakistan; the map can be moved/zoomed normally.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(30.3753, 69.3451), 5.5f));
    }

    private void hideSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemUi();
    }
}
