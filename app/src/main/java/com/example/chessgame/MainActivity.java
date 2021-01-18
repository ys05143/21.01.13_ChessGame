package com.example.chessgame;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
//------------------------------------변수처리부분---------------------------------
    // imageview ID 처리
    int block_id[] = {R.id.A8, R.id.B8, R.id.C8, R.id.D8, R.id.E8, R.id.F8, R.id.G8, R.id.H8,
            R.id.A7, R.id.B7, R.id.C7, R.id.D7, R.id.E7, R.id.F7, R.id.G7, R.id.H7,
            R.id.A6, R.id.B6, R.id.C6, R.id.D6, R.id.E6, R.id.F6, R.id.G6, R.id.H6,
            R.id.A5, R.id.B5, R.id.C5, R.id.D5, R.id.E5, R.id.F5, R.id.G5, R.id.H5,
            R.id.A4, R.id.B4, R.id.C4, R.id.D4, R.id.E4, R.id.F4, R.id.G4, R.id.H4,
            R.id.A3, R.id.B3, R.id.C3, R.id.D3, R.id.E3, R.id.F3, R.id.G3, R.id.H3,
            R.id.A2, R.id.B2, R.id.C2, R.id.D2, R.id.E2, R.id.F2, R.id.G2, R.id.H2,
            R.id.A1, R.id.B1, R.id.C1, R.id.D1, R.id.E1, R.id.F1, R.id.G1, R.id.H1,
    };

    ImageView block[] = new ImageView[64];

    //   rook-1, knight-2, bishop-3, queen-4, king-5, pawn-6   *dot - 0
    int number[] = {1, 2, 3, 4, 5, 3, 2, 1,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            1, 2, 3, 4, 5, 3, 2, 1,
            6, 6, 6, 6, 6, 6, 6, 6};

    static int pawn1_count = 0;

    Drawable[] temp = new Drawable[1];// 버튼 클릭 시 이미지 임시 저장
    int[] temp_index = new int[1]; // 첫번째 버튼의 번호 저장(첫번째 누른 block의 인덱스)
    int[] choose_num = new int[1]; // 첫번째 버튼의 말의 종류 저장 (1~6)
    boolean[] flag = {false}; // 버튼 2번눌렀을때
//---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  ImageView - ID  match
        for (int i = 0; i < 64; i++)
            block[i] = (ImageView) findViewById(block_id[i]);

        // 0~23 3번째 줄까지 추가해놓음
        block[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(0);
            }
        });

        block[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(1);
            }
        });

        block[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(2);
            }
        });

        block[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(3);
            }
        });

        block[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(4);
            }
        });

        block[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(5);
            }
        });

        block[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(6);
            }
        });

        block[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(7);
            }
        });

        block[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(8);
            }
        });

        block[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(9);
            }
        });
        block[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(10);
            }
        });
        block[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(11);
            }
        });
        block[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(12);
            }
        });
        block[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(13);
            }
        });
        block[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(14);
            }
        });
        block[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(15);
            }
        });
        block[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(16);
            }
        });
        block[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(17);
            }
        });
        block[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(18);
            }
        });
        block[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(19);
            }
        });
        block[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(20);
            }
        });
        block[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(21);
            }
        });
        block[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(22);
            }
        });
        block[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(23);
            }
        });

    }


//-----------------------------MOVE() 부분 -----------------------------------------------
    public void move(int num){
        // 버튼 선택 1
        if(temp[0]==null) {
            if (!flag[0]) {
                temp[0] = block[num].getDrawable();
                temp_index[0] = num;
                flag[0] = true;
                // switch 부문( 말의 종류에따라서 )
                switch (number[temp_index[0]]) {
                    case 1: {
                        choose_num[0] = 1;
                        rook(num);
                        break;
                    }
                    case 2: {
                        choose_num[0] = 2;
                        knight(num);
                        break;
                    }
                    case 3: {
                        choose_num[0] = 3;
                        bishop(num);
                        break;
                    }
                    case 4: {
                        choose_num[0] = 4;
                        queen(num);
                        break;
                    }
                    case 5: {
                        choose_num[0] = 5;
                        king(num);
                        break;
                    }
                    case 6: {
                        choose_num[0] = 6;
                        pawn(num);
                        break;
                    }
                }

            } else {
                for (int i = 0; i < 64; i++)
                    if (number[i] == 0) block[i].setVisibility(View.INVISIBLE);
                temp[0] = null;
                flag[0] = false;
            }
        }
        //버튼 선택 2  (temp[0]!=null)
        else {
            number[temp_index[0]]= 0 ;
            number[num] = choose_num[0] ; // number[0] fix
            block[temp_index[0]].setImageDrawable(block[num].getDrawable()); // block[0] fix
            block[num].setImageDrawable(temp[0]); // block[0] fix
            // clear
            temp[0]=null;
            flag[0]=false;
            for(int i=0;i<64;i++) if(number[i]==0) block[i].setVisibility(View.INVISIBLE);
        }
    }
//-----------------------------------------------------------------------------------------


