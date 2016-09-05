package kbu.sineepun.kanpengnet.badmintontraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class TestHub extends AppCompatActivity {

    //Explicit
    private ImageView hub1ImageView, hub2ImageView,
            hub3ImageView, hub4ImageView,
            hub5ImageView, graphImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hub);

        //Bind Widget
        hub1ImageView = (ImageView) findViewById(R.id.imvHub1);
        hub2ImageView = (ImageView) findViewById(R.id.imvHub2);
        hub3ImageView = (ImageView) findViewById(R.id.imvHub3);
        hub4ImageView = (ImageView) findViewById(R.id.imvHub4);
        hub5ImageView = (ImageView) findViewById(R.id.imvHub5);


    }   // Main Method

}   // Main Class
