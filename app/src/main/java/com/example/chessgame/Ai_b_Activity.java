package com.example.chessgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class Ai_b_Activity extends AppCompatActivity {

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
    int kill_red[] = { 0,0,0,0,0,0,0,0, // red인 말들만 죽일수있음
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0};
    int enpassant[] = { 0,0,0,0,0,0,0,0, // 2칸 전진한 말 표시
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0};

    Button give_up_w; Button draw_w;

    Drawable[] temp = new Drawable[1];// 버튼 클릭 시 이미지 임시 저장
    int[] temp_index = new int[1]; // 첫번째 버튼의 번호 저장(첫번째 누른 block의 인덱스)
    int[] choose_num = new int[1]; // 첫번째 버튼의 말의 종류 저장 (1~6)
    boolean[] flag = {false}; // 버튼 2번눌렀을때
    int count=0;
    boolean turn=false; //순서 표시, 처음에는 백 먼저
    /*int [][]board_state=new int [50][64];//현재 보드 저장
    int repeat[]={ 0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0}; //반복횟수
    boolean r=false;
    int round=0;*/
//---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_b);

        //  ImageView - ID  match
        for (int i = 0; i < 64; i++)
            block[i] = (ImageView) findViewById(block_id[i]);

        draw_w=(Button)findViewById(R.id.draw_w);
        give_up_w=(Button)findViewById(R.id.give_up_w);

       /* for(int i=0; i<64;i++)
            board_state[0][i]=number[i];
        round++;*/

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

        give_up_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                give_up(2);
            }
        });

        draw_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw_suggestion();
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
                if(turn==true) {
                    switch (number[temp_index[0]]) {
                        case 1: {
                            choose_num[0] = 1;
                            rook(num, true);
                            break;
                        }
                        case 2: {
                            choose_num[0] = 2;
                            knight(num, true);
                            break;
                        }
                        case 3: {
                            choose_num[0] = 3;
                            bishop(num, true);
                            break;
                        }
                        case 4: {
                            choose_num[0] = 4;
                            queen(num, true);
                            break;
                        }
                        case 5: {
                            choose_num[0] = 5;
                            king(num, true);
                            break;
                        }
                        case 6: { //일반 pawn
                            choose_num[0] = 6;
                            pawn(num, true);
                            break;
                        }
                        case 7: { //처음 움직이는 pawn_b
                            choose_num[0] = 6;
                            f_pawn(num, true);
                            break;
                        }
                        case 11: case 12: case 13: case 14: case 15: case 16: case 17: {
                            clear();
                            Toast.makeText(this,"'흑'차례입니다.",Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }

                }
                else if(turn==false){
                    switch (number[temp_index[0]]) {
                        case 1: case 2: case 3: case 4: case 5: case 6: case 7:{
                            clear();
                            Toast.makeText(this,"'백'차례입니다.",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 11: { //rook_w
                            choose_num[0] = 11;
                            rook(num, false);
                            break;
                        }
                        case 12: {
                            choose_num[0] = 12;
                            knight(num, false);
                            break;
                        }
                        case 13: {
                            choose_num[0] = 13;
                            bishop(num, false);
                            break;
                        }
                        case 14: {
                            choose_num[0] = 14;
                            queen(num, false);
                            break;
                        }
                        case 15: {
                            choose_num[0] = 15;
                            king(num, false);
                            break;
                        }
                        case 16: {
                            choose_num[0] = 16;
                            pawn(num, false);
                            break;
                        }
                        case 17: {
                            choose_num[0] = 16;
                            f_pawn(num, false);
                            break;
                        }


                    }

                }

            }
        }
        //버튼 선택 2  (temp[0]!=null) : 이미지가 하나라도 복사되었을 때
        else {
            // 두번 눌렀을때 = 선택취소
            if(num==temp_index[0]) {
                if(turn==true) turn=false;
                else if (turn==false) turn=true;
            }
            // dot
            else if(number[num]==0) {
                enpassant_clear();
                if(choose_num[0]==6&&(num>=56&&num<=63)){ //pawn_b이 상대방 진영끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num,true);
                }
                else if(choose_num[0]==16&&(num>=0&&num<=7)) { //pawn-w가 상대방 진영 끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num,false);
                }
                else {
                    number[temp_index[0]] = 0;
                    number[num] = choose_num[0]; // number[0] fix
                    block[temp_index[0]].setImageDrawable(block[num].getDrawable()); // 첫번째 선택한 버튼 자리에 두번째 선택한 버튼 이미지 삽입
                    block[num].setImageDrawable(temp[0]); // 두번째 선택한 버튼 자리에 첫번째 선택에서 저장해둔 이미지 삽입
                }

                count++;

            }
            // 양파상 ( w -> b )
            else if (number[temp_index[0]]==16&& enpassant[num]==1&&kill_red[num]==1&&(num==temp_index[0]-1||num==temp_index[0]+1)) {
                number[temp_index[0]] =0; number[num]=0; number[num-8] = 16;
                enpassant[num]=0;
                block[num-8].setImageDrawable(temp[0]);
                block[num-8].setVisibility(View.VISIBLE);
                block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                block[num].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                block[num].setBackgroundColor(getResources().getColor(R.color.transparent));
                count=0;
            }
            //양파상 (b -> w)
            else if (number[temp_index[0]]==6&& enpassant[num]==1&&kill_red[num]==1&&(num==temp_index[0]-1||num==temp_index[0]+1)) {
                number[temp_index[0]] =0; number[num]=0; number[num+8] = 6;
                enpassant[num]=0;
                block[num+8].setImageDrawable(temp[0]);
                block[num+8].setVisibility(View.VISIBLE);
                block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                block[num].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                block[num].setBackgroundColor(getResources().getColor(R.color.transparent));
                count=0;
            }

            //다른 말 선택
            else if(kill_red[num]==1 )  {
                enpassant_clear();
                checkmate(num);
                count=0;
                if(choose_num[0]==6&&(num>=56&&num<=63)){ //pawn_b이 상대방 진영끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num,true);

                }
                else if(choose_num[0]==16&&(num>=0&&num<=7)) { //pawn-w가 상대방 진영 끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num,false);

                }
                else {
                    number[temp_index[0]] = 0;
                    number[num] = choose_num[0];
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    block[num].setImageDrawable(temp[0]); // 두번째 선택한 버튼 자리에 첫번째 선택에서 저장해둔 이미지 삽입
                }

            }
            else if(kill_red[num]!=1) {
                if(turn==true) turn=false;
                else if (turn==false) turn=true;
            }
            // 2칸 전진한 경우
            if((number[num]==6&&num==temp_index[0]+16)||(number[num]==16&&num==temp_index[0]-16)) {
                enpassant[num] = 1 ;
            }
            // 2칸 전진한 자리를 벗어난 경우 0으로 만듬
            else if(enpassant[temp_index[0]]==1)  enpassant[temp_index[0]]=0;
                // 양파상인 위치의 폰이 먹혔을 경우
            else if(enpassant[num]==1) enpassant[num] = 0;

            //move의 else(버튼2선택) 공통
            clear() ;
            for(int i=0;i<64;i++) {
                block[i].setBackgroundColor(getResources().getColor(R.color.transparent));
                if(number[i]==0) block[i].setVisibility(View.INVISIBLE);
            }
            if(turn==true) turn=false;
            else if (turn==false) turn=true;

            /*for(int i=0;i<=round;i++){
                for(int j=0;j<64;j++){
                    if(board_state[i][j]==number[j])//같은 상태가 있으면
                        repeat[i]++; //반복횟수 증가
                    r=true;
                }
            }
            if(r==false) {
                for (int i = 0; i < 64; i++)
                    board_state[round++][i] = number[i];
            }
            r=false;
            check_repeat(repeat);*/
        }
        if(count>=50) end_game(0); //말 갯수 변화 없이 50수 진행되면 비긴다
        if (turn == true) AI(); //유저 차례가 끝나고 흑의 차례가 되면 ai 실행

    }
    //-------------------------------함수 정리-----------------------------------
    void clear() {
        temp_index[0] = -1 ;
        flag[0] = false ;
        temp[0] = null ;
        for(int i=0;i<64;i++) kill_red[i]= 0;

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
    boolean dot(int index) {
        if(number[index]==0) return true;
        else return false ;
    }
    void red(int index) {
        block[index].setBackgroundColor(getResources().getColor(R.color.red));
        kill_red[index]= 1;
    }
    void enpassant_clear(){
        for(int i=0;i<64;i++){
            if(enpassant[i]!=0) enpassant[i]=0;
        }
    }
    void checkmate(int spot){
        if(number[spot]==5){
            end_game(1);
        }
        else if(number[spot]==15){
            end_game(2);
        }
    }
   /* void check_repeat(int repeat[]){
        for(int i=0;i<50;i++)
            if(repeat[i]>=3) end_game(0);
    }*/


    /////////////각 말들 움직임----------------------------------------------------------------
    int k; // (임시) 위치변수
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
            // 양파상
            if(spot%8>=1&&enpassant[spot+1]==1&&number[spot+9]==0) red(spot+1);
            if(8-spot%8-1>=1&&enpassant[spot-1]==1&&number[spot+7]==0) red(spot-1);
        }
        if(bw==false){
            for (int a = spot - 8; a >= spot - 8; a = a - 8) {
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
            // 양파상
            if(spot%8>=1&&enpassant[spot-1]==1&&number[spot-9]==0) red(spot-1);
            if(8-spot%8-1>=1&&enpassant[spot+1]==1&&number[spot-7]==0) red(spot+1);
        }
    }

    public void bishop(int spot,boolean bw){ //bishop 의 이동함수 완료

        for(int a=0;a<8-((spot%8)+1);a++){//우 대각 아래
            k=spot+(9*(a+1));
            if(in_board(k))
            {
                if(bw==true&& white(k))
                    red(k);
                if(bw==false&& black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int b=0;b<spot%8;b++){ //좌 대각 위
            k=spot-(9*(b+1));
            if(in_board(k))
            {
                if(bw==true&&white(k))
                    red(k);
                if(bw==false&&black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int c=0;c<spot%8;c++){ //좌 대각 아래
            k=spot+(7*(c+1));
            if(in_board(k))
            {
                if(bw==true&&white(k))
                    red(k);
                if(bw==false&&black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우 대각 위
            k=spot-(7*(d+1));
            if(in_board(k))
            {
                if(bw==true&&white(k))
                    red(k);
                if(bw==false&&black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
    }

    public void knight(int spot,boolean bw){ //knight 이동함수 완료
        k=spot+(8*2)+1 ;//우 대각 아래(2) down-2 right-1
        if(in_board(k)&&8-spot%8-1>=1)  //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot+(8*2)-1 ; //좌 대각 아래(2) down-2 left-1
        if(in_board(k)&& spot%8>=1) //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-(8*2)+1 ; //우 대각 위(2) up-2 right-1
        if(in_board(k)&&8-spot%8-1>=1)  //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-(8*2)-1 ; //좌 대각 위(2) up-2 left-1
        if(in_board(k)&& spot%8>=1) //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot+(8*1)+2 ; //우 대각 아래(1) right-2 down-1
        if(in_board(k)&& 8-spot%8-1>=2) //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot+(8*1)-2 ;//좌 대각 아래(1) left-2 down-1
        if(in_board(k)&& spot%8>=2) //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-(8*1)+2 ; //우 대각 위(1) right-2 up -1
        if(in_board(k)&& 8-spot%8-1>=2) //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-(8*1)-2 ; //좌 대각 위(1) left-2 up - 1
        if(in_board(k)&& spot%8>=2) //끝쪽에 있을때 넘어가기 방지
        {   if(dot(k)) block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
    }

    public void king(int spot,boolean bw){ //king 이동함수 완료
        k=spot+1 ; //우
        if(in_board(k)&&8-spot%8-1>=1)
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-1 ;  //좌
        if(in_board(k)&&spot%8>=1)
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot+7 ; //좌대각 아래
        if(in_board(k)&&spot%8>=1)
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot+8; //아래
        if(in_board(k))//아래
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot+9 ;
        if(in_board(k)&&8-spot%8-1>=1)//우대각 아래
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-7; //우대각 위
        if(in_board(k)&&8-spot%8-1>=1)
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot-8 ; //위
        if(in_board(k))
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }
        k=spot -9 ; //좌대각위
        if(in_board(k)&&spot%8>=1)
        {if (dot(k))
            block[k].setVisibility(View.VISIBLE);
        else if(bw==true&&white(k)) red(k) ;
        else if(bw==false&&black(k)) red(k);
        }

    }
    public void queen(int spot,boolean bw){ //queen 이동함수 완료(rook+bishop)

        for(int a=spot+8;a<64;a=a+8) {//하
            k=a ;
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int b=spot-8;b>=0;b=b-8) {//상
            k=b ;
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int c=0;c<spot%8;c++){//좌
            k=spot-(c+1);
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우
            k=spot+(d+1);
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int a=0;a<8-((spot%8)+1);a++){//우 대각 아래
            k=spot+(9*(a+1));
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int b=0;b<spot%8;b++){ //좌 대각 위
            k=spot-(9*(b+1));
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int c=0;c<spot%8;c++){ //좌 대각 아래
            k=spot+(7*(c+1));
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
        for(int d=0;d<8-((spot%8)+1);d++){//우 대각 아래
            k=spot-(7*(d+1));
            if(in_board(k))
            {   if(bw==true&&white(k)) red(k) ;
            else if(bw==false&&black(k)) red(k) ;
                if(number[k]!=0) break;
                block[k].setVisibility(View.VISIBLE);}
        }
    }
    //-------------------------------기타 규칙을 위한 함수들-------------------------------------------------

    public void change_pawn(int spot, boolean bw){ //pawn은 상대진영 끝까지 가면 원하는 말로 변경가능 하다 (주로 queen) , 이부분은 사용자로 부터 입력을 받아서 설정하도록 수정 필요
        // 새로운 스레드를 생성해서 따로 진행흐름을 가져가야 사용자가 입력하기전에 코드가 진행되지 않는다.
        Ai_b_Activity.this.runOnUiThread(new Runnable(){
            public void run() {
                final String[] oItems = {"룩", "나이트", "비숍", "퀸"};


                AlertDialog.Builder oDialog = new AlertDialog.Builder(Ai_b_Activity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                oDialog.setTitle("무슨 말로 바꾸시겠습니까?") ;
                oDialog.setItems(oItems,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(bw==true){
                            switch(which) {
                                case 0 :  {
                                    number[spot] =1 ;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.rook_b));
                                    break;
                                }
                                case 1 : {
                                    number[spot] = 2;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.knight_b));
                                    break;
                                }
                                case 2 : {
                                    number[spot] = 3;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.bishop_b));
                                    break;
                                }
                                case 3 : {
                                    number[spot] = 4;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.queen_b));
                                    break;
                                }
                            }
                        }
                        else {
                            switch(which) {
                                case 0 :  {
                                    number[spot] =11 ;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.rook_w));
                                    break;
                                }
                                case 1 : {
                                    number[spot] = 12;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.knight_w));
                                    break;
                                }
                                case 2 : {
                                    number[spot] = 13;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.bishop_w));
                                    break;
                                }
                                case 3 : {
                                    number[spot] = 14;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.queen_w));
                                    break;
                                }
                            }
                        }
                        block[spot].setVisibility(View.VISIBLE);
                    }


                }).setCancelable(false).create().show();
            }
        });

    }

    public void end_game(int bw){
        AlertDialog.Builder end_builder = new AlertDialog.Builder(Ai_b_Activity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        end_builder.setTitle("게임이 종료되었습니다.");
        if (bw == 1) end_builder.setMessage("'백'이 승리하였습니다!!!");
        else if (bw == 2) end_builder.setMessage("'흑'이 승리하였습니다!!!");
        else if (bw == 0) end_builder.setMessage("비겼습니다!!!");
        end_builder.setPositiveButton("홈으로 이동", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turn=false;
                count=0;
                clear();
                finish(); //현재(메인) 엑티비티 종료
            }
        });
        end_builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //앱 종료
                turn=false;
                count=0;
                clear();
                finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        });
        AlertDialog end=end_builder.create();
        end.show();
    }
    public void give_up(int bw){
        AlertDialog.Builder giveup_builder = new AlertDialog.Builder(Ai_b_Activity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        giveup_builder.setTitle("기권 버튼을 누르셨습니다.");
        giveup_builder.setMessage("기권하시겠습니까?");
        giveup_builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                end_game(bw);
            }
        });
        giveup_builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //앱 종료

            }
        });
        AlertDialog end=giveup_builder.create();
        end.show();

    }
    public void draw_suggestion(){
        AlertDialog.Builder giveup_builder = new AlertDialog.Builder(Ai_b_Activity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        giveup_builder.setTitle("상대방이 비기기를 제안했습니다.");
        giveup_builder.setMessage("받아드리겠습니까?");
        giveup_builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                end_game(0);
            }
        });
        giveup_builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //앱 종료

            }
        });
        AlertDialog end=giveup_builder.create();
        end.show();

    }
    //---------------------ai관련-----------------------------------------------------------

     public void AI() {
        number = MinMax(number).clone();//ai가 minmax 알고리즘으로 찾은 배열을 number로 지정
         int a=0; int b=0;
        for (int i = 0; i < 64; i++) { //알고리즘으로 나온 결과로 판을 바꾸는 것
            if (number[i] == 1) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.rook_b));
                block[i].setVisibility(View.VISIBLE);
            }//룩
            else if (number[i] == 2) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.knight_b));
                block[i].setVisibility(View.VISIBLE);
            }//나이트
            else if (number[i] == 3) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.bishop_b));
                block[i].setVisibility(View.VISIBLE);
            }//비숍
            else if (number[i] == 4) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.queen_b));
                block[i].setVisibility(View.VISIBLE);
            }//퀸
            else if (number[i] == 5) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.king_b));
                block[i].setVisibility(View.VISIBLE);
                a++;
            }//킹
            else if (number[i] == 7 || number[i] == 6 ) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.pawn_b));
                block[i].setVisibility(View.VISIBLE);
            }//폰
            else if (number[i] == 11) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.rook_w));
                block[i].setVisibility(View.VISIBLE);
            }
            else if (number[i] == 12) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.knight_w));
                block[i].setVisibility(View.VISIBLE);
            }
            else if (number[i] == 13) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.bishop_w));
                block[i].setVisibility(View.VISIBLE);
            }
            else if (number[i] == 14) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.queen_w));
                block[i].setVisibility(View.VISIBLE);
            }
            else if (number[i] == 15) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.king_w));
                block[i].setVisibility(View.VISIBLE);
                b++;
            }
            else if (number[i] == 17 || number[i] == 16 ) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.pawn_w));
                block[i].setVisibility(View.VISIBLE);
            }
            else if (number[i] == 0) {
                block[i].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                block[i].setVisibility(View.INVISIBLE);
            }
        }

        if (a!=1&&b==1) end_game(1);
        else if (a==1&&b!=1) end_game(2);
        turn = false;
    }

    public int[] MinMax(int[] number) {
        return MinMove(number, 5);
    }
    int alpha=-10000; int beta=10000;

    public int[] MaxMove(int[] node, int depth) {
        int[] P_Node = node.clone(); //매개변수로 받은 배열 복사
        int best_val = Evalstate_w(P_Node);
        int[] best_Node = node.clone();
        int[] Node = node.clone();
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_Node.clone();
        else {
            for (int i = 0; i < 64; i++) {
                if (P_Node[i] == 11) {
                    Node = rook_generatemove_max_w(P_Node, i, depth).clone();
                } else if (P_Node[i] == 12) {
                    Node = knight_generatemove_max_w(P_Node, i, depth).clone();
                } else if (P_Node[i] == 13) {
                    Node = bishop_generatemove_max_w(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 14) {
                    Node = queen_generatemove_max_w(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 15) {
                    Node = king_generatemove_max_w(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 16) {
                    Node = pawn_generatemove_max_w(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 17) {
                    Node = f_pawn_generatemove_max_w(P_Node, i, depth).clone();
                }
                if(Arrays.equals(Node,P_Node)) continue;

                if (Evalstate_w(Node) >= best_val) {
                    best_Node = Node.clone();
                    best_val = Evalstate_w(Node);

                }


            }
            return best_Node.clone();
        }
    }

    public int[] MinMove(int[] node, int depth) {
        int[] P_Node = node.clone(); //매개변수로 받은 배열 복사
        int best_val = Evalstate_w(P_Node);
        int[] best_Node = node.clone();
        int[] Node = node.clone() ;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_Node.clone();
        else {
            for (int i = 0; i < 64; i++) {
                if (P_Node[i] == 1) {
                    Node = rook_generatemove_min_b(P_Node, i, depth).clone();
                } else if (P_Node[i] == 2) {
                    Node = knight_generatemove_min_b(P_Node, i, depth).clone();
                } else if (P_Node[i] == 3) {
                    Node = bishop_generatemove_min_b(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 4) {
                    Node = queen_generatemove_min_b(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 5) {
                    Node = king_generatemove_min_b(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 6) {
                    Node = pawn_generatemove_min_b(P_Node, i, depth).clone();
                }
                else if (P_Node[i] == 7) {
                    Node = f_pawn_generatemove_min_b(P_Node, i, depth).clone();
                }
                if(Arrays.equals(Node,P_Node)) continue;
                if (Evalstate_w(Node) <= best_val) {
                    best_Node = Node.clone();
                    best_val = Evalstate_w(Node);
                }

            }
            return best_Node.clone();
        }
    }

    public int Evalstate_w(int[] number) {// 현재 상태를 평가하는 평가함수 (일단 ai가 백 이라는 가정으로 작성)
        int value = 0;
        for (int i = 0; i < 64; i++) {
            if (number[i] == 1) value = value - 5; //룩
            else if (number[i] == 2) value = value - 3;//나이트
            else if (number[i] == 3) value = value - 3;//비숍
            else if (number[i] == 4) value = value - 9;//퀸
            else if (number[i] == 7 || number[i] == 6) {
                value = value - 1;
            }
            else if (number[i]==5) value=value-1000;
            else if (number[i] == 11) value = value + 5;
            else if (number[i] == 12) value = value + 3;
            else if (number[i] == 13) value = value + 3;
            else if (number[i] == 14) value = value + 9;
            else if (number[i] == 17 || number[i] == 16) {
                value = value + 1;
            }
            else if (number[i]==15) value=value+1000;
        }
        return value;
    }

    public int[] rook_generatemove_max_w(int[] P_Node, int spot, int depth) {
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int a = spot - 8; a >= 0; a = a - 8) {//상
                if (in_board(a)) {
                    if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int c = 0; c < spot % 8; c++) {//좌
                int a = spot - (c + 1);
                if (in_board(a)) {
                    if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우
                int a = spot + (d + 1);
                if (in_board(a)) {
                    if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            return best_move;
        }
    }
    public int[] knight_generatemove_max_w(int []P_Node,int spot,int depth) {
        int []move;
        int []node = P_Node.clone();
        int []best_move=P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int []temp_node ;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        int a;
        a=spot+(8*2)+1 ;//우 대각 아래(2) down-2 right-1
        if(in_board(a)&&8-spot%8-1>=1)  //끝쪽에 있을때 넘어가기 방지
        {    if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot+(8*2)-1 ; //좌 대각 아래(2) down-2 left-1
        if(in_board(a)&& spot%8>=1) //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*2)+1 ; //우 대각 위(2) up-2 right-1
        if(in_board(a)&&8-spot%8-1>=1)  //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*2)-1 ; //좌 대각 위(2) up-2 left-1
        if(in_board(a)&& spot%8>=1) //끝쪽에 있을때 넘어가기 방지
        {  if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot+(8*1)+2 ; //우 대각 아래(1) right-2 down-1
        if(in_board(a)&& 8-spot%8-1>=2) //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot+(8*1)-2 ;//좌 대각 아래(1) left-2 down-1
        if(in_board(a)&& spot%8>=2) //끝쪽에 있을때 넘어가기 방지
        {  if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*1)+2 ; //우 대각 위(1) right-2 up -1
        if(in_board(a)&& 8-spot%8-1>=2) //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*1)-2 ; //좌 대각 위(1) left-2 up - 1
        if(in_board(a)&& spot%8>=2) //끝쪽에 있을때 넘어가기 방지
        {  if (!(P_Node[a]>=11&&P_Node[a]<=17)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) >= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val>alpha) alpha=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        return best_move ;
    }
    public int[] bishop_generatemove_max_w(int[] P_Node, int spot, int depth) {
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
                int t = spot + (9 * (a + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int b = 0; b < spot % 8; b++) { //좌 대각 위
                int t = spot - (9 * (b + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
                int t = spot + (7 * (c + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우 대각 위
                int t = spot - (7 * (d + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            return best_move;
        }
    }
    public int[] queen_generatemove_max_w(int []P_Node,int spot,int depth) {
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;

        for (int a = spot + 8; a < 64; a = a + 8) {//하
            if (in_board(a)) {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[a] != 0) break;
            }
        }
        for (int a = spot - 8; a >= 0; a = a - 8) {//상
            if (in_board(a)) {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[a] != 0) break;
            }
        }
        for (int a = 0; a < spot % 8; a++) {//좌
            int t= spot -(a+1) ;
            if (in_board(t)) {
                if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우
            int t= spot + (a+1) ;
            if (in_board(t)) {
                if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
            int t = spot + (9 * (a + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            int t = spot - (9 * (b + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
            int t= spot + (7 * (c + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우 대각 아래
            int t = spot - (7 * (d + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        return best_move ;
    }
    public int[] king_generatemove_max_w(int []P_Node,int spot,int depth) {
        int []move;
        int []node = P_Node.clone();
        int []best_move=P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int []temp_node ;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        int k = spot + 1; //우
        if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 1;  //좌
        if (in_board(k) && spot % 8 >= 1) {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot + 7; //좌대각 아래
        if (in_board(k) && spot % 8 >= 1) {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot + 8; //아래
        if (in_board(k))//아래
        {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot + 9;
        if (in_board(k) && 8 - spot % 8 - 1 >= 1)//우대각 아래
        {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 7; //우대각 위
        if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 8; //위
        if (in_board(k)) {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 9; //좌대각위
        if (in_board(k) && spot % 8 >= 1) {
            if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        return best_move ;
    }
    public int[] pawn_generatemove_max_w(int []P_Node, int spot, int depth){
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = spot - 8; a >= spot - 8; a = a - 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) { //white가 아니면 -> 점이면 (직선으로는 말을 잡을 수 없음)
                        if(a>=0&&a<=7){//change_pawn 상황일때
                            if(in_board(a+(8*2)+1)&&8-a%8-1>=1&&P_Node[a+(8*2)+1]==5){ //d-2,r-1에 킹이 있으면 knight로 바로 변경
                                node[a]=12;
                                node[spot]=0;
                            }
                            else if(in_board(a+(8*2)-1)&&a%8>=1&&P_Node[a+(8*2)-1]==5){ //d-2,1-1에 킹이 있으면 knight로 바로 변경
                                node[a]=12;
                                node[spot]=0;
                            }
                            else if(in_board(a+(8*1)+2)&&8-a%8-1>=2&&P_Node[a+(8*1)+2]==5){ //d-1,r-2에 킹이 있으면 knight로 바로 변경
                                node[a]=12;
                                node[spot]=0;
                            }
                            else if(in_board(a+(8*1)-2)&&a%8>=2&&P_Node[a+(8*1)-2]==5){ //d-1,l-2에 킹이 있으면 knight로 바로 변경
                                node[a]=12;
                                node[spot]=0;
                            }
                            else { //knight로 굳이 바꿀 상황이 아니면 그냥 퀸으로 변경
                                node[a] = 14;
                                node[spot] = 0;
                            }
                        }
                        else { //change_pawn 상황이 아니면
                            node[a] = node[spot];
                            node[spot] = 0;
                        }
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            if (in_board(spot - 9) && (P_Node[spot-9] >= 1 && P_Node[spot-9] <= 7)) //좌 대각 위에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1){
                    int t=spot-9;
                    // 위치 바꿔주기
                    if(t>=0&&t<=7){
                        if(in_board(t+(8*2)+1)&&8-t%8-1>=1&&P_Node[t+(8*2)+1]==5){ //d-2,r-1에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else if(in_board(t+(8*2)-1)&&t%8>=1&&P_Node[t+(8*2)-1]==5){ //d-2,1-1에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else if(in_board(t+(8*1)+2)&&8-t%8-1>=2&&P_Node[t+(8*1)+2]==5){ //d-1,r-2에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else if(in_board(t+(8*1)-2)&&t%8>=2&&P_Node[t+(8*1)-2]==5){ //d-1,l-2에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else{
                            node[t] = 14;
                            node[spot] = 0;
                        }
                    }
                    else {
                        node[spot-9] = node[spot];
                        node[spot] = 0;
                    }
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
            }

            if (in_board(spot - 7) && (P_Node[spot-7] >= 1 && P_Node[spot-7] <= 7)) //우 대각 위에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1){
                    int t=spot-7;
                    // 위치 바꿔주기
                    if(t>=0&&t<=7){
                        if(in_board(t+(8*2)+1)&&8-t%8-1>=1&&P_Node[t+(8*2)+1]==5){ //d-2,r-1에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else if(in_board(t+(8*2)-1)&&t%8>=1&&P_Node[t+(8*2)-1]==5){ //d-2,1-1에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else if(in_board(t+(8*1)+2)&&8-t%8-1>=2&&P_Node[t+(8*1)+2]==5){ //d-1,r-2에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else if(in_board(t+(8*1)-2)&&t%8>=2&&P_Node[t+(8*1)-2]==5){ //d-1,l-2에 킹이 있으면 knight로 바로 변경
                            node[t]=12;
                            node[spot]=0;
                        }
                        else{
                            node[t] = 14;
                            node[spot] = 0;
                        }
                    }
                    else {
                        node[spot-7] = node[spot];
                        node[spot] = 0;
                    }
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }

            }
            // 양파상
            if (spot % 8 >= 1 && enpassant[spot - 1] == 1 && P_Node[spot - 9] == 0){
                // 위치 바꿔주기
                node[spot-9] = node[spot];
                node[spot] = 0;
                node[spot-1]=0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
            if (8 - spot % 8 - 1 >= 1 && enpassant[spot + 1] == 1 && P_Node[spot - 7] == 0) {
                // 위치 바꿔주기
                node[spot-7] = node[spot];
                node[spot] = 0;
                node[spot+1]=0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val>alpha) alpha=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        return best_move;
    }
    public int[] f_pawn_generatemove_max_w(int []P_Node, int spot, int depth){
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = spot - 8; a >= spot - 16; a = a - 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) { //점일때
                        node[a] = 16;
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MinMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) >= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val>alpha) alpha=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            if (in_board(spot - 9) &&(P_Node[spot-9] >= 1 && P_Node[spot-9] <= 7)) //좌 대각 위에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1){
                    // 위치 바꿔주기
                    node[spot-9] = 16;
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }

            }
            if (in_board(spot - 7) && (P_Node[spot-9] >= 1 && P_Node[spot-9] <= 7)) //우 대각 위에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1){
                    // 위치 바꿔주기
                    node[spot-7] = 16;
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MinMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) >= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val>alpha) alpha=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
            }
            return best_move;
        }
    }


    public int[] rook_generatemove_min_b(int[] P_Node, int spot, int depth) {
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int a = spot - 8; a >= 0; a = a - 8) {//상

                if (in_board(a)) {
                    if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int c = 0; c < spot % 8; c++) {//좌
                int a = spot - (c + 1);
                if (in_board(a)) {
                    if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우
                int a = spot + (d + 1);
                if (in_board(a)) {
                    if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            return best_move;
        }
    }
    public int[] knight_generatemove_min_b(int []P_Node,int spot,int depth) {
        int []move;
        int []node = P_Node.clone();
        int []best_move=P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int []temp_node ;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        int a;
        a=spot+(8*2)+1 ;//우 대각 아래(2) down-2 right-1
        if(in_board(a)&&8-spot%8-1>=1)  //끝쪽에 있을때 넘어가기 방지
        {    if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot+(8*2)-1 ; //좌 대각 아래(2) down-2 left-1
        if(in_board(a)&& spot%8>=1) //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*2)+1 ; //우 대각 위(2) up-2 right-1
        if(in_board(a)&&8-spot%8-1>=1)  //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*2)-1 ; //좌 대각 위(2) up-2 left-1
        if(in_board(a)&& spot%8>=1) //끝쪽에 있을때 넘어가기 방지
        {  if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot+(8*1)+2 ; //우 대각 아래(1) right-2 down-1
        if(in_board(a)&& 8-spot%8-1>=2) //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MinMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot+(8*1)-2 ;//좌 대각 아래(1) left-2 down-1
        if(in_board(a)&& spot%8>=2) //끝쪽에 있을때 넘어가기 방지
        {  if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*1)+2 ; //우 대각 위(1) right-2 up -1
        if(in_board(a)&& 8-spot%8-1>=2) //끝쪽에 있을때 넘어가기 방지
        {   if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        a=spot-(8*1)-2 ; //좌 대각 위(1) left-2 up - 1
        if(in_board(a)&& spot%8>=2) //끝쪽에 있을때 넘어가기 방지
        {  if (!(P_Node[a]>=1&&P_Node[a]<=7)) {
            // 위치 바꿔주기
            node[a] = node[spot];
            node[spot] = 0;
            // 한 칸 옮겼을때 상태 저장
            temp_node = node.clone() ;
            // 깊이 0까지 탐색
            move = MaxMove(node,depth-1).clone();
            // 깊이 0까지에서의 value >= 현재까지의 best_val
            if (Evalstate_w(move) <= best_val ) {
                best_move=temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                best_val = Evalstate_w(move);
            }
            if(best_val<beta) beta=best_val;
            else return best_move.clone();
            node= P_Node.clone(); // 원상태로
        }
        }
        return best_move ;
    }
    public int[] bishop_generatemove_min_b(int[] P_Node, int spot, int depth){
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
                int t = spot + (9 * (a + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int b = 0; b < spot % 8; b++) { //좌 대각 위
                int t = spot - (9 * (b + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
                int t = spot + (7 * (c + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우 대각 위
                int t = spot - (7 * (d + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            return best_move;
        }
    }
    public int[] queen_generatemove_min_b(int []P_Node,int spot,int depth) {
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;

        for (int a = spot + 8; a < 64; a = a + 8) {//하
            if (in_board(a)) {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[a] != 0) break;
            }
        }
        for (int a = spot - 8; a >= 0; a = a - 8) {//상
            if (in_board(a)) {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[a] != 0) break;
            }
        }
        for (int a = 0; a < spot % 8; a++) {//좌
            int t= spot -(a+1) ;
            if (in_board(t)) {
                if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우
            int t = spot + (a+1) ;
            if (in_board(t)) {
                if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
            int t = spot + (9 * (a + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            int t = spot - (9 * (b + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
            int t= spot + (7 * (c + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우 대각 아래
            int t = spot - (7 * (d + 1));
            if (in_board(t)) {
                if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                    // 위치 바꿔주기
                    node[t] = node[spot];
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
                if (P_Node[t] != 0) break;
            }
        }
        return best_move ;
    }
    public int[] king_generatemove_min_b(int []P_Node,int spot,int depth) {
        int []move;
        int []node = P_Node.clone();
        int []best_move=P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int []temp_node ;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        int k = spot + 1; //우
        if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 1;  //좌
        if (in_board(k) && spot % 8 >= 1) {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot + 7; //좌대각 아래
        if (in_board(k) && spot % 8 >= 1) {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot + 8; //아래
        if (in_board(k))//아래
        {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot + 9;
        if (in_board(k) && 8 - spot % 8 - 1 >= 1)//우대각 아래
        {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 7; //우대각 위
        if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 8; //위
        if (in_board(k)) {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        k = spot - 9; //좌대각위
        if (in_board(k) && spot % 8 >= 1) {
            if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                // 위치 바꿔주기
                node[k] = node[spot];
                node[spot] = 0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MaxMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) <= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로
            }
        }
        return best_move ;
    }
    public int[] pawn_generatemove_min_b(int []P_Node, int spot, int depth) {
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = spot + 8; a <= spot + 8; a = a + 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) { //black 이 아니면 -> 점일때만 (직선으로는 잡을수 없음)
                        if(a>=56&&a<=63){//change_pawn 상황일때
                            if(in_board(a-(8*2)+1)&&8-a%8-1>=1&&P_Node[a-(8*2)+1]==15) {//u-2,r-1
                                node[a] = 2;
                                node[spot] = 0;
                            }
                            else if(in_board(a-(8*2)-1)&&spot%8>=1&&P_Node[a-(8*2)-1]==15) {//u-2,l-1
                                node[a] = 2;
                                node[spot] = 0;
                            }
                            else if(in_board(a-(8*1)+1)&&8-a%8-1>=1&&P_Node[a-(8*1)+2]==15) {//u-1,r-2
                                node[a] = 2;
                                node[spot] = 0;
                            }
                            else if(in_board(a-(8*1)-2)&&spot%8>=1&&P_Node[a-(8*1)-2]==15) {//u-1,l-2
                                node[a] = 2;
                                node[spot] = 0;
                            }
                            else{
                                node[a] = 4;
                                node[spot] = 0;
                            }
                        }
                        else {
                            node[a] = node[spot];
                            node[spot] = 0;
                        }
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            if (in_board(spot + 9) && (P_Node[spot+9] >= 11 && P_Node[spot+9] <= 17)) //우 대각 아래에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1){
                    int t=spot+9;
                    // 위치 바꿔주기
                    if(t>=56&&t<=63){
                        if(in_board(t-(8*2)+1)&&8-t%8-1>=1&&P_Node[t-(8*2)+1]==15) {//u-2,r-1에 킹이 있다면
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else if(in_board(t-(8*2)-1)&&spot%8>=1&&P_Node[t-(8*2)-1]==15) {//u-2,l-1
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else if(in_board(t-(8*1)+1)&&8-t%8-1>=1&&P_Node[t-(8*1)+2]==15) {//u-1,r-2
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else if(in_board(t-(8*1)-2)&&spot%8>=1&&P_Node[t-(8*1)-2]==15) {//u-1,l-2
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else{
                            node[t] = 4;
                            node[spot] = 0;
                        }
                    }
                    else {
                        node[spot+9] = node[spot];
                        node[spot] = 0;
                    }
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로

                }

            }
            if (in_board(spot + 7) &&(P_Node[spot+7] >= 11 && P_Node[spot+7] <= 17)) //좌 대각 아래에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1){
                    int t=spot+7;
                    // 위치 바꿔주기
                    if(t>=56&&t<=63){
                        if(in_board(t-(8*2)+1)&&8-t%8-1>=1&&P_Node[t-(8*2)+1]==15) {//u-2,r-1에 킹이 있다면
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else if(in_board(t-(8*2)-1)&&spot%8>=1&&P_Node[t-(8*2)-1]==15) {//u-2,l-1
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else if(in_board(t-(8*1)+1)&&8-t%8-1>=1&&P_Node[t-(8*1)+2]==15) {//u-1,r-2
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else if(in_board(t-(8*1)-2)&&spot%8>=1&&P_Node[t-(8*1)-2]==15) {//u-1,l-2
                            node[t] = 2;
                            node[spot] = 0;
                        }
                        else{
                            node[t] = 4;
                            node[spot] = 0;
                        }
                    }
                    else {
                        node[spot+7] = node[spot];
                        node[spot] = 0;
                    }
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로

                }

            }
            // 양파상
            if (spot % 8 >= 1 && enpassant[spot + 1] == 1 && P_Node[spot + 9] == 0) {

                // 위치 바꿔주기
                node[spot+9] = node[spot];
                node[spot] = 0;
                node[spot+1]=0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로

            }
            if (8 - spot % 8 - 1 >= 1 && enpassant[spot - 1] == 1 && P_Node[spot + 7] == 0){

                // 위치 바꿔주기
                node[spot+7] = node[spot];
                node[spot] = 0;
                node[spot-1]=0;
                // 한 칸 옮겼을때 상태 저장
                temp_node = node.clone();
                // 깊이 0까지 탐색
                move = MinMove(node, depth - 1).clone();
                // 깊이 0까지에서의 value >= 현재까지의 best_val
                if (Evalstate_w(move) >= best_val) {
                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                    best_val = Evalstate_w(move);
                }
                if(best_val<beta) beta=best_val;
                else return best_move.clone();
                node = P_Node.clone(); // 원상태로

            }

        }
        return best_move;
    }
    public int[] f_pawn_generatemove_min_b(int []P_Node, int spot, int depth){
        int[] move;
        int[] node = P_Node.clone();
        int[] best_move = P_Node.clone();
        int best_val = Evalstate_w(best_move);
        int[] temp_node;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_move;
        else {
            for (int a = spot + 8; a <= spot + 16; a = a + 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) { //점일때
                        node[a] = 6;
                        node[spot] = 0;
                        // 한 칸 옮겼을때 상태 저장
                        temp_node = node.clone();
                        // 깊이 0까지 탐색
                        move = MaxMove(node, depth - 1).clone();
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (Evalstate_w(move) <= best_val) {
                            best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                            best_val = Evalstate_w(move);
                        }
                        if(best_val<beta) beta=best_val;
                        else return best_move.clone();
                        node = P_Node.clone(); // 원상태로
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            if (in_board(spot + 9) && (P_Node[spot+9] >= 11 && P_Node[spot+9] <= 17)) //우 대각 아래에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1){
                    // 위치 바꿔주기
                    node[spot+9] = 6;
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }

            }
            if (in_board(spot + 7) && (P_Node[spot+7] >= 11 && P_Node[spot+7] <= 17)) //좌 대각 아래에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1){
                    // 위치 바꿔주기
                    node[spot+7] = 6;
                    node[spot] = 0;
                    // 한 칸 옮겼을때 상태 저장
                    temp_node = node.clone();
                    // 깊이 0까지 탐색
                    move = MaxMove(node, depth - 1).clone();
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (Evalstate_w(move) <= best_val) {
                        best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
                        best_val = Evalstate_w(move);
                    }
                    if(best_val<beta) beta=best_val;
                    else return best_move.clone();
                    node = P_Node.clone(); // 원상태로
                }
            }
            return best_move;
        }
    }


    public boolean game_ended(int[] P_Node){
        int a=0;
        for(int i=0;i<64;i++){
            if(P_Node[i]==5||P_Node[i]==15) a++;//king이 둘다있으면 a는 2가 된다.
        }
        if(a<2) return true;
        else return false;
    }

}