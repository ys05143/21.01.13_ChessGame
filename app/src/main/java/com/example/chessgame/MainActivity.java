package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int from_x; static int from_y; static int to_x; static int to_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iconListener1 i1=new iconListener1(); //listener 객체 생성
        iconListener2 i2=new iconListener2();

        Button a1_b=(Button)findViewById(R.id.a1_b);
        a1_b.setOnClickListener(i1); //위젯에 등록
        Button a2_b=(Button)findViewById(R.id.a2_b);
        a2_b.setOnClickListener(i2);

    }

    public void onButtonClicked(View v){//click 하면 toast 메세지 띄우는 함수
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    class iconListener1 implements View.OnClickListener{ //listener 클래스 정의
        public void onClick(View v){
            int [] blocation=new int[2];
            v.getLocationOnScreen(blocation);
            from_x=blocation[0]; from_y=blocation[1];
            String s=Integer.toString(blocation[1]);
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

        }
    }
    class iconListener2 implements View.OnClickListener{ //listener 클래스 정의
        public void onClick(View v){
            int [] blocation=new int[2];
            v.getLocationOnScreen(blocation);
            to_x=blocation[0]; to_y=blocation[1];
            String s=Integer.toString(blocation[1]);
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

        }
    }


}

