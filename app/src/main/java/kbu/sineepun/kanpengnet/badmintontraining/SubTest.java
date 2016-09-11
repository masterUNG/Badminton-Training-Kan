package kbu.sineepun.kanpengnet.badmintontraining;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

public class SubTest extends AppCompatActivity {

    //Explicit
    private int indexAnInt;
    private MyConstance myConstance = new MyConstance();
    private String[] subVideo = myConstance.getVideoStrings();
    private String pathVideo = "android.resource://kbu.sineepun.kanpengnet.badmintontraining/raw/";
    private VideoView videoView;
    private String[] pointStrings = myConstance.getPointStrings();
    private Spinner spinner;
    private ArrayAdapter<String> stringArrayAdapter;
    private int spinnerAnInt = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_test);

        //Bind Widget
        videoView = (VideoView) findViewById(R.id.videoView);
        spinner = (Spinner) findViewById(R.id.spinner);

        indexAnInt = getIntent().getIntExtra("Index", 0);
        Log.d("5SepV3", "Index ==> " + indexAnInt);

        videoView.setVideoPath(pathVideo + subVideo[indexAnInt]);


        Log.d("5SepV3", "path Video ==> " + (pathVideo + subVideo[indexAnInt]));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

        stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pointStrings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerAnInt = Integer.parseInt(pointStrings[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerAnInt = 20;
            }
        });

    }   // Main Method

    public void clickSaveSubTest(View view) {

        Log.d("5SepV4", "index ==> " + indexAnInt);
        Log.d("5SepV4", "Spinner ==> " + spinnerAnInt);

        // myConstance.setupPointInt(indexAnInt, spinnerAnInt);

        String[] columnStrings = new String[]{"Hub1", "Hub2", "Hub3", "Hub4", "Hub5"};
        Log.d("11SepV1", "Column ==> " + columnStrings[indexAnInt]);
        Log.d("11SepV1", "Value ==> " + spinnerAnInt);

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("UPDATE testTABLE SET " + "'" + columnStrings[indexAnInt] + "'" + " = " + "'" + Integer.toString(spinnerAnInt) + "'" + " WHERE _id = 1 " );
           // sqLiteDatabase.execSQL("UPDATE testTABLE SET Hub1=30 WHERE _id=1");

        } catch (Exception e) {
            Log.d("11SepV1", "e ==> " + e.toString());
        }


        finish();

    }

}   // Main Class
