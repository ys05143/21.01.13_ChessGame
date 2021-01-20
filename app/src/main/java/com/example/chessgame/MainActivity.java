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

    //   rook_b-1, knight_b-2, bishop_b-3, queen_b-4, king_b-5, pawn_b-6, first_pawn_b-7  *dot - 0
    //   rook_w-11, knight_w=12, bishop-13, queen_w-14, king_w-15, pawn_w-16, first_pawn_w-17
    int number[] = {1,  2,  3,  4,  5,  3,  2,  1,
                    7,  7,  7,  7,  7,  7,  7,  7,
                    0,  0,  0,  0,  0,  0,  0,  0,
                    0,  0,  0,  0,  0,  0,  0,  0,
                    0,  0,  0,  0,  0,  0,  0,  0,
                    0,  0,  0,  0,  0,  0,  0,  0,
                   17, 17, 17, 17, 17, 17, 17, 17,
                   11, 12, 13, 14, 15, 13, 12, 11 };


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
        block[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(24);
            }
        });
        block[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(25);
            }
        });
        block[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(26);
            }
        });
        block[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(27);
            }
        });
        block[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(28);
            }
        });
        block[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(29);
            }
        });
        block[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(30);
            }
        });
        block[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(31);
            }
        });
        block[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(32);
            }
        });
        block[33].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(33);
            }
        });
        block[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(34);
            }
        });
        block[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(35);
            }
        });
        block[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(36);
            }
        });
        block[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(37);
            }
        });
        block[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(38);
            }
        });
        block[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(39);
            }
        });
        block[40].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(40);
            }
        });
        block[41].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(41);
            }
        });
        block[42].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(42);
            }
        });
        block[43].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(43);
            }
        });
        block[44].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(44);
            }
        });
        block[45].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(45);
            }
        });
        block[46].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(46);
            }
        });
        block[46].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(46);
            }
        });
        block[47].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(47);
            }
        });
        block[48].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(48);
            }
        });
        block[49].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(49);
            }
        });
        block[50].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(50);
            }
        });
        block[51].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(51);
            }
        });
        block[52].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(52);
            }
        });
        block[53].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(53);
            }
        });
        block[54].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(54);
            }
        });
        block[55].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(55);
            }
        });
        block[56].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(56);
            }
        });
        block[57].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(57);
            }
        });
        block[58].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(58);
            }
        });
        block[59].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(59);
            }
        });
        block[60].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(60);
            }
        });
        block[61].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(61);
            }
        });
        block[62].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(62);
            }
        });
        block[63].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(63);
            }
        });

    }


