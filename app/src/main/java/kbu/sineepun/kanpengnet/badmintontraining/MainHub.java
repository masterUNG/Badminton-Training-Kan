package kbu.sineepun.kanpengnet.badmintontraining;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainHub extends AppCompatActivity implements View.OnClickListener {
    //Explicit
    private ImageView hub1ImageView, hub2ImageView, hub3ImageView,
            hub4ImageView, hub5ImageView, hub6ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        //Bind Widget
        hub1ImageView = (ImageView) findViewById(R.id.imageView2);
        hub2ImageView = (ImageView) findViewById(R.id.imageView3);
        hub3ImageView = (ImageView) findViewById(R.id.imageView4);
        hub4ImageView = (ImageView) findViewById(R.id.imageView5);
        hub5ImageView = (ImageView) findViewById(R.id.imageView6);
        hub6ImageView = (ImageView) findViewById(R.id.imageView7);

        hub1ImageView.setOnClickListener(this);
        hub2ImageView.setOnClickListener(this);
        hub3ImageView.setOnClickListener(this);
        hub4ImageView.setOnClickListener(this);
        hub5ImageView.setOnClickListener(this);
        hub6ImageView.setOnClickListener(this);

    }//main method


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageView2:// to กฏกติกา
                startActivity(new Intent(MainHub.this,RulesActivity.class));
                break;
            case R.id.imageView3:// to ดู Video
                startActivity(new Intent(MainHub.this,VideoActivity.class));
                break;
            case R.id.imageView4:// to นับคะแนน
                confirmCountScore();

                break;
            case R.id.imageView5:// to แสดงแผนที่สนาม
                startActivity(new Intent(MainHub.this, MapsActivity.class));
                break;
            case R.id.imageView6:
                break;
            case R.id.imageView7:
                startActivity(new Intent(MainHub.this,DeveloperActivity.class));
                break;

        }// switch

    } //onClick

    private void confirmCountScore() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nonglukbad);
        builder.setTitle("นับคะแนน");
        builder.setMessage("กรุณาเลือกประเภทการแข่งขัน คู่ หรือ เดี่ยว");
        builder.setNegativeButton("คู่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainHub.this, FourPlayer.class));
                finish();
                dialog.dismiss();

            }
        });
        builder.setPositiveButton("เดี่ยว", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainHub.this,TwoPlayer.class));
                finish();
                dialog.dismiss();
            }
        });
        builder.show();

    }//confirm
}//main class