/////////////각 말들 움직임

    public void rook(int spot) { //rook의 이동
        for(int a=spot+8;a<64;a=a+8) {//하
            if(number[a]!=0) break;
            block[a].setVisibility(View.VISIBLE);
        }
        for(int b=spot-8;b>=0;b=b-8) {//상
            if(number[b]!=0) break;
            block[b].setVisibility(View.VISIBLE);
        }
        for(int c=0;c<spot%8;c++){//좌
            if(number[c]!=0) break;
            block[spot-(c+1)].setVisibility(View.VISIBLE);
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우
            if(number[d]!=0) break;
            block[spot-(d+1)].setVisibility(View.VISIBLE);
        }

    }
    public void pawn(int spot) { //pawn의 이동
        if(pawn1_count<1) {
            for (int a = spot+8; a < 32; a = a+ 8) {
                if (number[a] != 0) break;
                block[a].setVisibility(View.VISIBLE);
            }
            pawn1_count++;
        }
        else{
            for (int a = spot+8; a < 24; a = a + 8) {
                if (number[a] != 0) break;
                block[a].setVisibility(View.VISIBLE);
            }

        }
    }
    public void bishop(int spot){ //bishop 의 이동
        for(int a=0;a<8-((spot%8)+1);a++){//우 대각 아래

            if(spot+(9*(a+1))>=0&&spot+(9*(a+1))<=63)
            { if (number[spot+(9*(a+1))] != 0) break;
                block[spot+(9*(a+1))].setVisibility(View.VISIBLE);}
        }
        for(int b=0;b<spot%8;b++){ //좌 대각 위

            if(spot-(9*(b+1))>=0&&spot-(9*(b+1))<=63)
            {  if (number[spot-(9*(b+1))] != 0) break;
                block[spot-(9*(b+1))].setVisibility(View.VISIBLE);}
        }
        for(int c=0;c<spot%8;c++){ //좌 대각 아래

            if(spot+(7*(c+1))>=0&&spot+(7*(c+1))<=63)
            {if(number[spot+(7*(c+1))] != 0) break;
                block[spot+(7*(c+1))].setVisibility(View.VISIBLE);}
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우 대각 아래

            if(spot-(7*(d+1))>=0&&spot-(7*(d+1))<=63)
            {if (number[spot-(7*(d+1))] != 0) break;
                block[spot-(7*(d+1))].setVisibility(View.VISIBLE);}
        }

    }
    public void knight(int spot){
        if((spot+(8*2)+1<=63&&spot+(8*2)+1>=0)&&(number[spot+(8*2)+1]==0))//우 대각 아래
            block[spot+(8*2)+1].setVisibility(View.VISIBLE);
        if((spot+(8*2)-1<=63&&spot+(8*2)-1>=0)&&(number[spot+(8*2)-1]==0))//좌 대각 아래
            block[spot+(8*2)-1].setVisibility(View.VISIBLE);
        if((spot-(8*2)+1<=63&&spot-(8*2)+1>=0)&&(number[spot-(8*2)+1]==0))//우 대각 위
            block[spot-(8*2)+1].setVisibility(View.VISIBLE);
        if((spot-(8*2)-1<=63&&spot-(8*2)-1>=0)&&(number[spot-(8*2)-1]==0))//좌 대각 위
            block[spot-(8*2)-1].setVisibility(View.VISIBLE);
    }
    public void king(int spot){
        if((spot+1<=63&&spot+1>=0)&&(number[spot+1]==0))//우
            block[spot+1].setVisibility(View.VISIBLE);
        if((spot-1<=63&&spot-1>=0)&&(number[spot-1]==0))//좌
            block[spot+1].setVisibility(View.VISIBLE);
        if((spot+7<=63&&spot+7>=0)&&(number[spot+7]==0))//좌대각 아래
            block[spot+7].setVisibility(View.VISIBLE);
        if((spot+8<=63&&spot+8>=0)&&(number[spot+8]==0))//아래
            block[spot+8].setVisibility(View.VISIBLE);
        if((spot+9<=63&&spot+9>=0)&&(number[spot+9]==0))//우대각 아래
            block[spot+9].setVisibility(View.VISIBLE);
        if((spot-7<=63&&spot-7>=0)&&(number[spot-7]==0))//우대각 위
            block[spot-7].setVisibility(View.VISIBLE);
        if((spot-8<=63&&spot-8>=0)&&(number[spot-8]==0))//위
            block[spot-8].setVisibility(View.VISIBLE);
        if((spot-9<=63&&spot-9>=0)&&(number[spot-9]==0))//좌대각위
            block[spot-9].setVisibility(View.VISIBLE);

    }
    public void queen(int spot){

        for(int a=spot+8;a<64;a=a+8) {//하
            if(number[a]!=0) break;
            block[a].setVisibility(View.VISIBLE);
        }
        for(int b=spot-8;b>=0;b=b-8) {//상
            if(number[b]!=0) break;
            block[b].setVisibility(View.VISIBLE);
        }
        for(int c=0;c<spot%8;c++){//좌
            if(number[c]!=0) break;
            block[spot-(c+1)].setVisibility(View.VISIBLE);
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우
            if(number[d]!=0) break;
            block[spot-(d+1)].setVisibility(View.VISIBLE);
        }
        for(int a=0;a<8-((spot%8)+1);a++){//우 대각 아래

            if(spot+(9*(a+1))>=0&&spot+(9*(a+1))<=63)
            { if (number[spot+(9*(a+1))] != 0) break;
                block[spot+(9*(a+1))].setVisibility(View.VISIBLE);}
        }
        for(int b=0;b<spot%8;b++){ //좌 대각 위

            if(spot-(9*(b+1))>=0&&spot-(9*(b+1))<=63)
            {  if (number[spot-(9*(b+1))] != 0) break;
                block[spot-(9*(b+1))].setVisibility(View.VISIBLE);}
        }
        for(int c=0;c<spot%8;c++){ //좌 대각 아래

            if(spot+(7*(c+1))>=0&&spot+(7*(c+1))<=63)
            {if(number[spot+(7*(c+1))] != 0) break;
                block[spot+(7*(c+1))].setVisibility(View.VISIBLE);}
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우 대각 아래

            if(spot-(7*(d+1))>=0&&spot-(7*(d+1))<=63)
            {if (number[spot-(7*(d+1))] != 0) break;
                block[spot-(7*(d+1))].setVisibility(View.VISIBLE);}
        }

    }

}


