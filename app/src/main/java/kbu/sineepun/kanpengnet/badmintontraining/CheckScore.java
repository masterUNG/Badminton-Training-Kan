package kbu.sineepun.kanpengnet.badmintontraining;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckScore extends AppCompatActivity {


    //explicit
    private ImageView addScoreAImagerView, addScoreBImageView,
            undoImageView, clearImageView;
    private TextView playerATextView, playerBTextView,
            scoreAtextView, scoreBtextView;

    private String playerAString, playerBString;
    private int scoreAnInt = 0, scoreBAnInt = 0;
    private boolean statusABoolean = true;//true สภาวะปกติ false สภาวะดิว
    private ArrayList<String> stringArrayList = new ArrayList<String>();
    private String[] undoStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_score);

        //Bind Widget
        playerATextView = (TextView) findViewById(R.id.textView20);
        playerBTextView = (TextView) findViewById(R.id.textView21);
        scoreAtextView = (TextView) findViewById(R.id.textView18);
        scoreBtextView = (TextView) findViewById(R.id.textView19);
        addScoreAImagerView = (ImageView) findViewById(R.id.imageView17);
        addScoreBImageView = (ImageView) findViewById(R.id.imageView18);
        undoImageView = (ImageView) findViewById(R.id.imageView14);
        clearImageView = (ImageView) findViewById(R.id.imageView15);

        //Show Player
        playerAString = getIntent().getStringExtra("A");
        playerBString = getIntent().getStringExtra("B");
        playerATextView.setText(playerAString);
        playerBTextView.setText(playerBString);

        //Click UndoScore
        undoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check Log
                Log.d("5SepV2", "Last stringArrayList(" +
                        (stringArrayList.size() - 1) + ") ==> " +
                        stringArrayList.get(stringArrayList.size() - 1));

                switch (Integer.parseInt(stringArrayList.get(stringArrayList.size()-1))) {
                    case 0: // Decrease Score A
                        scoreAnInt -= 2;
                        changScore(scoreAtextView, 0);
                        break;
                    case 1: // Decrease Score B
                        scoreBAnInt -= 2;
                        changScore(scoreBtextView, 1);
                        break;
                }   // switch

                stringArrayList.remove(stringArrayList.size() - 1);

            }   // onClick
        });

        //Click ClearScore
        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scoreAnInt = -1;
                scoreBAnInt = -1;

                changScore(scoreAtextView, 0);
                changScore(scoreBtextView, 1);
                soundEffect();

            }   // onClick
        });


        //Click ADD scoreA
        addScoreAImagerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changScore(scoreAtextView, 0);
                soundEffect();
                addMyArrayList("0");
            } //onClick
        });

        //Click Add scoreB
        addScoreBImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changScore(scoreBtextView, 1);
                soundEffect();
                addMyArrayList("1");

            }//onclick
        });

    }//main method

    private void addMyArrayList(String strIndex) {

        stringArrayList.add(strIndex);
        Log.d("5SepV1", "Count of ArrayList ==> " + stringArrayList.size());

        undoStrings = new String[stringArrayList.size()];
        for (int i=0;i<stringArrayList.size();i++) {

            undoStrings[i] = stringArrayList.get(i);
            Log.d("5SepV1", "undoStrings(" + i + ") = " + undoStrings[i]);

        }   //for

    }   // addMyArrayList

    private void soundEffect() {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.button);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });


    }

    private void changScore(TextView scoreShow, int index) {
        int intShowScore = 0;
        switch (index) {
            case 0: // for A
                scoreAnInt += 1;
                intShowScore = scoreAnInt;
                break;
            case 1: // for B
                scoreBAnInt += 1;
                intShowScore = scoreBAnInt;
        }
        scoreShow.setText(Integer.toString(intShowScore));

        //Check Status
        if ((scoreAnInt == 20) && (scoreBAnInt == 20)) {
            statusABoolean = false;//มีการตดิวเกิดขึ้น
        }

        //check Score
        if (statusABoolean) {
            // ปกติ

            if (scoreAnInt == 21) {
                alertScore(playerAString, scoreAnInt, scoreBAnInt);

            } else if (scoreBAnInt == 21) {
                alertScore(playerBString, scoreBAnInt, scoreAnInt);
            }

        } else {
            //ดิว
            if ((scoreAnInt - scoreBAnInt) >= 2) {
                alertScore(playerAString, scoreAnInt, scoreBAnInt);
            } else if ((scoreBAnInt - scoreAnInt) >= 2) {
                alertScore(playerBString, scoreBAnInt, scoreBAnInt);
            } else if (scoreAnInt == 30) {
                alertScore(playerAString, scoreAnInt, scoreBAnInt);
            } else if (scoreBAnInt == 30) {
                alertScore(playerBString, scoreBAnInt, scoreBAnInt);
            }

        }//if

    }//checkScore

    private void alertScore(String strwin, int intScoreWin, int intScoreLost) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nonglukbad);
        builder.setTitle("ยินดีในชัยชนะ คุณ " + strwin);
        builder.setMessage("คะแนนของคุณ " +
                Integer.toString(intScoreWin) +
                " : " +
                Integer.toString(intScoreLost));
        builder.setNegativeButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetAll();
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }// alertScore

    private void resetAll() {

        scoreAnInt = 0;
        scoreBAnInt = 0;
        scoreAtextView.setText("0");
        scoreBtextView.setText("0");
        statusABoolean = true;
    }


}//main class
