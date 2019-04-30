package com.example.adabazii;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adabazii.db.AdaBaziDataSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> words;
    private SeekBar roundSeekBar;
    private TextView roundtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_layout);


        roundSeekBar = findViewById(R.id.seekBar);
        roundtxt = findViewById(R.id.round);

        roundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                roundtxt.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void  goToGroups(View view) {
        roundSeekBar = findViewById(R.id.seekBar);
        System.out.println("progress" + roundSeekBar.getProgress());

        if(roundSeekBar.getProgress() == 0) {

            Toast.makeText(getApplicationContext(),"تعداد دور ها را انتخاب کنید", Toast.LENGTH_LONG).show();
            view.setClickable(false);
            return;
        }

        Intent intent = new Intent(MainActivity.this, GroupActivity.class);

        Bundle bundle = new Bundle();

        Button button = (Button) view;

        bundle.putInt("groupNumber", Integer.valueOf(button.getText().toString()));
        bundle.putInt("rounds", Integer.valueOf(roundtxt.getText().toString()));

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
