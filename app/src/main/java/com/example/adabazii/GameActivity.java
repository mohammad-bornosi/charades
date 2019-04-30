package com.example.adabazii;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adabazii.db.AdaBaziDataSource;


public class GameActivity extends Activity {
    int minutes = 0 ;
    int seconds = 5;

    TextView timerTextView;
    ProgressBar progressBar;
    Button RightButton;
    Button WrongButton;

    public int score;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            progressBar.setProgress((minutes * 60 + seconds) * 20);

            if(minutes == 0 && seconds == 0) {
                RightButton.getLayoutParams().width = 350;
                WrongButton.getLayoutParams().width = 350;
                WrongButton.setVisibility(View.VISIBLE);
            } else {
                minutes = seconds == 0 ? minutes - 1 : minutes;
                seconds = seconds == 0 ? 59 : seconds -1;
;

                timerHandler.postDelayed(this, 1000);

            }
         }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_layout);

        AdaBaziDataSource adaBaziDataSource = new AdaBaziDataSource(GameActivity.this);
        adaBaziDataSource.open();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.score = bundle.getInt("score");

        Cursor cursor = adaBaziDataSource.select(bundle.getString("category"), this.score);

        cursor.moveToFirst();
        TextView wordTextView = findViewById(R.id.wordtxt);

        wordTextView.setText(cursor.getString(cursor.getColumnIndex("WORD")));


        timerTextView = findViewById(R.id.timer);

        progressBar = findViewById(R.id.progressBar);



        RightButton = findViewById(R.id.rightButton);
        WrongButton = findViewById(R.id.wrongButton);


        WrongButton.setVisibility(View.GONE);

        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));

        timerHandler.postDelayed(timerRunnable, 0);



    }

    public void goToGroupsWrong(View view) {

        Intent returnIntent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putInt("score", 0);
        returnIntent.putExtras(bundle);

        setResult(Activity.RESULT_OK,returnIntent);

        finish();
    }

    public void goToGroupsRight(View view) {
        Intent returnIntent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putInt("score", this.score);

        returnIntent.putExtras(bundle);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
