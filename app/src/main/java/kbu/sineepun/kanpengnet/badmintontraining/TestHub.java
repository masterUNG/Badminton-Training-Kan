package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class TestHub extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView hub1ImageView, hub2ImageView,
            hub3ImageView, hub4ImageView,
            hub5ImageView, graphImageView;
    private MyConstance myConstance = new MyConstance();
    private int[] pointInts;
    private MyManage myManage;

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
        hub2ImageView.setOnClickListener(this);
        hub3ImageView.setOnClickListener(this);
        hub4ImageView.setOnClickListener(this);
        hub5ImageView.setOnClickListener(this);
        graphImageView.setOnClickListener(this);

        myManage = new MyManage(this);


    }   // Main Method

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgGraph) {
            //Click Graph

            pointInts = myConstance.getPointInts();
            for (int i=0;i<pointInts.length;i++) {
                Log.d("5SepV4", "Point(" + i + ") ==> " + pointInts[i]);
            }

        } else {
            myIntent(view.getId());
        }

    }   // onClick

    private void myIntent(int intID) {

        int intResult = 0;

        switch (intID) {
            case R.id.imvHub1:
                intResult = 0;
                break;
            case R.id.imvHub2:
                intResult = 1;
                break;
            case R.id.imvHub3:
                intResult = 2;
                break;
            case R.id.imvHub4:
                intResult = 3;
                break;
            case R.id.imvHub5:
                intResult = 4;
                break;
        }

        Intent intent = new Intent(TestHub.this, SubTest.class);
        intent.putExtra("Index", intResult);
        startActivity(intent);

    }   // myIntent

}   // Main Class
