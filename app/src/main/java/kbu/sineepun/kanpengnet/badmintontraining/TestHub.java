package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class TestHub extends AppCompatActivity implements View.OnClickListener {

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
        graphImageView = (ImageView) findViewById(R.id.imgGraph);

        //Image Controller
        hub1ImageView.setOnClickListener(this);
        hub1ImageView.setOnClickListener(this);
        hub1ImageView.setOnClickListener(this);
        hub1ImageView.setOnClickListener(this);
        hub1ImageView.setOnClickListener(this);
        graphImageView.setOnClickListener(this);


    }   // Main Method

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgGraph) {
            //Click Graph
        } else {
            myIntent(view.getId());
        }

    }   // onClick

    private void myIntent(int intID) {

        int intResult = 1;

        switch (intID) {
            case R.id.imvHub1:
                intResult = 1;
                break;
            case R.id.imvHub2:
                intResult = 2;
                break;
            case R.id.imvHub3:
                intResult = 3;
                break;
            case R.id.imvHub4:
                intResult = 4;
                break;
            case R.id.imvHub5:
                intResult = 5;
                break;
        }

        Intent intent = new Intent();

    }   // myIntent

}   // Main Class
