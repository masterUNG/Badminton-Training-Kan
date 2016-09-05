package kbu.sineepun.kanpengnet.badmintontraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    }   // Main Method

}   // Main Class
