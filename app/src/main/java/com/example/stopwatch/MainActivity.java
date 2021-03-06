package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean running = false;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    public void onclickStart(View view) {
        running = true;
    }

    public void onclickStop(View view) {
        running = false;
    }

    public void onclickReset(View view) {
        running = true;
        seconds = 0;
    }

    private void runTimer() {


        final TextView timeview = (TextView) findViewById(R.id.Time);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = seconds / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

}