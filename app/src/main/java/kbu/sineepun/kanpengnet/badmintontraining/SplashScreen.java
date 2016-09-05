package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    //Explicit
    private ImageView imageView;
    private TextView team1TextView, team2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //bind widget
        imageView = (ImageView) findViewById(R.id.imageView);
        team1TextView = (TextView) findViewById(R.id.textView2);
        team2TextView = (TextView) findViewById(R.id.textView3);
        //show view
        MyConfig myConfig = new MyConfig();
        imageView.setImageResource(myConfig.getLogoAnInt());
        team1TextView.setText(myConfig.getTeam1String());
        team2TextView.setText(myConfig.getTeam2String());

        //Auto Intent
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainHub.class));
                finish();
            }
        }, 5000);

        //Sound Effect
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.load);
    mediaPlayer.start();


    }//main Method
}//main Class
