package com.example.abhishek.version_18;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    HashMap<String,LatLng> hashMap;
    //private MySupportMapFragment mSupportMapFragment;
    private NestedScrollView scrollView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_map);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout_map);
        collapsingToolbarLayout.setTitle("Map");

        scrollView = findViewById(R.id.scrollView_map);

        hashMap = new HashMap<>();
        hashMap.put("Main Gate",new LatLng(10.754192, 78.819805));
        hashMap.put("Saphire",new LatLng(10.765675, 78.814412));
        hashMap.put("EEE",new LatLng(10.759158, 78.814657));
        hashMap.put("Lyceum",new LatLng(10.760618, 78.817359));
        hashMap.put("Mess",new LatLng(10.762762, 78.812909));
        hashMap.put("SBI ATM Buhari",new LatLng(10.760892, 78.819050));
        hashMap.put("SBI ATM EEE",new LatLng(10.759432, 78.813989));

       /* mSupportMapFragment = (MySupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        if(mSupportMapFragment != null)
            mSupportMapFragment.setListener(new MySupportMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
            });*/
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Spinner spinner = findViewById(R.id.spinner_map);
        String[] items = new String[] {"Main Gate","Saphire","EEE","Lyceum","Mess","SBI ATM Buhari","SBI ATM EEE"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                LatLng latLng = hashMap.get((String)parent.getItemAtPosition(position));
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f",latLng.latitude, latLng.longitude);
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");

                button = findViewById(R.id.button_map);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }

                });

                

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        // Add a marker in NIT Trichy, and move the camera.



        mMap.addMarker(new MarkerOptions().position(hashMap.get("Saphire")).title("Sapphire").snippet("Sapphire Hostel"));
        mMap.addMarker(new MarkerOptions().position(hashMap.get("EEE")).title("EEE").snippet("EEE Auditorium"));
        mMap.addMarker(new MarkerOptions().position(hashMap.get("Lyceum")).title("Lyceum").snippet("Department of CA"));
        mMap.addMarker(new MarkerOptions().position(hashMap.get("Main Gate")).title("Main Gate").snippet("National Institute of Technology,Tiruchirapalli"));
        mMap.addMarker(new MarkerOptions().position(hashMap.get("Mess")).title("Mess A").snippet("Mess"));
        mMap.addMarker(new MarkerOptions().position(hashMap.get("SBI ATM Buhari")).title("State Bank ATM").snippet("Buhari"));
        mMap.addMarker(new MarkerOptions().position(hashMap.get("SBI ATM EEE")).title("State Bank ATM").snippet("Front of EEE"));

        //Move Camera zoom to NIT Main

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hashMap.get("Main Gate"),15));
    }

    /*public static class MySupportMapFragment extends SupportMapFragment {

        private OnTouchListener mListener;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

            View layout = super.onCreateView(inflater, parent, savedInstanceState);

            TouchableWrapper frameLayout = new TouchableWrapper(getActivity());
            frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            ((ViewGroup) layout).addView(frameLayout,
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            return layout;
        }

        public void setListener(OnTouchListener listener) {
            mListener = listener;
        }

        public interface OnTouchListener {
            public abstract void onTouch();
        }

        public class TouchableWrapper extends FrameLayout {

            public TouchableWrapper(Context context) {
                super(context);
            }

            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mListener.onTouch();
                        break;
                    case MotionEvent.ACTION_UP:
                        mListener.onTouch();
                        break;
                }
                return super.dispatchTouchEvent(event);
            }
        }
    }*/

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();
        finish();// optional depending on your needs
    }
}
