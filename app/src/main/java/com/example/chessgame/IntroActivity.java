package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        ImageView Brotate1 = (ImageView)findViewById(R.id.rook_anim) ;
        Brotate1.startAnimation(animation);

        ImageView Wrotate1 = (ImageView)findViewById(R.id.rook_anim2) ;
        Wrotate1.startAnimation(animation);

        ImageView Protate1 = (ImageView)findViewById(R.id.rook_anim3) ;
        Protate1.startAnimation(animation);

        ImageView Protate2 = (ImageView)findViewById(R.id.rook_anim4) ;
        Protate2.startAnimation(animation);

        Button b1=(Button)findViewById(R.id.oneP_Game_w);
        Button b2=(Button)findViewById(R.id.oneP_Game_b);
        Button b3=(Button)findViewById(R.id.twoP_Game);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(IntroActivity.this, Ai_b_Activity.class);
               startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroActivity.this, Ai_w_Activity.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}