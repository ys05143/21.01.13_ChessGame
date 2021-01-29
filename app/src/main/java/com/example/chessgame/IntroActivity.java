package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button b1=(Button)findViewById(R.id.twoP_Game);
        Button b2=(Button)findViewById(R.id.oneP_Game);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(IntroActivity.this, MainActivity.class);
               startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroActivity.this, Ai_Activity.class);
                startActivity(intent);
            }
        });
    }
}