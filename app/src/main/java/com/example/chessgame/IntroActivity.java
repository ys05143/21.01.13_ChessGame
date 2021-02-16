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
        ImageView rotate1 = (ImageView)findViewById(R.id.rook_anim) ;
        ImageView rotate2 = (ImageView)findViewById(R.id.knight_anim) ;
        ImageView rotate3 = (ImageView)findViewById(R.id.bishp_anim) ;
        ImageView rotate4 = (ImageView)findViewById(R.id.queen_anim) ;
        ImageView rotate5 = (ImageView)findViewById(R.id.king_anim) ;
        ImageView rotate6 = (ImageView)findViewById(R.id.pawn_anim) ;
        rotate1.startAnimation(animation);
        rotate2.startAnimation(animation);
        rotate3.startAnimation(animation);
        rotate4.startAnimation(animation);
        rotate5.startAnimation(animation);
        rotate6.startAnimation(animation);

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