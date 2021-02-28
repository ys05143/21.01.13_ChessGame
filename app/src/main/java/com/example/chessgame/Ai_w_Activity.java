 package com.example.chessgame;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.random;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

 public class Ai_w_Activity extends AppCompatActivity {

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
    int number[] = {11, 12, 13, 15, 14, 13, 12, 11,
            17, 17, 17, 17, 17, 17, 17, 17,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            7, 7, 7, 7, 7, 7, 7, 7,
            1, 2, 3, 5, 4, 3, 2, 1};
    int kill_red[] = {0, 0, 0, 0, 0, 0, 0, 0, // red인 말들만 죽일수있음
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0};
    int enpassant[] = {0, 0, 0, 0, 0, 0, 0, 0, // 2칸 전진한 말 표시
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0};
     int is_first_move[] = {1, 1, 1, 1, 1, 1, 1, 1, // 한번도 안움직인 말 표시
             1, 1, 1, 1, 1, 1, 1, 1,
             0, 0, 0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0,
             1, 1, 1, 1, 1, 1, 1, 1,
             1, 1, 1, 1, 1, 1, 1, 1};

    Button give_up_w; TextView ai_message;

    Drawable[] temp = new Drawable[1];// 버튼 클릭 시 이미지 임시 저장
    int[] temp_index = new int[1]; // 첫번째 버튼의 번호 저장(첫번째 누른 block의 인덱스)
    int[] choose_num = new int[1]; // 첫번째 버튼의 말의 종류 저장 (1~6)
    boolean[] flag = {false}; // 버튼 2번눌렀을때
    int count = 0;
    boolean turn = false; //순서 표시, 처음에는 백 먼저
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
        setContentView(R.layout.activity_ai_w);

        //  ImageView - ID  match
        for (int i = 0; i < 64; i++)
            block[i] = (ImageView) findViewById(block_id[i]);

        give_up_w = (Button) findViewById(R.id.give_up_w);
        ai_message=(TextView)findViewById(R.id.ai_message);

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
                give_up(1);
            }
        });
        ai_message.setTextColor(getResources().getColor(R.color.black));
        AiThread t = new AiThread() ;
        t.start();
    } //onCreate

    Handler handler = new Handler() ;
    boolean start_handler = false;

