package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FourPlayer extends AppCompatActivity {
    private EditText playerAEditText, playerBEditText,playerCEditText, playerDEditText;
    private Button playButton;
    private String playerAString, playerBString, playerCString,playerDString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player);
        //Bind Widget
        playerAEditText = (EditText) findViewById(R.id.editText4);
        playerBEditText = (EditText) findViewById(R.id.editText6);
        playerCEditText = (EditText) findViewById(R.id.editText5);
        playerDEditText = (EditText) findViewById(R.id.editText7);
        playButton = (Button) findViewById(R.id.button4);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerAString = playerAEditText.getText().toString().trim();
                playerBString = playerBEditText.getText().toString().trim();
                playerCString = playerCEditText.getText().toString().trim();
                playerDString = playerDEditText.getText().toString().trim();

                if (playerAString.equals("")) {
                    playerAString = "Player A";
                }
                if (playerBString.equals("")) {
                    playerBString = "Player B";
                }
                if (playerCString.equals("")) {
                    playerCString = "Player C";
                }
                if (playerDString.equals("")) {
                    playerDString = "Player D";
                }

                Log.d("kanV2", "A ==>" + playerAString);
                Log.d("kanV2", "A ==>" + playerBString);
                Log.d("kanV2", "A ==>" + playerCString);
                Log.d("kanV2", "A ==>" + playerDString);

                Intent intent = new Intent(FourPlayer.this, CheckScore2.class);
                intent.putExtra("A", playerAString);
                intent.putExtra("B", playerBString);
                intent.putExtra("C", playerCString);
                intent.putExtra("D", playerDString);
                startActivity(intent);
                finish();


            }//onclick
        });

    }//main Method
}//Main class
