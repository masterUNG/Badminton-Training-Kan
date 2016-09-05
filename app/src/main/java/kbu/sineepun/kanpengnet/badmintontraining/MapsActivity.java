package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }// main Method

    private class SynCourt extends AsyncTask<Void, Void, String> {
        //explicit
        private Context context;
        private GoogleMap courtGoogleMap;
        private static final String urlJON = "http://swiftcodingthai.com/kan/get_court.php";

        public SynCourt(Context context, GoogleMap courtGoogleMap) {
            this.context = context;
            this.courtGoogleMap = courtGoogleMap;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }
        }// doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("kanV3", "JSON ==>" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i += 1) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString("Name");
                    String strAddress = jsonObject.getString("Address");
                    String strPhone = jsonObject.getString("Phone");
                    String strLat = jsonObject.getString("Lat");
                    String strLng = jsonObject.getString("Lng");

                    LatLng latLng = new LatLng(Double.parseDouble(strLat),
                            Double.parseDouble(strLng));
                    courtGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(strName)
                    .snippet(strPhone));
                }//for
            } catch (Exception e) {
                e.printStackTrace();
             }
        }// onPost

    } //SynCourt class

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double lat = 13.739227;
        double lng = 100.627310;
        LatLng latLng = new LatLng(lat, lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));


        SynCourt synCourt = new SynCourt(this, mMap);
        synCourt.execute();

    } // onMapReady
}// main class
