package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonEntrar = findViewById(R.id.buttonEntrar);
        Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse_animation);
        buttonEntrar.startAnimation(pulseAnimation);
        buttonEntrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TelaSelecaoActivity.class);
            startActivity(intent);
        });
    }
}