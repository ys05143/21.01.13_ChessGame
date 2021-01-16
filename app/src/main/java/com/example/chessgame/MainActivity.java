package com.example.chessgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.a8);
        Button next_btn = (Button) findViewById(R.id.a7);
        final int[] first_btn = new int[1];
        first_btn[0] =0 ;
        final int[] second_btn = {0};
        final Drawable[] temp_img = new Drawable[1];


        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                first_btn[0] = btn.getId() ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    temp_img[0] =  btn.getForeground();
                }


            }
        });
        next_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (first_btn[0] != 0) {
                    second_btn[0] = next_btn.getId();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        next_btn.setForeground(temp_img[0]);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        btn.setForeground(null);
                    }
                }
            }
        });
    }

}


