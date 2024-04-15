package com.example.rmp_lab_8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame;
    private SeekBar seekBarDuration, seekBarActiveDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = findViewById(R.id.btnStartGame);
        seekBarDuration = findViewById(R.id.seekBarDuration);
        seekBarActiveDuration = findViewById(R.id.seekBarActiveDuration);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = seekBarDuration.getProgress() * 1000; // Продолжительность игры в миллисекундах
                int activeDuration = seekBarActiveDuration.getProgress(); // Продолжительность активности кнопки в миллисекундах

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("duration", duration);
                intent.putExtra("activeDuration", activeDuration);
                startActivity(intent);
            }
        });
    }
}
