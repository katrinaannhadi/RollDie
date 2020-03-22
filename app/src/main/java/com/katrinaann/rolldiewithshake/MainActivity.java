package com.katrinaann.rolldiewithshake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textDiceResult1;
    private TextView textDiceResult2;
    private TextView textTotal;
    public static final Random rng = new Random();
    private Button mRollDices;
    private ImageView imageViewDice, imageViewDice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRollDices = (Button) findViewById(R.id.button);
        imageViewDice = findViewById(R.id.iv_dice);
        imageViewDice2 = findViewById(R.id.iv_dice2);
        textDiceResult1 = findViewById(R.id.tv_result1);
        textDiceResult2 = findViewById(R.id.tv_result2);
        textTotal = findViewById(R.id.tv_total);

        mRollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int value1 = randomDiceValue();
                        int value2 = randomDiceValue();

                        int res1 = getResources().getIdentifier("dice_" + value1, "drawable", "com.katrinaann.rolldiewithshake");
                        int res2 = getResources().getIdentifier("dice_" + value2, "drawable", "com.katrinaann.rolldiewithshake");

                        textDiceResult1.setText(String.valueOf(value1));
                        textDiceResult2.setText(String.valueOf(value2));
                        textTotal.setText(String.valueOf(value1 + value2));

                        imageViewDice.setImageResource(res1);
                        imageViewDice2.setImageResource(res2);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);

                imageViewDice.startAnimation(anim1);
                imageViewDice2.startAnimation(anim2);
            }
        });
    }

    public static int randomDiceValue() {
        return rng.nextInt(6) + 1;
    }
}