public class AiThread extends Thread { //work thread
        @Override
    public void run() {
                AI(); //여기서 start_handler를 true로 바꿔줌
                    while(turn==false) {
                           if(start_handler) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
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

                                            }//킹
                                            else if (number[i] == 7 || number[i] == 6) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.pawn_b));
                                                block[i].setVisibility(View.VISIBLE);
                                            }//폰
                                            else if (number[i] == 11) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.rook_w));
                                                block[i].setVisibility(View.VISIBLE);
                                            } else if (number[i] == 12) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.knight_w));
                                                block[i].setVisibility(View.VISIBLE);
                                            } else if (number[i] == 13) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.bishop_w));
                                                block[i].setVisibility(View.VISIBLE);
                                            } else if (number[i] == 14) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.queen_w));
                                                block[i].setVisibility(View.VISIBLE);
                                            } else if (number[i] == 15) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.king_w));
                                                block[i].setVisibility(View.VISIBLE);

                                            } else if (number[i] == 17 || number[i] == 16) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.pawn_w));
                                                block[i].setVisibility(View.VISIBLE);
                                            } else if (number[i] == 0) {
                                                block[i].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                                                block[i].setVisibility(View.INVISIBLE);
                                            }
                                        }
                                        ValueAnimator ani=ValueAnimator.ofObject(new ArgbEvaluator(),getResources().getColor(R.color.gray),getResources().getColor(R.color.transparent));
                                        ani.setDuration(3000);
                                        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                            @Override
                                            public void onAnimationUpdate(ValueAnimator animation) {
                                                block[Point].setBackgroundColor((int)animation.getAnimatedValue());
                                            }
                                        });
                                        ani.start();
                                        ai_message.setTextColor(getResources().getColor(R.color.transparent));
                                        turn = true;
                                        start_handler = false;
                                    }
                                });

                           }
                    } // while

            };
}



    //-----------------------------MOVE() 부분 -----------------------------------------------
    public void move(int num) {
    // 차례가 아닐때
    if(turn==false) {
        Toast.makeText(this, "user의 차례가 아닙니다.", Toast.LENGTH_SHORT).show();
        return ;
    }
        boolean b_king = false;
        for(int i=0; i<64;i++) {
            if(number[i]==5) b_king=true;
        }
        if(!b_king) end_game(1);

    int[] f_number = number.clone();
        // 버튼 선택 1
        if (temp[0] == null && number[num] != 0) {
            if (!flag[0]) {// 한번 눌렀을 때
                temp[0] = block[num].getDrawable();
                temp_index[0] = num;
                flag[0] = true;
                // switch 부문( 말의 종류에따라서 )
                if (turn == true) { //흑의 차례일때
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
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17: {
                            clear();
                            Toast.makeText(this, "'흑'차례입니다.", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }

                } else if (turn == false) { //백의 차례일때
                    clear();
                    Toast.makeText(this, "user의 차례가 아닙니다.", Toast.LENGTH_SHORT).show();
                }

            }
            return;
        }
        //버튼 선택 2  (temp[0]!=null) : 이미지가 하나라도 복사되었을 때
        else {
            // 두번 눌렀을때 = 선택취소
            if (num == temp_index[0]) {

            }
            // dot
            else if (number[num] == 0) {
                enpassant_clear();
                if (choose_num[0] == 6 && (num >= 56 && num <= 63)) { //pawn_b이 상대방 진영끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num, true);

                } else if (choose_num[0] == 16 && (num >= 0 && num <= 7)) { //pawn-w가 상대방 진영 끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num, false);
                } else {
                    number[temp_index[0]] = 0;
                    number[num] = choose_num[0]; // number[0] fix
                    block[temp_index[0]].setImageDrawable(block[num].getDrawable()); // 첫번째 선택한 버튼 자리에 두번째 선택한 버튼 이미지 삽입
                    block[num].setImageDrawable(temp[0]); // 두번째 선택한 버튼 자리에 첫번째 선택에서 저장해둔 이미지 삽입
                    is_first_move[temp_index[0]]=0;
                    is_first_move[num]=0;
                }

                count++;

            }
//            // 양파상 ( w -> b )
//            else if (number[temp_index[0]] == 16 && enpassant[num] == 1 && kill_red[num] == 1 && (num == temp_index[0] - 1 || num == temp_index[0] + 1)) {
//                number[temp_index[0]] = 0;
//                number[num] = 0;
//                number[num - 8] = 16;
//                enpassant[num] = 0;
//                block[num - 8].setImageDrawable(temp[0]);
//                block[num - 8].setVisibility(View.VISIBLE);
//                block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot));
//                block[num].setImageDrawable(getResources().getDrawable(R.drawable.dot));
//                block[num].setBackgroundColor(getResources().getColor(R.color.transparent));
//                count = 0;
//            }
//            //양파상 (b -> w)
//            else if (number[temp_index[0]] == 6 && enpassant[num] == 1 && kill_red[num] == 1 && (num == temp_index[0] - 1 || num == temp_index[0] + 1)) {
//                number[temp_index[0]] = 0;
//                number[num] = 0;
//                number[num + 8] = 6;
//                enpassant[num] = 0;
//                block[num + 8].setImageDrawable(temp[0]);
//                block[num + 8].setVisibility(View.VISIBLE);
//                block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot));
//                block[num].setImageDrawable(getResources().getDrawable(R.drawable.dot));
//                block[num].setBackgroundColor(getResources().getColor(R.color.transparent));
//                count = 0;
//            }

            //다른 말 선택
            else if (kill_red[num] == 1) {
                enpassant_clear();
                checkmate(num);
                count = 0;
                if (choose_num[0] == 6 && (num >= 56 && num <= 63)) { //pawn_b이 상대방 진영끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num, true);

                } else if (choose_num[0] == 16 && (num >= 0 && num <= 7)) { //pawn-w가 상대방 진영 끝까지 갔을 때
                    number[temp_index[0]] = 0;
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    change_pawn(num, false);

                } else {
                    number[temp_index[0]] = 0;
                    number[num] = choose_num[0];
                    block[temp_index[0]].setImageDrawable(getResources().getDrawable(R.drawable.dot)); // 첫번째 선택한 버튼 자리에 투명 버튼 삽입
                    block[num].setImageDrawable(temp[0]); // 두번째 선택한 버튼 자리에 첫번째 선택에서 저장해둔 이미지 삽입
                    is_first_move[temp_index[0]]=0;
                    is_first_move[num]=0;
                }

            } else if (kill_red[num] != 1) {

            }
            // 2칸 전진한 경우
            if ((number[num] == 6 && num == temp_index[0] + 16) || (number[num] == 16 && num == temp_index[0] - 16)) {
                enpassant[num] = 1;
            }
            // 2칸 전진한 자리를 벗어난 경우 0으로 만듬
            else if (enpassant[temp_index[0]] == 1) enpassant[temp_index[0]] = 0;
                // 양파상인 위치의 폰이 먹혔을 경우
            else if (enpassant[num] == 1) enpassant[num] = 0;

            //move의 else(버튼2선택) 공통
            clear();
            for (int i = 0; i < 64; i++) {
                block[i].setBackgroundColor(getResources().getColor(R.color.transparent));
                if (number[i] == 0) block[i].setVisibility(View.INVISIBLE);
            }
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
        } // else
        boolean w_king = false;
        for(int i=0; i<64;i++) {
            if(number[i]==15) w_king=true;
        }
        if(!w_king) end_game(2);

        if (Arrays.equals(f_number,number)==false) turn =false;
        if (count >= 50) end_game(0); //말 갯수 변화 없이 50수 진행되면 비긴다
        if(turn==false) {
            ai_message.setTextColor(getResources().getColor(R.color.black));
            AiThread t = new AiThread() ;
            t.start();
        }
    }

    //-------------------------------함수 정리-----------------------------------
    void clear() {
        temp_index[0] = -1;
        flag[0] = false;
        temp[0] = null;
        for (int i = 0; i < 64; i++) kill_red[i] = 0;

    }

    boolean in_board(int index) {
        if (index >= 0 && index < 64) return true;
        else return false;
    }

    boolean black(int index) {
        if (number[index] >= 1 && number[index] <= 7) return true;
        else return false;
    }

    boolean white(int index) {
        if (number[index] >= 11 && number[index] <= 17) return true;
        else return false;
    }

    boolean dot(int index) {
        if (number[index] == 0) return true;
        else return false;
    }

    void red(int index) {
        block[index].setBackgroundColor(getResources().getColor(R.color.red));
        kill_red[index] = 1;
    }

    void enpassant_clear() {
        for (int i = 0; i < 64; i++) {
            if (enpassant[i] != 0) enpassant[i] = 0;
        }
    }

    void checkmate(int spot) {
        if (number[spot] == 5) {
            end_game(1);
        } else if (number[spot] == 15) {
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
                if (bw == true && white(a))
                    red(a);
                if (bw == false && black(a))
                    red(a);
                if (number[a] != 0) break;
                block[a].setVisibility(View.VISIBLE);
            }
        }
        for (int b = spot - 8; b >= 0; b = b - 8) {//상
            if (in_board(b)) {
                if (bw == true && white(b))
                    red(b);
                if (bw == false && black(b))
                    red(b);
                if (number[b] != 0) break;
                block[b].setVisibility(View.VISIBLE);
            }
        }
        for (int c = 0; c < spot % 8; c++) {//좌
            if (in_board(spot - (c + 1))) {
                if (bw == true && white(spot - (c + 1)))
                    red(spot - (c + 1));
                if (bw == false && black(spot - (c + 1)))
                    red(spot - (c + 1));
                if (number[spot - (c + 1)] != 0) break;
                block[spot - (c + 1)].setVisibility(View.VISIBLE);
            }
        }
        for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우
            if (in_board(spot + (d + 1))) {
                if (bw == true && white(spot + (d + 1)))
                    red(spot + (d + 1));
                if (bw == false && black(spot + (d + 1)))
                    red(spot + (d + 1));
                if (number[spot + (d + 1)] != 0) break;
                block[spot + (d + 1)].setVisibility(View.VISIBLE);
            }
        }

    }

    public void f_pawn(int spot, boolean bw) { //처음 움직이는 pawn의 이동함수 완료
        if (bw == false) { //pawn_w
            for (int a = spot + 8; a <= spot + 16; a = a + 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if (in_board(spot + 9) && black(spot + 9)) //우 대각 아래에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1)
                    red(spot + 9);
            }
            if (in_board(spot + 7) && black(spot + 7)) //좌 대각 아래에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1)
                    red(spot + 7);
            }
        }
        if (bw == true) { //pawn_b
            for (int a = spot - 8; a >= spot - 16; a = a - 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if (in_board(spot - 9) && white(spot - 9)) //좌 대각 위에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1)
                    red(spot - 9);
            }
            if (in_board(spot - 7) && white(spot - 7)) //우 대각 위에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1)
                    red(spot - 7);
            }
        }

    }

    public void pawn(int spot, boolean bw) { //일반 pawn의 이동함수 완료
        if (bw == false) { //white
            for (int a = spot + 8; a <= spot + 8; a = a + 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if (in_board(spot + 9) && black(spot + 9)) //우 대각 아래에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1)
                    red(spot + 9);
            }
            if (in_board(spot + 7) && black(spot + 7)) //좌 대각 아래에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1)
                    red(spot + 7);
            }
            // 양파상
            if (spot % 8 >= 1 && enpassant[spot + 1] == 1 && number[spot + 9] == 0) red(spot + 1);
            if (8 - spot % 8 - 1 >= 1 && enpassant[spot - 1] == 1 && number[spot + 7] == 0)
                red(spot - 1);
        }
        if (bw == true) { //black
            for (int a = spot - 8; a >= spot - 8; a = a - 8) {
                if (in_board(a)) {
                    if (number[a] != 0) break;
                    block[a].setVisibility(View.VISIBLE);
                }
            }
            if (in_board(spot - 9) && white(spot - 9)) //좌 대각 위에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1)
                    red(spot - 9);
            }
            if (in_board(spot - 7) && white(spot - 7)) //우 대각 위에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1)
                    red(spot - 7);
            }
