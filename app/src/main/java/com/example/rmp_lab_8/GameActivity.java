package com.example.rmp_lab_8;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private TextView textResult;
    private TextView textTimer;
    private CountDownTimer countDownTimer;
    private int score = 0;
    private int activeButtonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textResult = findViewById(R.id.textResult);
        textTimer = findViewById(R.id.textTimer);

        // Инициализация кнопок
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);

        // Установка обработчика нажатия на кнопки
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick((Button) view);
                }
            });
        }

        startGame();
    }

    private void startGame() {
        score = 0;
        textResult.setText("Score: " + score);

        // Создание и запуск таймера
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Обновляем отображение оставшегося времени
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                textTimer.setText("Time: " + secondsRemaining + "s");

                // Генерируем случайный индекс активной кнопки
                activeButtonIndex = new Random().nextInt(buttons.length);

                // Снимаем подсветку со всех кнопок
                for (Button button : buttons) {
                    button.setBackgroundColor(Color.WHITE);
                }

                // Подсвечиваем активную кнопку
                buttons[activeButtonIndex].setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onFinish() {
                textResult.setText("Game Over! Score: " + score);
            }
        }.start();
    }

    public void onButtonClick(Button clickedButton) {
        if (buttons[activeButtonIndex] == clickedButton) {
            score++;
            textResult.setText("Score: " + score);
        } else {
            score--;
            textResult.setText("Score: " + score);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}


