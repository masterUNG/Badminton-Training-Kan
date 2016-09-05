package kbu.sineepun.kanpengnet.badmintontraining;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class VideoActivity extends AppCompatActivity {
    //Explicit
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        listView = (ListView) findViewById(R.id.listView);

        //create listview
        SynchronizeVideo synchronizeVideo = new SynchronizeVideo(this, listView);
        synchronizeVideo.execute();

        //Active Back
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }//main method

    // create inner class
    private class SynchronizeVideo extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private ListView myListView;
        private static final String urlJON = "http://swiftcodingthai.com/kan/get_video.php";
        private String[] titleStrings, detailStrings,
                imageStrings, videoStrings, detailShortStrings;


        public SynchronizeVideo(Context context, ListView myListView) {
            this.context = context;
            this.myListView = myListView;
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
                Log.d("kanV1", " e = " + e.toString());
                return null;
            }

        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("kanV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                titleStrings = new String[jsonArray.length()];
                detailStrings = new String[jsonArray.length()];
                imageStrings = new String[jsonArray.length()];
                videoStrings = new String[jsonArray.length()];
                detailShortStrings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i += 1) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    titleStrings[i] = jsonObject.getString("Title");
                    detailStrings[i] = jsonObject.getString("Detail");
                    imageStrings[i] = jsonObject.getString("Image");
                    videoStrings[i] = jsonObject.getString("Video");
                    detailShortStrings[i] = detailStrings[i].substring(0, 30) + "...";

                }// for
                VideoAdapter videoAdapter = new VideoAdapter(context,
                        imageStrings, titleStrings, detailShortStrings);
                myListView.setAdapter(videoAdapter);

                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {


                        myAlertDialog(i);

                    }
                });

            } catch (Exception e) {
                Log.d("kanV1", "e onPost ==> " + e.toString());
            }

        }   //onpost

        private void myAlertDialog(final int intIndex) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setIcon(R.drawable.nonglukbad);
            builder.setTitle(titleStrings[intIndex]);
            builder.setMessage(detailStrings[intIndex]);
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Watch Video", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(videoStrings[intIndex]));
                    startActivity(intent);

                    dialog.dismiss();
                }
            });
            builder.show();

        }// MyAlertDialog


    }// synVideo Class


}//main Class