//            // 양파상
//            if (spot % 8 >= 1 && enpassant[spot - 1] == 1 && number[spot - 9] == 0) red(spot - 1);
//            if (8 - spot % 8 - 1 >= 1 && enpassant[spot + 1] == 1 && number[spot - 7] == 0)
//                red(spot + 1);
        }
    }

    public void bishop(int spot, boolean bw) { //bishop 의 이동함수 완료

        for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
            k = spot + (9 * (a + 1));
            if (in_board(k)) {
                if (bw == true && white(k))
                    red(k);
                if (bw == false && black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            k = spot - (9 * (b + 1));
            if (in_board(k)) {
                if (bw == true && white(k))
                    red(k);
                if (bw == false && black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
            k = spot + (7 * (c + 1));
            if (in_board(k)) {
                if (bw == true && white(k))
                    red(k);
                if (bw == false && black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우 대각 위
            k = spot - (7 * (d + 1));
            if (in_board(k)) {
                if (bw == true && white(k))
                    red(k);
                if (bw == false && black(k))
                    red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
    }

    public void knight(int spot, boolean bw) { //knight 이동함수 완료
        k = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
        if (in_board(k) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
        if (in_board(k) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
        if (in_board(k) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
        if (in_board(k) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
        if (in_board(k) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
        if (in_board(k) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
        if (in_board(k) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
        if (in_board(k) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (dot(k)) block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
    }

    public void king(int spot, boolean bw) { //king 이동함수 완료
        k = spot + 1; //우
        if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - 1;  //좌
        if (in_board(k) && spot % 8 >= 1) {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot + 7; //좌대각 아래
        if (in_board(k) && spot % 8 >= 1) {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot + 8; //아래
        if (in_board(k))//아래
        {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot + 9;
        if (in_board(k) && 8 - spot % 8 - 1 >= 1)//우대각 아래
        {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - 7; //우대각 위
        if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - 8; //위
        if (in_board(k)) {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }
        k = spot - 9; //좌대각위
        if (in_board(k) && spot % 8 >= 1) {
            if (dot(k))
                block[k].setVisibility(View.VISIBLE);
            else if (bw == true && white(k)) red(k);
            else if (bw == false && black(k)) red(k);
        }

    }

    public void queen(int spot, boolean bw) { //queen 이동함수 완료(rook+bishop)

        for (int a = spot + 8; a < 64; a = a + 8) {//하
            k = a;
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int b = spot - 8; b >= 0; b = b - 8) {//상
            k = b;
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int c = 0; c < spot % 8; c++) {//좌
            k = spot - (c + 1);
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우
            k = spot + (d + 1);
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
            k = spot + (9 * (a + 1));
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            k = spot - (9 * (b + 1));
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
            k = spot + (7 * (c + 1));
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
        for (int d = 0; d < 8 - ((spot % 8) + 1); d++) {//우 대각 아래
            k = spot - (7 * (d + 1));
            if (in_board(k)) {
                if (bw == true && white(k)) red(k);
                else if (bw == false && black(k)) red(k);
                if (number[k] != 0) break;
                block[k].setVisibility(View.VISIBLE);
            }
        }
    }
    //-------------------------------기타 규칙을 위한 함수들-------------------------------------------------


    public void change_pawn(int spot, boolean bw) { //pawn은 상대진영 끝까지 가면 원하는 말로 변경가능 하다 (주로 queen) , 이부분은 사용자로 부터 입력을 받아서 설정하도록 수정 필요
        // 새로운 스레드를 생성해서 따로 진행흐름을 가져가야 사용자가 입력하기전에 코드가 진행되지 않는다.
        Ai_w_Activity.this.runOnUiThread(new Runnable() {
            public void run() {
                final String[] oItems = {"룩", "나이트", "비숍", "퀸"};


                AlertDialog.Builder oDialog = new AlertDialog.Builder(Ai_w_Activity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                oDialog.setTitle("무슨 말로 바꾸시겠습니까?");
                oDialog.setItems(oItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (bw == true) {
                            switch (which) {
                                case 0: {
                                    number[spot] = 1;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.rook_b));
                                    break;
                                }
                                case 1: {
                                    number[spot] = 2;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.knight_b));
                                    break;
                                }
                                case 2: {
                                    number[spot] = 3;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.bishop_b));
                                    break;
                                }
                                case 3: {
                                    number[spot] = 4;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.queen_b));
                                    break;
                                }
                            }
                        } else {
                            switch (which) {
                                case 0: {
                                    number[spot] = 11;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.rook_w));
                                    break;
                                }
                                case 1: {
                                    number[spot] = 12;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.knight_w));
                                    break;
                                }
                                case 2: {
                                    number[spot] = 13;
                                    block[spot].setImageDrawable(getResources().getDrawable(R.drawable.bishop_w));
                                    break;
                                }
                                case 3: {
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

    public void end_game(int bw) {
        AlertDialog.Builder end_builder = new AlertDialog.Builder(Ai_w_Activity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        end_builder.setTitle("게임이 종료되었습니다.");
        if (bw == 1) end_builder.setMessage("'백'이 승리하였습니다!!!");
        else if (bw == 2) end_builder.setMessage("'흑'이 승리하였습니다!!!");
        else if (bw == 0) end_builder.setMessage("비겼습니다!!!");
        end_builder.setPositiveButton("홈으로 이동", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turn = false;
                count = 0;
                clear();
                finish(); //현재(메인) 엑티비티 종료
            }
        });
        end_builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //앱 종료
                turn = false;
                count = 0;
                clear();
                finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        });
        AlertDialog end = end_builder.create();
        end.show();
    }

    public void give_up(int bw) {
        AlertDialog.Builder giveup_builder = new AlertDialog.Builder(Ai_w_Activity.this,
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
        AlertDialog end = giveup_builder.create();
        end.show();

    }

    public void draw_suggestion() {
        AlertDialog.Builder giveup_builder = new AlertDialog.Builder(Ai_w_Activity.this,
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
        AlertDialog end = giveup_builder.create();
        end.show();

    }

    public boolean check_castling_w(int []node, int side){ //white가 캐슬릉 가능한지 체크
        if(side==5) { //kingside castling
            if (is_first_move[3]!=1||is_first_move[0]!=1) return false;
        }
        else if(side==4){ //queenside castling
            if (is_first_move[3]!=1||is_first_move[7]!=1) return false;
        }

        int [] Node=node.clone();

        //두 기물 사이에 기물이 있는가
        if(side==5) { //kingside castling
            if (Node[1] != 0 || Node[2] != 0) return false;
        }
        else if(side==4){ //queenside castling
            if (Node[5] != 0 || Node[6] != 0|Node[4]!=0) return false;
        }

        //현재 킹이 공격 받고 있는가(처음위치에서 공격받고 있는가)
        if(is_attack_b(Node,3)) return false;

        //캐슬링을 했을 때 두 기물중 하나라도 공격 받고 있는가
        if(side==5){
           Node[1]=Node[3];
           Node[2]=Node[0];
           Node[3]=0;
           Node[0]=0;
          if(is_attack_b(Node,1)||is_attack_b(Node,2)) return false;
        }
        else if(side==4){
            Node[6]=Node[3];
            Node[5]=Node[7];
            Node[3]=0;
            Node[7]=0;
            if(is_attack_b(Node,5)||is_attack_b(Node,6)) return false;
        }

        //위 조건 모두 통과 했으면 캐슬링 가능
        return true;
    }

     public boolean check_castling_b(int []node, int side){//black이 캐슬링가능한가
         if(side==5) { //kingside castling
             if (is_first_move[59]!=1||is_first_move[56]!=1) return false;
         }
         else if(side==4){ //queenside castling
             if (is_first_move[59]!=1||is_first_move[63]!=1) return false;
         }

         int [] Node=node.clone();

         //두 기물 사이에 기물이 있는가
         if(side==5) { //kingside castling
             if (Node[57] != 0 || Node[58] != 0) return false;
         }
         else if(side==4){ //queenside castling
             if (Node[61] != 0 || Node[62] != 0||Node[60]!=0) return false;
         }

         //현재 킹이 공격 받고 있는가(처음위치 4에서 공격받고 있는가)
         if(is_attack_w(Node,59)) return false;

         //캐슬링을 했을 때 두 기물중 하나라도 공격 받고 있는가
         if(side==5){
             Node[57]=Node[59];
             Node[58]=Node[56];
             Node[59]=0;
             Node[56]=0;
             if(is_attack_w(Node,57)||is_attack_w(Node,58)) return false;
         }
         else if(side==4){
             Node[62]=Node[59];
             Node[61]=Node[63];
             Node[59]=0;
             Node[63]=0;
             if(is_attack_w(Node,61)||is_attack_w(Node,62)) return false;
         }

         //위 조건 모두 통과 했으면 캐슬링 가능
         return true;
     }


     public boolean is_attack_w(int []node,int n){
         int ret_val=-1;
         for(int i=0;i<64;i++) {
             switch (node[i]) {
                 //rook
                 case 11: {
                     ret_val = RookMax_c(node, i, n);
                     break;
                 }
                 //knight
                 case 12: {
                     ret_val = KnightMax_c(node, i, n);
                     break;
                 }
                 case 13: {
                     ret_val = BishopMax_c(node, i, n);
                     break;
                 }
                 case 14: {
                     ret_val = QueenMax_c(node, i, n);
                     break;
                 }
                 case 15: {
                     ret_val = KingMax_c(node, i, n);
                     break;
                 }
                 case 16: {
                     ret_val = PawnMax_c(node, i, n);
                     break;
                 }
                 case 17: {
                     ret_val = FPawnMax_c(node, i, n);
                     break;
                 }
                 default: {
                     continue;
                 }
             } // switch
         }
         if(ret_val==-1) return false;
         else return true;
     }
     public boolean is_attack_b(int []node,int n){
        int ret_val=-1;
         for(int i=0;i<64;i++) {
             switch (node[i]) {
                 //rook
                 case 1: {
                     ret_val = RookMin_c(node, i, n);
                     break;
                 }
                 //knight
                 case 2: {
                     ret_val = KnightMin_c(node, i, n);
                     break;
                 }
                 case 3: {
                     ret_val = BishopMin_c(node, i, n);
                     break;
                 }
                 case 4: {
                     ret_val = QueenMin_c(node, i, n);
                     break;
                 }
                 case 5: {
                     ret_val = KingMin_c(node, i, n);
                     break;
                 }
                 case 6: {
                     ret_val = PawnMin_c(node, i, n);
                     break;
                 }
                 case 7: {
                     ret_val = FPawnMin_c(node, i, n);
                     break;
                 }
                 default: {
                     continue;
                 }
             } // switch
         }
         if(ret_val==-1) return false;
         else return true;
     }
     //-----------------------------------------------------------
     public int RookMax_c(int[] node,int spot,int n) {
         for (int a = spot + 8; a < 64; a = a + 8) {//하
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int a = spot - 8; a >= 0; a = a - 8) {//상
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) {//좌
             int a = spot - (b+1) ;
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
             int a = spot + (b+1) ;
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }

         return -1; // -1이 리턴 되면 n자리 위협 안한다는 뜻
     }
     public int KnightMax_c(int[] node,int spot,int n) {
         int a = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
         if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
         if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
         if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
         if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
         if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
         if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
         if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
         if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }

         return -1;
     }
     public int BishopMax_c(int[] node,int spot,int n) {

         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
             int a = spot + (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }

         for (int b = 0; b < spot % 8; b++) { //좌 대각 위
             int a = spot - (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
             int a = spot + (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 위
             int a = spot - (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         return -1 ;
     }
     public int QueenMax_c(int[] node,int spot,int n) {

         for (int a = spot + 8; a < 64; a = a + 8) {//하
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int a = spot - 8; a >= 0; a = a - 8) {//상
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) {//좌
             int a = spot - (b + 1);
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
             int a = spot + (b + 1);
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
             int a = spot + (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) { //좌 대각 위
             int a = spot - (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
             int a= spot + (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
             int a = spot - (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         return -1;
     }
     public int KingMax_c(int[] node,int spot,int n) {

         int a = spot + 1; //우
         if (in_board(a)&&8 - spot % 8 - 1 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 1;  //좌
         if (in_board(a)&&spot % 8 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + 7; //좌대각 아래
         if (in_board(a)&&spot % 8 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + 8; //아래
         if (in_board(a)) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + 9; // 우대각 아래
         if (in_board(a)&&8 - spot % 8 - 1 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 7; //우대각 위
         if (in_board(a)&&8 - spot % 8 - 1 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 8; //위
         if (in_board(a)) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 9; //좌대각위
         if (in_board(a)&&spot % 8 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         return -1;
     }
     public int PawnMax_c(int[] node,int spot,int n) {

         // 1칸 전진
         for (int a = spot - 8; a >= spot - 8; a = a - 8) {
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         int a= spot-7 ;//우 대각 위에 상대방 말이 있을 때
         if (in_board(a)&& 8-(spot%8)>=2) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a= spot-9;// 좌 대각 위에 상대발 말이 있을 때
         if (in_board(a)&& spot%8>=1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         return -1;
     }
     public int FPawnMax_c(int[] node,int spot,int n) {

         // 1칸/2칸 전진
         for (int a = spot - 8; a >= spot - 16; a = a - 8) {
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         int a= spot-7 ;//우 대각 위에 상대방 말이 있을 때
         if (in_board(a)&& 8-(spot%8)>=2) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a= spot-9;// 좌 대각 아래에 상대발 말이 있을 때
         if (in_board(a)&& spot%8>=1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         return -1;
     }
     //------------------------------------------------------
     public int RookMin_c(int[] node,int spot,int n) {
         for (int a = spot + 8; a < 64; a = a + 8) {//하
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int a = spot - 8; a >= 0; a = a - 8) {//상
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) {//좌
             int a = spot - (b+1) ;
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
             int a = spot + (b+1) ;
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }

         return -1; // -1이 리턴 되면 n자리 위협 안한다는 뜻
     }
     public int KnightMin_c(int[] node,int spot,int n) {
         int a = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
         if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
         if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
         if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
         if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
         if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
         if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
         if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
         if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
         {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }

         return -1;
     }
     public int BishopMin_c(int[] node,int spot,int n) {

         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
             int a = spot + (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }

         for (int b = 0; b < spot % 8; b++) { //좌 대각 위
             int a = spot - (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
             int a = spot + (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 위
             int a = spot - (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         return -1 ;
     }
     public int QueenMin_c(int[] node,int spot,int n) {

         for (int a = spot + 8; a < 64; a = a + 8) {//하
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int a = spot - 8; a >= 0; a = a - 8) {//상
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) {//좌
             int a = spot - (b + 1);
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
             int a = spot + (b + 1);
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
             int a = spot + (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) { //좌 대각 위
             int a = spot - (9 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
             int a= spot + (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
             int a = spot - (7 * (b + 1));
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         return -1;
     }
     public int KingMin_c(int[] node,int spot,int n) {

         int a = spot + 1; //우
         if (in_board(a)&&8 - spot % 8 - 1 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 1;  //좌
         if (in_board(a)&&spot % 8 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + 7; //좌대각 아래
         if (in_board(a)&&spot % 8 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + 8; //아래
         if (in_board(a)) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot + 9; // 우대각 아래
         if (in_board(a)&&8 - spot % 8 - 1 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 7; //우대각 위
         if (in_board(a)&&8 - spot % 8 - 1 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 8; //위
         if (in_board(a)) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a = spot - 9; //좌대각위
         if (in_board(a)&&spot % 8 >= 1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         return -1;
     }
     public int PawnMin_c(int[] node,int spot,int n){
         // 1칸 전진
         for (int a = spot + 8; a <= spot + 8; a = a + 8) {
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         int a= spot+9 ;//우 대각 아래에 상대방 말이 있을 때
         if (in_board(a)&& 8-(spot%8)>=2) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a= spot+7;// 좌 대각 아래에 상대발 말이 있을 때
         if (in_board(a)&& spot%8>=1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         return -1;
     }
     public int FPawnMin_c(int[] node,int spot,int n){

         // 1칸/2칸 전진
         for (int a = spot + 8; a <= spot + 16; a = a + 8) {
             if (in_board(a)) {
                 //말이 해당자리 n을 공격할 수 있을 때
                 if (a == n) return n;
                 //  말이 있을경우
                 if (node[a] != 0) break;
             }
         }
         int a= spot+9 ;//우 대각 아래에 상대방 말이 있을 때
         if (in_board(a)&& 8-(spot%8)>=2) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         a= spot+7;// 좌 대각 아래에 상대발 말이 있을 때
         if (in_board(a)&& spot%8>=1) {
             //말이 해당자리 n을 공격할 수 있을 때
             if (a == n) return n;
         }
         return -1;
     }
     //------------------------------------------------------

    //---------------------ai관련-----------------------------------------------------------
    // 공통 변수
    final int INF = 10000 ;
     public int[] FinalNode = new int[64] ;
     int AI_DEPTH = 5;
     // random 부분
     ArrayList<int[]> cand=new ArrayList<int[]>();
     int Count=0;
     int t=0;
     int Point = 0;

    public void AI() {
        number = MinMax(number).clone();//ai가 minmax 알고리즘으로 찾은 배열을 number로 지정
        start_handler = true;
//        for (int i = 0; i < 64; i++) { //알고리즘으로 나온 결과로 판을 바꾸는 것
//            if (number[i] == 1) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.rook_b));
//                block[i].setVisibility(View.VISIBLE);
//            }//룩
//            else if (number[i] == 2) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.knight_b));
//                block[i].setVisibility(View.VISIBLE);
//            }//나이트
//            else if (number[i] == 3) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.bishop_b));
//                block[i].setVisibility(View.VISIBLE);
//            }//비숍
//            else if (number[i] == 4) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.queen_b));
//                block[i].setVisibility(View.VISIBLE);
//            }//퀸
//            else if (number[i] == 5) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.king_b));
//                block[i].setVisibility(View.VISIBLE);
//                a++;
//            }//킹
//            else if (number[i] == 7 || number[i] == 6) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.pawn_b));
//                block[i].setVisibility(View.VISIBLE);
//            }//폰
//            else if (number[i] == 11) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.rook_w));
//                block[i].setVisibility(View.VISIBLE);
//            } else if (number[i] == 12) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.knight_w));
//                block[i].setVisibility(View.VISIBLE);
//            } else if (number[i] == 13) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.bishop_w));
//                block[i].setVisibility(View.VISIBLE);
//            } else if (number[i] == 14) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.queen_w));
//                block[i].setVisibility(View.VISIBLE);
//            } else if (number[i] == 15) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.king_w));
//                block[i].setVisibility(View.VISIBLE);
//                b++;
//            } else if (number[i] == 17 || number[i] == 16) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.pawn_w));
//                block[i].setVisibility(View.VISIBLE);
//            } else if (number[i] == 0) {
//                block[i].setImageDrawable(getResources().getDrawable(R.drawable.dot));
//                block[i].setVisibility(View.INVISIBLE);
//            }
//        }
//        ValueAnimator ani=ValueAnimator.ofObject(new ArgbEvaluator(),getResources().getColor(R.color.gray),getResources().getColor(R.color.transparent));
//        ani.setDuration(5000);
//        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                block[Point].setBackgroundColor((int)animation.getAnimatedValue());
//            }
//        });
//        ani.start();
//        if (a!=1&&b==1) end_game(1);
//        else if (a==1&&b!=1) end_game(2);
//         turn = true;
    }

    public int[] MinMax(int[] number) {
        MaxMove(number.clone(), AI_DEPTH,-INF,INF);
        Point=Find_AI_Move(number.clone());
        return FinalNode.clone() ;
    }

    public int Find_AI_Move(int [] number){
        int r=-1;
        for(int i=0;i<64;i++){
//            if(FinalNode[i]!=number[i]&&FinalNode[i]!=0) {r=i; break;}
            if(FinalNode[i]!=number[i]){
                is_first_move[i]=0;
                if(FinalNode[i]!=0){
                    r=i; break;
                }
            }
        }
        return r;
    }

    public int MaxMove(int[] node, int depth,int alpha,int beta) {
        if(depth==0||game_ended(node)==true) return Evalstate_w(node) ;
        int ret_val ;
        int  best_val = -INF +1 ;
        int[] ret_node = node.clone();
        int[] temp_node = new int[64] ;

        if(check_castling_w(node,5)){
            ret_val=Kingside_castlingMax(node,depth,ret_node,alpha,beta);
//            // beta-cut  (return INF)
//            if(ret_val>=beta) return INF+1 ;

//            // node == ret_node ( 움직임이 없을 시 )
//            if(Arrays.equals(node,ret_node))
//            if(ret_val==-INF||ret_val==INF)

            // best_val,temp_node 갱신
            if(depth== AI_DEPTH){
                if(ret_val >= best_val ) {
                    best_val = ret_val ;
                    alpha = max(alpha,ret_val) ;
                    temp_node = ret_node.clone() ;
//                    //  random-arraylist reset
//                    cand.clear();
//                    t=ret_val;
//                    Count=0;
//                    cand.add(ret_node.clone());
                }
//                else if(ret_val == best_val ) {
//                    alpha = max(alpha,ret_val) ;
//                    best_val = ret_val ;
//                    cand.add(ret_node.clone());
//                    Count++;
//                }
            }
            else {
                if (ret_val >= best_val) {
                    alpha = max(alpha,ret_val) ;
                    best_val = ret_val;
                }
            }
        }
        if(check_castling_w(node,4)){
            ret_val=Queenside_castlingMax(node,depth,ret_node,alpha,beta);
//            // beta-cut  (return INF)
//            if(ret_val>=beta) return INF+1 ;

//            // node == ret_node ( 움직임이 없을 시 )
//            if(Arrays.equals(node,ret_node))
//            if(ret_val==-INF||ret_val==INF)

            // best_val,temp_node 갱신
            if(depth== AI_DEPTH){
                if(ret_val >= best_val ) {
                    best_val = ret_val ;
                    alpha = max(alpha,ret_val) ;
                    temp_node = ret_node.clone() ;
//                    //  random-arraylist reset
//                    cand.clear();
//                    t=ret_val;
//                    Count=0;
//                    cand.add(ret_node.clone());
                }
//                else if(ret_val == best_val ) {
//                    alpha = max(alpha,ret_val) ;
//                    best_val = ret_val ;
//                    cand.add(ret_node.clone());
//                    Count++;
//                }
            }
            else {
                if (ret_val >= best_val) {
                    alpha = max(alpha,ret_val) ;
                    best_val = ret_val;
                }
            }
        }
        for(int i=0;i<64;i++) {
            ret_node=node.clone();
            switch(node[i]) {
                //rook
                case 11: {
                    ret_val = RookMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                //knight
                case 12 : {
                    ret_val = KnightMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                case 13 : {
                    ret_val = BishopMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                case 14 : {
                    ret_val = QueenMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                case 15 : {
                    ret_val = KingMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                case 16 : {
                    ret_val = PawnMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                case 17 : {
                    ret_val = FPawnMax(node,i,depth,ret_node,alpha,beta) ;
                    break;
                }
                default : {
                    continue;
                }
            } // switch
            // beta-cut  (return INF)
            if(ret_val>=beta) return INF+1 ;

            // node == ret_node ( 움직임이 없을 시 )
            if(Arrays.equals(node,ret_node)) continue ;
            if(ret_val==-INF||ret_val==INF) continue;

            // best_val,temp_node 갱신
            if(depth== AI_DEPTH){
                if(ret_val >= best_val ) {
                    best_val = ret_val ;
                    alpha = max(alpha,ret_val) ;
                    temp_node = ret_node.clone() ;
//                    //  random-arraylist reset
//                    cand.clear();
//                    t=ret_val;
//                    Count=0;
//                    cand.add(ret_node.clone());
                }
//                else if(ret_val == best_val ) {
//                    alpha = max(alpha,ret_val) ;
//                    best_val = ret_val ;
//                    cand.add(ret_node.clone());
//                    Count++;
//                }
            }
            else {
                if (ret_val >= best_val) {
                    alpha = max(alpha,ret_val) ;
                    best_val = ret_val;
                }
            }

        } // for(i)
        // depth == 최대높이
        if(depth== AI_DEPTH) {
//            if (best_val == t&&Count>0) {
//                temp_node = cand.get((int)((random()*100) % Count)).clone();
//                // initialize
//                cand.clear() ; Count = 0; t =-INF ;
//            }
            for (int i = 0; i < 64; i++)
                FinalNode[i] = temp_node[i]; //깊이가 n일때 가장 좋은 node를 (전역변수)FinalNode로 저장.
        }
        return best_val ;
    }

    public int MinMove(int[] node, int depth,int alpha,int beta) {
        if(depth==0||game_ended(node)==true) return Evalstate_w(node) ;
        int ret_val ;
        int  best_val = INF -1  ;

        if(check_castling_b(node,5)){
            ret_val=Kingside_castlingMin(node,depth,alpha,beta);
//            // alpha-cut (return -INF)
//            if(ret_val <= alpha) return -INF-1;
//
//            // node == ret_node ( 움직임이 없을 시 )
//            if(ret_val==INF||ret_val==-INF)

            // best_val  갱신
            if(ret_val <= best_val ) {
                beta = min(beta,ret_val) ;
                best_val = ret_val ;
            }
        }
        if(check_castling_b(node,4)){
            ret_val=Queenside_castlingMin(node,depth,alpha,beta);
//            // alpha-cut (return -INF)
//            if(ret_val <= alpha) return -INF-1;
//
//            // node == ret_node ( 움직임이 없을 시 )
//            if(ret_val==INF||ret_val==-INF)

            // best_val  갱신
            if(ret_val <= best_val ) {
                beta = min(beta,ret_val) ;
                best_val = ret_val ;
            }
        }
        for(int i=0;i<64;i++) {

            switch(node[i]) {
                //rook
                case 1: {
                    ret_val = RookMin(node,i,depth,alpha,beta) ;
                    break;
                }
                //knight
                case 2 : {
                    ret_val = KnightMin(node,i,depth,alpha,beta) ;
                    break;
                }
                case 3 : {
                    ret_val = BishopMin(node,i,depth,alpha,beta) ;
                    break;
                }
                case 4 : {
                    ret_val = QueenMin(node,i,depth,alpha,beta) ;
                    break;
                }
                case 5 : {
                    ret_val = KingMin(node,i,depth,alpha,beta) ;
                    break;
                }
                case 6 : {
                    ret_val = PawnMin(node,i,depth,alpha,beta) ;
                    break;
                }
                case 7 : {
                    ret_val = FPawnMin(node,i,depth,alpha,beta) ;
                    break;
                }
                default:  {
                    continue ;
                }
            } // switch
            // alpha-cut (return -INF)
            if(ret_val <= alpha) return -INF-1;

            // node == ret_node ( 움직임이 없을 시 )
            if(ret_val==INF||ret_val==-INF) continue ;

            // best_val  갱신
            if(ret_val <= best_val ) {
                beta = min(beta,ret_val) ;
                best_val = ret_val ;
            }

        } // for(i)

        return best_val ;
    }

    public int Evalstate_w(int[] number) {// 현재 상태를 평가하는 평가함수 (일단 ai가 백 이라는 가정으로 작성)
        int value = 0;
        for (int i = 0; i < 64; i++) {
            if (number[i] == 1) value = value - 50; //룩
            else if (number[i] == 2) value = value - 30;//나이트
            else if (number[i] == 3) value = value - 30;//비숍
            else if (number[i] == 4) value = value - 90;//퀸
            else if (number[i] == 7 || number[i] == 6) {value = value - 10; /*if((number[i-7]!=16||number[i-7]!=17)||(number[i-9]!=16||number[i-9]!=17)) value=value-1;*/}//폰
            else if (number[i] == 5) value = value - 1000;
            else if (number[i] == 11) value = value + 50;
            else if (number[i] == 12) value = value + 30;
            else if (number[i] == 13) value = value + 30;
            else if (number[i] == 14) value = value + 90;
            else if (number[i] == 17 || number[i] == 16) {value = value + 10; /*if((number[i+7]!=6||number[i+7]!=7)||(number[i+9]!=6||number[i+9]!=7)) value=value+1;*/}
            else if (number[i] == 15) value = value + 1000;
        }
        return value;
    }


    public int RookMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val = -INF;


        int[] temp_node = node.clone();

        for (int a = spot + 8; a < 64; a = a + 8) {//하
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                    if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int a = spot - 8; a >= 0; a = a - 8) {//상
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) {//좌
            int a = spot - (b+1) ;
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
            int a = spot + (b+1) ;
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        // return
        if (best_val!=-INF)
        for(int i=0 ; i<64 ; i++) best_node[i] = temp_node[i] ;

        return best_val ;

    }
    public int KnightMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val = -INF ;


        int[] temp_node = node.clone();

        int a = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
        if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
        if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
        if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
        if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
        if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
        if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
        if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
        if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=11&&node[a]<=17)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
        return best_val ;
    }
    public int BishopMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val= -INF ;

        int[] temp_node =node.clone();

        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
            int a = spot + (9 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }

        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            int a = spot - (9 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
            int a = spot + (7 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 위
            int a = spot - (7 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
        return best_val ;
    }
    public int QueenMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val = -INF;

        int[] temp_node = node.clone();

        for (int a = spot + 8; a < 64; a = a + 8) {//하
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int a = spot - 8; a >= 0; a = a - 8) {//상
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) {//좌
            int a = spot - (b + 1);
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
            int a = spot + (b + 1);
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
            int a = spot + (9 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            int a = spot - (9 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
            int a= spot + (7 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
            int a = spot - (7 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 11 && node[a] <= 17)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MinMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
        return best_val ;
    }
    public int KingMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val= -INF ;

        int[] temp_node = node.clone();

        int a = spot + 1; //우
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)&&8 - spot % 8 - 1 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 1;  //좌
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)&&spot % 8 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot + 7; //좌대각 아래
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)&&spot % 8 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot + 8; //아래
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot + 9; // 우대각 아래
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)&&8 - spot % 8 - 1 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 7; //우대각 위
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)&&8 - spot % 8 - 1 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 8; //위
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 9; //좌대각위
        if (in_board(a)&&!(node[a]>=11&&node[a]<=17)&&spot % 8 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MinMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
        return best_val ;
    }
    public int PawnMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val= -INF ;

        int[] temp_node =node.clone();

        // 1칸 전진
            for (int a = spot + 8; a <= spot + 8; a = a + 8) {
                if(in_board(a)) {
                    if (node[a]==0) {
                        // GenerateMove   + Pawn이 진영 끝까지 갔을 경우 / else
                        if(a<=63&&a>=56) Node[a] = 14;
                        else Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MinMove(Node, depth - 1,alpha,beta);
//                        if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                        if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            int a= spot+9 ;//우 대각 아래에 상대방 말이 있을 때
             if (in_board(a)&&(node[a]>=1&&node[a]<=7)&& 8-(spot%8)>=2) {
                 // GenerateMove   + Pawn이 진영 끝까지 갔을 경우 / else
                 if(a<=63&&a>=56) Node[a] = 14;
                 else Node[a] = Node[spot];
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
//                 if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                 if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
                }
            a= spot+7;// 좌 대각 아래에 상대발 말이 있을 때
            if (in_board(a)&&(node[a]>=1&&node[a]<=7)&& spot%8>=1) {
                // GenerateMove   + Pawn이 진영 끝까지 갔을 경우 / else
                if(a<=63&&a>=56) Node[a] =14;
                else Node[a] = Node[spot];
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
//                if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
//            // 양파상 우측
//            a=spot+9 ;
//            if (8-(spot % 8) >= 2 && enpassant[spot + 1] == 1 && node[a] == 0) {
//                // GenerateMove
//                Node[a] = Node[spot] ;
//                Node[spot] = 0 ;
//                Node[spot+1] =0 ;
//                // MinMove
//                ret_val = MinMove(Node,depth-1,alpha,beta) ;
////                if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
////                if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                // 비교  ( temp_node,best_val 갱신)
//                if (ret_val >= best_val) {
//                    best_val = ret_val ;
//                    temp_node = Node.clone() ;
//                }
//                // GenerateMove Cancel
//                Node = node.clone() ;
//            }
//            // 양파상 좌측
//            a=spot+7;
//            if ( spot%8 >= 1 && enpassant[spot - 1] == 1 && node[a] == 0) {
//                // GenerateMove
//                Node[a] = Node[spot] ;
//                Node[spot] = 0 ;
//                Node[spot-1] = 0;
//                // MinMove
//                ret_val = MinMove(Node,depth-1,alpha,beta) ;
////                if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
////                if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                // 비교  ( temp_node,best_val 갱신)
//                if (ret_val >= best_val) {
//                    best_val = ret_val ;
//                    temp_node = Node.clone() ;
//                }
//                // GenerateMove Cancel
//                Node = node.clone() ;
//            }
        if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
        return best_val ;
    }
    public int FPawnMax(int[] node,int spot,int depth,int[] best_node,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = -INF  ;
        int ret_val= -INF ;

        int[] temp_node = node.clone();

        // 1칸/2칸 전진
            for (int a = spot + 8; a <= spot + 16; a = a + 8) {
                if(in_board(a)) {
                    if (node[a]==0) {
                        // GenerateMove
                        Node[a] = 16;
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MinMove(Node, depth - 1,alpha,beta);
//                        if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                        if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val >= best_val) {
                        best_val = ret_val;
                        temp_node = Node.clone();
                        alpha = max(alpha,ret_val) ;
                    }
                     if(ret_val >= beta) {
                        return best_val;
                    }
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            int a= spot+9 ;//우 대각 아래에 상대방 말이 있을 때
            if (in_board(a)&&(node[a]>=1&&node[a]<=7)&& 8-(spot%8)>=2) {
                // GenerateMove
                Node[a] = 16;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
//                if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
            a= spot+7;// 좌 대각 아래에 상대발 말이 있을 때
            if (in_board(a)&&(node[a]>=1&&node[a]<=7)&& spot%8>=1) {
                // GenerateMove
                Node[a] = 16;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MinMove(Node,depth-1,alpha,beta) ;
//                if(Node[a+7]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
//                if(Node[a+9]!=6) {ret_val=ret_val+1; if(Node[a-7]==6||Node[a-9]==6) ret_val=ret_val+1;}
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val >= best_val) {
                    best_val = ret_val ;
                    temp_node = Node.clone() ;
                    alpha = max(alpha,ret_val) ;
                }
                 if(ret_val >= beta) {
                        return best_val;
                    }
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
        return best_val ;
    }

    public int RookMin(int[] node,int spot,int depth,int alpha,int beta) {
            int[] Node=node.clone();  //
            int  best_val = INF  ;
            int ret_val = INF ;

            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            for (int a = spot - 8; a >= 0; a = a - 8) {//상
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            for (int b = 0; b < spot % 8; b++) {//좌
                int a = spot - (b+1) ;
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
                int a = spot + (b+1) ;
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            // return
            return best_val ;
    }
    public int KnightMin(int[] node,int spot,int depth,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = INF  ;
        int ret_val = INF;

        int a = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
        if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
        if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
        if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
        if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
        if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
        if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
        if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        a = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
        if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
        {
            if (!(node[a]>=1&&node[a]<=7)) {
                // GenerateMove
                Node[a] = Node[spot] ;
                Node[spot] = 0 ;
                // MinMove
                ret_val = MaxMove(Node,depth-1,alpha,beta) ;
                // 비교  ( temp_node,best_val 갱신)
                if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
                // GenerateMove Cancel
                Node = node.clone() ;
            }
        }
        return best_val ;
    }
    public int BishopMin(int[] node,int spot,int depth,int alpha,int beta) {

            int[] Node=node.clone();  //
            int  best_val = INF  ;
            int ret_val = INF;

            for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
                int a = spot + (9 * (b + 1));
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }

            for (int b = 0; b < spot % 8; b++) { //좌 대각 위
                int a = spot - (9 * (b + 1));
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
                int a = spot + (7 * (b + 1));
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 위
                int a = spot - (7 * (b + 1));
                if(in_board(a)) {
                    if (!(node[a] >= 1 && node[a] <= 7)) {
                        // GenerateMove
                        Node[a] = Node[spot];
                        Node[spot] = 0;
                        // MinMove
                        ret_val = MaxMove(Node, depth - 1,alpha,beta);
                        // 비교  ( temp_node,best_val 갱신)
                        if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                        // GenerateMove Cancel
                        Node = node.clone();
                    }
                    //  말이 있을경우
                    if (node[a] != 0) break;
                }
            }
            return best_val ;

    }
    public int QueenMin(int[] node,int spot,int depth,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = INF  ;
        int ret_val = INF;

        for (int a = spot + 8; a < 64; a = a + 8) {//하
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int a = spot - 8; a >= 0; a = a - 8) {//상
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) {//좌
            int a = spot - (b + 1);
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우
            int a = spot + (b + 1);
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
            int a = spot + (9 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 위
            int a = spot - (9 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < spot % 8; b++) { //좌 대각 아래
            int a= spot + (7 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        for (int b = 0; b < 8 - ((spot % 8) + 1); b++) {//우 대각 아래
            int a = spot - (7 * (b + 1));
            if(in_board(a)) {
                if (!(node[a] >= 1 && node[a] <= 7)) {
                    // GenerateMove
                    Node[a] = Node[spot];
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        return best_val ;
    }
    public int KingMin(int[] node,int spot,int depth,int alpha,int beta) {
        int[] Node=node.clone();  //
        int  best_val = INF  ;
        int ret_val= INF ;

        int a = spot + 1; //우
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)&&8 - spot % 8 - 1 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 1;  //좌
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)&&spot % 8 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot + 7; //좌대각 아래
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)&&spot % 8 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot + 8; //아래
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot + 9; // 우대각 아래
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)&&8 - spot % 8 - 1 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val >= best_val) {
                best_val = ret_val ;
            }
            if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 7; //우대각 위
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)&&8 - spot % 8 - 1 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 8; //위
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a = spot - 9; //좌대각위
        if (in_board(a)&&!(node[a]>=1&&node[a]<=7)&&spot % 8 >= 1) {
            // GenerateMove
            Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        return best_val ;
    }
    public int PawnMin(int[] node,int spot,int depth,int alpha,int beta){
        int[] Node=node.clone();  //
        int  best_val = INF  ;
        int ret_val= INF ;

        // 1칸 전진
        for (int a = spot - 8; a >= spot - 8; a = a - 8) {
            if(in_board(a)) {
                if (node[a]==0) {
                    // GenerateMove   + Pawn이 진영 끝까지 갔을 경우 / else
                    if(a<=63&&a>=56) Node[a] = 4;
                    else Node[a] = Node[spot] ;
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
//                    if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//                    if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        int a= spot-7 ;//우 대각 위에 상대방 말이 있을 때
        if (in_board(a)&&(node[a]>=11&&node[a]<=17)&& 8-(spot%8)>=2) {
            // GenerateMove   + Pawn이 진영 끝까지 갔을 경우 / else
            if(a<=63&&a>=56) Node[a] = 4;
            else Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
//            if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//            if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a= spot-9;// 좌 대각 위에 상대발 말이 있을 때
        if (in_board(a)&&(node[a]>=11&&node[a]<=17)&& spot%8>=1) {
            // GenerateMove   + Pawn이 진영 끝까지 갔을 경우 / else
            if(a<=63&&a>=56) Node[a] = 4;
            else Node[a] = Node[spot] ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
//            if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//            if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
//        // 양파상 우측
//        a=spot-7 ;
//        if (8-(spot % 8) >= 2 && enpassant[spot + 1] == 1 && node[a] == 0) {
//            // GenerateMove
//            Node[a] = Node[spot] ;
//            Node[spot] = 0 ;
//            Node[spot+1] =0 ;
//            // MinMove
//            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
////            if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
////            if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//            // 비교  ( temp_node,best_val 갱신)
//            if (ret_val <= best_val) {
//                best_val = ret_val ;
//            }
//            // GenerateMove Cancel
//            Node = node.clone() ;
//        }
//        // 양파상 좌측
//        a=spot-9;
//        if ( spot%8 >= 1 && enpassant[spot - 1] == 1 && node[a] == 0) {
//            // GenerateMove
//            Node[a] = Node[spot] ;
//            Node[spot] = 0 ;
//            Node[spot-1] = 0;
//            // MinMove
//            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
////            if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
////            if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//            // 비교  ( temp_node,best_val 갱신)
//            if (ret_val <= best_val) {
//                best_val = ret_val ;
//            }
//            // GenerateMove Cancel
//            Node = node.clone() ;
//        }
        return best_val ;
    }
    public int FPawnMin(int[] node,int spot,int depth,int alpha,int beta){
        int[] Node=node.clone();
        int  best_val = INF  ;
        int ret_val = INF;

        // 1칸/2칸 전진
        for (int a = spot - 8; a >= spot - 16; a = a - 8) {
            if(in_board(a)) {
                if (node[a]==0) {
                    // GenerateMove
                    Node[a] = 6;
                    Node[spot] = 0;
                    // MinMove
                    ret_val = MaxMove(Node, depth - 1,alpha,beta);
//                    if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//                    if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
                    // 비교  ( temp_node,best_val 갱신)
                    if (ret_val <= best_val) {
                            best_val = ret_val;
                            beta=min(beta,ret_val) ;
                        }
                        if(ret_val <= alpha) return best_val;
                    // GenerateMove Cancel
                    Node = node.clone();
                }
                //  말이 있을경우
                if (node[a] != 0) break;
            }
        }
        int a= spot-7 ;//우 대각 위에 상대방 말이 있을 때
        if (in_board(a)&&(node[a]>=11&&node[a]<=17)&& 8-(spot%8)>=2) {
            // GenerateMove
            Node[a] = 6 ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
//            if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//            if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        a= spot-9;// 좌 대각 아래에 상대발 말이 있을 때
        if (in_board(a)&&(node[a]>=11&&node[a]<=17)&& spot%8>=1) {
            // GenerateMove
            Node[a] = 6 ;
            Node[spot] = 0 ;
            // MinMove
            ret_val = MaxMove(Node,depth-1,alpha,beta) ;
//            if(Node[a-7]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
//            if(Node[a-9]!=16) {ret_val=ret_val-1; if(Node[a+7]==16||Node[a+9]==16) ret_val=ret_val-1;}
            // 비교  ( temp_node,best_val 갱신)
            if (ret_val <= best_val) {
                    best_val = ret_val ;
                    beta = min(beta,ret_val) ;
                }
                if(ret_val <= alpha) return best_val;
            // GenerateMove Cancel
            Node = node.clone() ;
        }
        return best_val ;
    }

     public int Kingside_castlingMax(int[] node,int depth,int[] best_node,int alpha,int beta){
         int[] Node=node.clone();  //
         int  best_val = -INF  ;
         int ret_val= -INF ;

         int[] temp_node = node.clone();
         // GenerateMove
         Node[1]=Node[3];
         Node[2]=Node[0];
         Node[3]=0;
         Node[0]=0;
         // MinMove
         ret_val = MinMove(Node,depth-1,alpha,beta) ;
         // 비교  ( temp_node,best_val 갱신)
         if (ret_val >= best_val) {
             best_val = ret_val ;
             temp_node = Node.clone() ;
             alpha = max(alpha,ret_val) ;
         }
         if(ret_val >= beta) {
             return best_val;
         }
         if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
         return best_val ;
     }
     public int Queenside_castlingMax(int[] node,int depth,int[] best_node,int alpha,int beta){
         int[] Node=node.clone();  //
         int  best_val = -INF  ;
         int ret_val= -INF ;

         int[] temp_node = node.clone();
         // GenerateMove
         Node[6]=Node[3];
         Node[5]=Node[7];
         Node[3]=0;
         Node[7]=0;
         // MinMove
         ret_val = MinMove(Node,depth-1,alpha,beta) ;
         // 비교  ( temp_node,best_val 갱신)
         if (ret_val >= best_val) {
             best_val = ret_val ;
             temp_node = Node.clone() ;
             alpha = max(alpha,ret_val) ;
         }
         if(ret_val >= beta) {
             return best_val;
         }
         if(best_val!=-INF) for(int i=0;i<64;i++) best_node[i] = temp_node[i] ;
         return best_val ;
     }
     public int Kingside_castlingMin(int[] node,int depth,int alpha,int beta){
         int[] Node=node.clone();
         int  best_val = INF  ;
         int ret_val = INF;
         // GenerateMove
         Node[57]=Node[59];
         Node[58]=Node[56];
         Node[59]=0;
         Node[56]=0;
         // MinMove
         ret_val = MaxMove(Node,depth-1,alpha,beta) ;
         // 비교  ( temp_node,best_val 갱신)
         if (ret_val <= best_val) {
             best_val = ret_val ;
             beta = min(beta,ret_val) ;
         }
         if(ret_val <= alpha) return best_val;
         return best_val ;
     }
     public int Queenside_castlingMin(int[] node,int depth,int alpha,int beta){
         int[] Node=node.clone();
         int  best_val = INF  ;
         int ret_val = INF;
         // GenerateMove
         Node[62]=Node[59];
         Node[61]=Node[63];
         Node[59]=0;
         Node[63]=0;
         // MinMove
         ret_val = MaxMove(Node,depth-1,alpha,beta) ;
         // 비교  ( temp_node,best_val 갱신)
         if (ret_val <= best_val) {
             best_val = ret_val ;
             beta = min(beta,ret_val) ;
         }
         if(ret_val <= alpha) return best_val;
         return best_val ;
     }


    public boolean game_ended(int[] P_Node){
        int a=0;
        for(int i=0;i<64;i++) {
            if(P_Node[i]==5||P_Node[i]==15) a++;
        }
        if(a<2) return true;
        else return false;
    }


}