//-----------------------------MOVE() 부분 -----------------------------------------------
    public void move(int num){
        // 버튼 선택 1
        if(temp[0]==null && number[num]!=0) {
            if (!flag[0]) {// 한번 눌렀을 때
                temp[0] = block[num].getDrawable();
                temp_index[0] = num;
                flag[0] = true;
                // switch 부문( 말의 종류에따라서 )
                switch (number[temp_index[0]]) {
                    case 1: {
                        choose_num[0] = 1;
                        rook(num,true);
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
                    case 6: { //일반 pawn
                        choose_num[0] = 6;
                        pawn(num, true);
                        break;
                    }
                    case 7:{ //처음 움직이는 pawn
                        choose_num[0]=6;
                        f_pawn(num, true);
                        break;
                    }
                    case 11:{ //rook_w
                        choose_num[0]=11;
                        rook(num, false);
                        break;
                    }
                    case 12:{
                        choose_num[0]=12;
                        knight(num);
                        break;
                    }
                    case 13:{
                        choose_num[0] = 13;
                        bishop(num);
                        break;
                    }
                    case 14:{
                        choose_num[0] = 14;
                        queen(num);
                        break;
                    }
                    case 15:{
                        choose_num[0] = 15;
                        king(num);
                        break;
                    }
                    case 16:{
                        choose_num[0] = 16;
                        pawn(num, false);
                        break;
                    }
                    case 17:{
                        choose_num[0]= 16;
                        f_pawn(num, false);
                        break;
                    }


                }

            }
        }
        //버튼 선택 2  (temp[0]!=null) : 이미지가 하나라도 복사되었을 때
        else {
            // 두번 눌렀을때 = 선택취소
            if(num==temp_index[0]) {
            }
            // dot
            else if(number[num]==0) {
                number[temp_index[0]] = 0;
                number[num] = choose_num[0]; // number[0] fix
                block[temp_index[0]].setImageDrawable(block[num].getDrawable()); // 첫번째 선택한 버튼 자리에 두번째 선택한 버튼 이미지 삽입
                block[num].setImageDrawable(temp[0]); // 두번째 선택한 버튼 자리에 첫번째 선택에서 저장해둔 이미지 삽입
            }
            // 다른 말 선택
            else  {
                number[temp_index[0]]=0;
                number[num] = choose_num[0] ;
                block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                block[num].setImageDrawable(temp[0]); // 두번째 선택한 버튼 자리에 첫번째 선택에서 저장해둔 이미지 삽입
            }
            //공통
            clear() ;
            for(int i=0;i<64;i++) {
                if(number[i]==0) block[i].setVisibility(View.INVISIBLE);
                if(number[i]!=0) block[i].setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        }
    }
//-------------------------------clear()-----------------------------------
    void clear() {
        temp_index[0] = -1 ;
        flag[0] = false ;
        temp[0] = null ;
    }
    boolean in_board(int index) {
        if(index>=0&&index<64) return true;
        else return false ;
    }
    boolean black(int index) {
        if(number[index]>=1&&number[index]<=7) return true;
        else return false ;
    }
    boolean white(int index) {
        if(number[index]>=11&&number[index]<=17) return true;
        else return false ;
    }
    void red(int index) {
        block[index].setBackgroundColor(getResources().getColor(R.color.red));
    }
/////////////각 말들 움직임----------------------------------------------------------------

    public void rook(int spot, boolean bw) {//rook의 이동함수 완료, bw=true:rook_b/bw=false:rook_w
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if(bw== true && white(a))
                        red(a);
                    if(bw==false && black(a))
                        red(a);
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            for (int b = spot - 8; b >= 0; b = b - 8) {//상
                if (in_board(b)) {
                    if(bw==true&& white(b))
                        red(b);
                    if(bw==false && black(b))
                        red(b);
                    if (number[b] != 0) break;
                    block[b].setVisibility(View.VISIBLE);
                }
            }
            for (int c = 0; c < spot % 8; c++) {//좌
                if (in_board(spot - (c + 1))) {
                    if(bw==true && white(spot - (c + 1)))
                        red(spot-(c+1));
                    if(bw==false && black(spot - (c + 1)))
                        red(spot-(c+1));
                    if (number[spot - (c + 1)] != 0) break;
                    block[spot - (c + 1)].setVisibility(View.VISIBLE);
                }
            }
            for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우
                if (in_board(spot + (d + 1))) {
                    if(bw==true && white(spot + (d + 1)))
                        red(spot+(d+1));
                    if(bw==false&& black(spot + (d + 1)))
                        red(spot+(d+1));
                    if (number[spot + (d + 1)] != 0) break;
                    block[spot + (d + 1)].setVisibility(View.VISIBLE);
                }
            }

    }

    public void f_pawn(int spot, boolean bw) { //처음 움직이는 pawn의 이동함수 완료
        if (bw == true) { //pawn_b
            for (int a = spot + 8; a <= spot + 16; a = a + 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if(in_board(spot+9)&&white(spot+9)) //우 대각 아래에 상대방 말이 있을 때
                {if(8-spot%8-1>=1)
                    red(spot+9);}
            if(in_board(spot+7)&&white(spot+7)) //좌 대각 아래에 상대방 말이 있을 때
                {if(spot%8>=1)
                    red(spot+7);}
        }
        if(bw==false){ //pawn_W
            for (int a = spot - 8; a >= spot - 16; a = a - 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if(in_board(spot-9)&&black(spot-9)) //좌 대각 위에 상대방 말이 있을 때
                {if(spot%8>=1)
                    red(spot-9);}
            if(in_board(spot-7)&&black(spot-7)) //우 대각 위에 상대방 말이 있을 때
                {if(8-spot%8-1>=1)
                    red(spot-7);}
        }

    }
    public void pawn(int spot,boolean bw) { //일반 pawn의 이동함수 완료
        if (bw == true) {
            for (int a = spot + 8; a <= spot + 8; a = a + 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if(in_board(spot+9)&&white(spot+9)) //우 대각 아래에 상대방 말이 있을 때
            {if(8-spot%8-1>=1)
                red(spot+9);}
            if(in_board(spot+7)&&white(spot+7)) //좌 대각 아래에 상대방 말이 있을 때
            {if(spot%8>=1)
                red(spot+7);}
        }
        if(bw==false){
            for (int a = spot - 8; a >= spot - 8; a = a - 8) {
                if (a >= 0 && a <= 63) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if(in_board(spot-9)&&black(spot-9)) //좌 대각 위에 상대방 말이 있을 때
            {if(spot%8>=1)
                red(spot-9);}
            if(in_board(spot-7)&&black(spot-7)) //우 대각 위에 상대방 말이 있을 때
            {if(8-spot%8-1>=1)
                red(spot-7);}
        }
    }

    public void bishop(int spot){ //bishop 의 이동함수 완료
        for(int a=0;a<8-((spot%8)+1);a++){//우 대각 아래
            if(spot+(9*(a+1))>=0&&spot+(9*(a+1))<=63)
            {if (number[spot+(9*(a+1))] != 0) break;
                block[spot+(9*(a+1))].setVisibility(View.VISIBLE);}
        }
        for(int b=0;b<spot%8;b++){ //좌 대각 위
            if(spot-(9*(b+1))>=0&&spot-(9*(b+1))<=63)
            {if (number[spot-(9*(b+1))] != 0) break;
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

    public void knight(int spot){ //knight 이동함수 완료
        if((spot+(8*2)+1<=63&&spot+(8*2)+1>=0)&&(number[spot+(8*2)+1]==0))//우 대각 아래(2)
        {if(8-spot%8-1>=1) //끝쪽에 있을때 넘어가기 방지
            block[spot+(8*2)+1].setVisibility(View.VISIBLE);}
        if((spot+(8*2)-1<=63&&spot+(8*2)-1>=0)&&(number[spot+(8*2)-1]==0))//좌 대각 아래(2)
        {if(spot%8>=1)
            block[spot+(8*2)-1].setVisibility(View.VISIBLE);}
        if((spot-(8*2)+1<=63&&spot-(8*2)+1>=0)&&(number[spot-(8*2)+1]==0))//우 대각 위(2)
        {if(8-spot%8-1>=1)
            block[spot-(8*2)+1].setVisibility(View.VISIBLE);}
        if((spot-(8*2)-1<=63&&spot-(8*2)-1>=0)&&(number[spot-(8*2)-1]==0))//좌 대각 위(2)
        {if(spot%8>=1)
            block[spot-(8*2)-1].setVisibility(View.VISIBLE);}
        if((spot+(8*1)+2<=63&&spot+(8*1)+2>=0)&&(number[spot+(8*1)+2]==0))//우 대각 아래(1)
        {if(8-spot%8-1>=2)
            block[spot+(8*1)+2].setVisibility(View.VISIBLE);}
        if((spot+(8*1)-2<=63&&spot+(8*1)-2>=0)&&(number[spot+(8*1)-2]==0))//좌 대각 아래(1)
        {if(spot%8>=2)
            block[spot+(8*1)-2].setVisibility(View.VISIBLE);}
        if((spot-(8*1)+2<=63&&spot-(8*1)+2>=0)&&(number[spot-(8*1)+2]==0))//우 대각 위(1)
        {if(8-spot%8-1>=2)
            block[spot-(8*1)+2].setVisibility(View.VISIBLE);}
        if((spot-(8*1)-2<=63&&spot-(8*1)-2>=0)&&(number[spot-(8*1)-2]==0))//좌 대각 위(1)
        {if(spot%8>=2)
            block[spot-(8*1)-2].setVisibility(View.VISIBLE);}
    }

    public void king(int spot){ //king 이동함수 완료
        if((spot+1<=63&&spot+1>=0)&&(number[spot+1]==0))//우
        {if(8-spot%8-1>=1)
            block[spot+1].setVisibility(View.VISIBLE);}
        if((spot-1<=63&&spot-1>=0)&&(number[spot-1]==0))//좌
        {if(spot%8>=1)
            block[spot-1].setVisibility(View.VISIBLE);}
        if((spot+7<=63&&spot+7>=0)&&(number[spot+7]==0))//좌대각 아래
        {if(spot%8>=1)
            block[spot+7].setVisibility(View.VISIBLE);}
        if((spot+8<=63&&spot+8>=0)&&(number[spot+8]==0))//아래
            block[spot+8].setVisibility(View.VISIBLE);
        if((spot+9<=63&&spot+9>=0)&&(number[spot+9]==0))//우대각 아래
        {if(8-spot%8-1>=1)
            block[spot+9].setVisibility(View.VISIBLE);}
        if((spot-7<=63&&spot-7>=0)&&(number[spot-7]==0))//우대각 위
        {if(8-spot%8-1>=1)
            block[spot-7].setVisibility(View.VISIBLE);}
        if((spot-8<=63&&spot-8>=0)&&(number[spot-8]==0))//위
            block[spot-8].setVisibility(View.VISIBLE);
        if((spot-9<=63&&spot-9>=0)&&(number[spot-9]==0))//좌대각위
        {if(spot%8>=1)
            block[spot-9].setVisibility(View.VISIBLE);}

    }
    public void queen(int spot){ //queen 이동함수 완료(rook+bishop)

        for(int a=spot+8;a<64;a=a+8) {//하
            if(a>=0&&a<=63)
            {if(number[a]!=0) break;
                block[a].setVisibility(View.VISIBLE);}
        }
        for(int b=spot-8;b>=0;b=b-8) {//상
            if(b>=0&&b<=63)
            {if(number[b]!=0) break;
                block[b].setVisibility(View.VISIBLE);}
        }
        for(int c=0;c<spot%8;c++){//좌
            if(spot-(c+1)>=0&&spot-(c+1)<=63)
            {if(number[spot-(c+1)]!=0) break;
                block[spot-(c+1)].setVisibility(View.VISIBLE);}
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우
            if(spot+(d+1)>=0&&spot+(d+1)<=63)
            {if(number[spot+(d+1)]!=0) break;
                block[spot+(d+1)].setVisibility(View.VISIBLE);}
        }
        for(int a=0;a<8-((spot%8)+1);a++){//우 대각 아래
            if(spot+(9*(a+1))>=0&&spot+(9*(a+1))<=63)
            {if (number[spot+(9*(a+1))] != 0) break;
                block[spot+(9*(a+1))].setVisibility(View.VISIBLE);}
        }
        for(int b=0;b<spot%8;b++){ //좌 대각 위
            if(spot-(9*(b+1))>=0&&spot-(9*(b+1))<=63)
            {if (number[spot-(9*(b+1))] != 0) break;
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


