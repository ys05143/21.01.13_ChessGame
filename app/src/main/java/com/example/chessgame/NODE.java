package com.example.chessgame;

import org.w3c.dom.Node;

import java.util.Arrays;

public class NODE {
    int [] board; int score; int output;

    boolean in_board(int index) {
        if(index>=0&&index<64) return true;
        else return false ;
    }

    public int MaxMove(int[] node, int depth,int al,int be) {
        int[] P_Node = node.clone(); //매개변수로 받은 배열 복사
        int best_val=-10000;
        int [] result = {best_val,al,be};
        int alpha=result[1]; int beta=result[2];
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int i = 0; i < 64; i++) {
                if (P_Node[i] == 17) {
                    result = f_pawn_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 16) {
                    result = pawn_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 11) {
                    result = rook_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 12) {
                    result = knight_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 13) {
                    result = bishop_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 14) {
                    result = queen_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 15) {
                    result = king_generatemove_max_w(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] >= best_val) {
                        best_val = result[0];
                    }
//                    if(result[1]<best_val){
//                        result[1]=best_val;
//                        alpha=result[1]
//                    }
//                    if(beta<=alpha) return best_val;
                }
            }
            return best_val;
        }
    }

    public int MinMove(int[] node, int depth,int al,int be) {
        int[] P_Node = node.clone(); //매개변수로 받은 배열 복사
        int best_val=10000;
        int [] result = {best_val,al,be};
        int alpha=result[1]; int beta=result[2];
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int i = 0; i < 64; i++) {
                if (P_Node[i] == 7) {
                    result = f_pawn_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 6) {
                    result = pawn_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 1) {
                    result = rook_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 2) {
                    result = knight_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 3) {
                    result = bishop_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 4) {
                    result = queen_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
                else if (P_Node[i] == 5) {
                    result = king_generatemove_min_b(node.clone(), i, depth,alpha,beta,result[0]);
                    if (result[0] <= best_val) {
                        best_val = result[0];
                    }
//                    if(result[2]>best_val){
//                        result[2]=best_val;
//                        beta=result[2];
//                    }
//                    if(beta<=alpha) return best_val;
                }
            }
            return best_val;
        }
    }

    public int Evalstate_w(int[] number) {// 현재 상태를 평가하는 평가함수 (일단 ai가 백 이라는 가정으로 작성)
        int value = 0;
        for (int i = 0; i < 64; i++) {
            if (number[i] == 1) value = value - 5; //룩
            else if (number[i] == 2) value = value - 3;//나이트
            else if (number[i] == 3) value = value - 3;//비숍
            else if (number[i] == 4) value = value - 9;//퀸
            else if (number[i] == 7 || number[i] == 6 ) value = value - 1;//폰
            else if (number[i]==5) value=value-1000;
            else if (number[i] == 11) value = value + 5;
            else if (number[i] == 12) value = value + 3;
            else if (number[i] == 13) value = value + 3;
            else if (number[i] == 14) value = value + 9;
            else if (number[i] == 17 || number[i] == 16 ) value = value + 1;
            else if (number[i]==15) value=value+1000;
        }
        return value;
    }

    public int[] rook_generatemove_max_w(int[] P_Node, int spot, int depth, int al, int be, int best ) {
        int[] node = P_Node.clone();
        int [] best_val= {best, al, be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                         //깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            return best_val;
        }
    }
    public int[] knight_generatemove_max_w(int []P_Node,int spot,int depth,int al,int be, int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha =best_val[1]; int beta = best_val[2];
        int result = 0;
        if ((depth == 0) || (game_ended(P_Node) == true)) return best_val;
        else {
            int a;
            a = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
            if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
            if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
            if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
            if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
            if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
            if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
            if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
            if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            return best_val;
        }
    }
    public int[] bishop_generatemove_max_w(int[] P_Node, int spot, int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
                int t = spot + (9 * (a + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            return best_val;
        }
    }
    public int[] queen_generatemove_max_w(int []P_Node,int spot,int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result = 0;
        if ((depth == 0) || (game_ended(P_Node) == true)) return best_val;
        else {
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if (!(P_Node[a] >= 11 && P_Node[a] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int a = 0; a < spot % 8; a++) {//좌
                int t = spot - (a + 1);
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우
                int t = spot + (a + 1);
                if (in_board(t)) {
                    if (!(P_Node[t] >= 11 && P_Node[t] <= 17)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MinMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            return best_val;
        }
    }
    public int[] king_generatemove_max_w(int []P_Node,int spot,int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result = 0;
        if ((depth == 0) || (game_ended(P_Node) == true)) return best_val;
        else {
            int k = spot + 1; //우
            if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 1;  //좌
            if (in_board(k) && spot % 8 >= 1) {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot + 7; //좌대각 아래
            if (in_board(k) && spot % 8 >= 1) {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot + 8; //아래
            if (in_board(k))//아래
            {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot + 9;
            if (in_board(k) && 8 - spot % 8 - 1 >= 1)//우대각 아래
            {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 7; //우대각 위
            if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 8; //위
            if (in_board(k)) {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 9; //좌대각위
            if (in_board(k) && spot % 8 >= 1) {
                if (!(P_Node[k] >= 11 && P_Node[k] <= 17)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MinMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            return best_val;
        }
    }
    public int[] pawn_generatemove_max_w(int []P_Node, int spot, int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = spot - 8; a >= spot - 8; a = a - 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) {
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
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                    // 깊이 0까지 탐색
                    result=MinMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
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
                    // 깊이 0까지 탐색
                    result=MinMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            // 양파상
//            if (spot % 8 >= 1 && enpassant[spot - 1] == 1 && P_Node[spot - 9] == 0){
//                // 위치 바꿔주기
//                node[spot-9] = node[spot];
//                node[spot] = 0;
//                node[spot-1]=0;
//                // 한 칸 옮겼을때 상태 저장
//                temp_node = node.clone();
//                // 깊이 0까지 탐색
//                move = MinMove(node, depth - 1,alpha,beta).clone();
//                int result=Evalstate_w(move);
//                // 깊이 0까지에서의 value >= 현재까지의 best_val
//                if (result >= best_val) {
//                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
//                    best_val = result;
//
//                }
//
//                if(result<=alpha) return best_move.clone();
//                else if(result<beta) beta=result;
//                node = P_Node.clone(); // 원상태로
//            }
//            if (8 - spot % 8 - 1 >= 1 && enpassant[spot + 1] == 1 && P_Node[spot - 7] == 0){
//                // 위치 바꿔주기
//                node[spot-7] = node[spot];
//                node[spot] = 0;
//                node[spot+1]=0;
//                // 한 칸 옮겼을때 상태 저장
//                temp_node = node.clone();
//                // 깊이 0까지 탐색
//                move = MinMove(node, depth - 1,alpha,beta).clone();
//                int result=Evalstate_w(move);
//                // 깊이 0까지에서의 value >= 현재까지의 best_val
//                if (result >= best_val) {
//                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
//                    best_val = result;
//
//                }
//
//                if(result<=alpha) return best_move.clone();
//                else if(result<beta) beta=result;
//                node = P_Node.clone(); // 원상태로
//            }

        }
        return best_val;
    }
    public int[] f_pawn_generatemove_max_w(int []P_Node, int spot, int depth,int al,int be,int best){
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = spot - 8; a >= spot - 16; a = a - 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) {
                        node[a] = 16;
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result=MinMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result >= best_val[0]) {
                            best_val[0] = result;
                        }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            if (in_board(spot - 9) && (P_Node[spot-9] >= 1 && P_Node[spot-9] <= 7)) //좌 대각 위에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1){
                    // 위치 바꿔주기
                    node[spot-9] = 16;
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result=MinMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            if (in_board(spot - 7) && (P_Node[spot-7] >= 1 && P_Node[spot-7] <= 7)) //우 대각 위에 상대방 말이 있을 때
            {
                if (8 - spot % 8 - 1 >= 1){
                    // 위치 바꿔주기
                    node[spot-7] = 16;
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result=MinMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result >= best_val[0]) {
                        best_val[0] = result;
                    }
//                       if(alpha<best_val[0]){
//                            alpha=best_val[0];
//                            best_val[1]=alpha;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            return best_val;
        }
    }


    public int[] rook_generatemove_min_b(int[] P_Node, int spot, int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            return best_val;
        }
    }
    public int[] knight_generatemove_min_b(int []P_Node,int spot,int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result = 0;
        if ((depth == 0) || (game_ended(P_Node) == true)) return best_val;
        else {
            int a;
            a = spot + (8 * 2) + 1;//우 대각 아래(2) down-2 right-1
            if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot + (8 * 2) - 1; //좌 대각 아래(2) down-2 left-1
            if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 2) + 1; //우 대각 위(2) up-2 right-1
            if (in_board(a) && 8 - spot % 8 - 1 >= 1)  //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 2) - 1; //좌 대각 위(2) up-2 left-1
            if (in_board(a) && spot % 8 >= 1) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot + (8 * 1) + 2; //우 대각 아래(1) right-2 down-1
            if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot + (8 * 1) - 2;//좌 대각 아래(1) left-2 down-1
            if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 1) + 2; //우 대각 위(1) right-2 up -1
            if (in_board(a) && 8 - spot % 8 - 1 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            a = spot - (8 * 1) - 2; //좌 대각 위(1) left-2 up - 1
            if (in_board(a) && spot % 8 >= 2) //끝쪽에 있을때 넘어가기 방지
            {
                if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) {
                    // 위치 바꿔주기
                    node[a] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            return best_val;
        }
    }
    public int[] bishop_generatemove_min_b(int[] P_Node, int spot, int depth,int al,int be,int best){
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우 대각 아래
                int t = spot + (9 * (a + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //black이 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            return best_val;
        }
    }
    public int[] queen_generatemove_min_b(int []P_Node,int spot,int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result = 0;
        if ((depth == 0) || (game_ended(P_Node) == true)) return best_val;
        else {
            for (int a = spot + 8; a < 64; a = a + 8) {//하
                if (in_board(a)) {
                    if (!(P_Node[a] >= 1 && P_Node[a] <= 7)) { //white가 아니면
                        // 위치 바꿔주기
                        node[a] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[a] != 0) break;
                }
            }
            for (int a = 0; a < spot % 8; a++) {//좌
                int t = spot - (a + 1);
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int a = 0; a < 8 - ((spot % 8) + 1); a++) {//우
                int t = spot + (a + 1);
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            for (int c = 0; c < spot % 8; c++) { //좌 대각 아래
                int t = spot + (7 * (c + 1));
                if (in_board(t)) {
                    if (!(P_Node[t] >= 1 && P_Node[t] <= 7)) { //white가 아니면
                        // 위치 바꿔주기
                        node[t] = node[spot];
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                        // 깊이 0까지 탐색
                        result = MaxMove(node, depth - 1, alpha, beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
                    }
                    if (P_Node[t] != 0) break;
                }
            }
            return best_val;
        }
    }
    public int[] king_generatemove_min_b(int []P_Node,int spot,int depth,int al,int be,int best) {
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result = 0;
        if ((depth == 0) || (game_ended(P_Node) == true)) return best_val;
        else {
            int k = spot + 1; //우
            if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    node[k] = node[spot];
                    // 위치 바꿔주기
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 1;  //좌
            if (in_board(k) && spot % 8 >= 1) {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot + 7; //좌대각 아래
            if (in_board(k) && spot % 8 >= 1) {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot + 8; //아래
            if (in_board(k))//아래
            {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot + 9;
            if (in_board(k) && 8 - spot % 8 - 1 >= 1)//우대각 아래
            {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 7; //우대각 위
            if (in_board(k) && 8 - spot % 8 - 1 >= 1) {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 8; //위
            if (in_board(k)) {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            k = spot - 9; //좌대각위
            if (in_board(k) && spot % 8 >= 1) {
                if (!(P_Node[k] >= 1 && P_Node[k] <= 7)) {
                    // 위치 바꿔주기
                    node[k] = node[spot];
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result = MaxMove(node, depth - 1, alpha, beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            return best_val;
        }
    }
    public int[] pawn_generatemove_min_b(int []P_Node, int spot, int depth,int al,int be,int best){
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for(int a = spot + 8; a <= spot + 8; a = a + 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) {
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
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                    // 깊이 0까지 탐색
                    result=MaxMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }

            if (in_board(spot + 7) && (P_Node[spot+7] >= 11 && P_Node[spot+7] <= 17)) //좌 대각 아래에 상대방 말이 있을 때
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
                    // 깊이 0까지 탐색
                    result=MaxMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            // 양파상
//            if (spot % 8 >= 1 && enpassant[spot + 1] == 1 && P_Node[spot + 9] == 0){
//                // 위치 바꿔주기
//                node[spot+9] = node[spot];
//                node[spot] = 0;
//                node[spot+1]=0;
//                // 한 칸 옮겼을때 상태 저장
//                temp_node = node.clone();
//                // 깊이 0까지 탐색
//                move = MinMove(node, depth - 1,alpha,beta).clone();
//                int result=Evalstate_w(move);
//                // 깊이 0까지에서의 value >= 현재까지의 best_val
//                if (result >= best_val) {
//                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
//                    best_val = result;
//
//                }
//
//                if(result>=beta) return best_move.clone();
//                else if(result>alpha) alpha=result;
//                node = P_Node.clone(); // 원상태로
//            }
//            if (8 - spot % 8 - 1 >= 1 && enpassant[spot - 1] == 1 && P_Node[spot + 7] == 0){
//                // 위치 바꿔주기
//                node[spot+7] = node[spot];
//                node[spot] = 0;
//                node[spot-1]=0;
//                // 한 칸 옮겼을때 상태 저장
//                temp_node = node.clone();
//                // 깊이 0까지 탐색
//                move = MinMove(node, depth - 1,alpha,beta).clone();
//                int result=Evalstate_w(move);
//                // 깊이 0까지에서의 value >= 현재까지의 best_val
//                if (result >= best_val) {
//                    best_move = temp_node.clone(); //  best_move 에는 한칸 옮겼을때의 temp_node 저장
//                    best_val = result;
//
//                }
//
//                if(result>=beta) return best_move.clone();
//                else if(result>alpha) alpha=result;
//                node = P_Node.clone(); // 원상태로
//            }
        }
        return best_val;
    }
    public int[] f_pawn_generatemove_min_b(int []P_Node, int spot, int depth,int al,int be, int best){
        int[] node = P_Node.clone();
        int []best_val = {best,al,be};
        int alpha=best_val[1]; int beta=best_val[2];
        int result=0;
        if ((depth == 0)||(game_ended(P_Node)==true)) return best_val;
        else {
            for (int a = spot + 8; a <= spot + 16; a = a + 8) {
                if (in_board(a)) {
                    if (P_Node[a]==0) {
                        node[a] = 6;
                        node[spot] = 0;
                        // 깊이 0까지 탐색
                        result=MaxMove(node, depth - 1,alpha,beta);
                        // 깊이 0까지에서의 value >= 현재까지의 best_val
                        if (result <= best_val[0]) {
                            best_val[0] = result;
                        }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                        node=P_Node.clone();
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
                    // 깊이 0까지 탐색
                    result=MaxMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            if (in_board(spot + 7) && (P_Node[spot+7] >= 11 && P_Node[spot+7] <= 17)) //좌 대각 아래에 상대방 말이 있을 때
            {
                if (spot % 8 >= 1){
                    // 위치 바꿔주기
                    node[spot+7] = 6;
                    node[spot] = 0;
                    // 깊이 0까지 탐색
                    result=MaxMove(node, depth - 1,alpha,beta);
                    // 깊이 0까지에서의 value >= 현재까지의 best_val
                    if (result <= best_val[0]) {
                        best_val[0] = result;
                    }
//                        if(beta>best_val){
//                            beta=best_val;
//                            best_val[2]=beta;
//                        }
//                        if(beta<=alpha) return best_val;
                    node=P_Node.clone();
                }
            }
            return best_val;
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
    //생성자
    public NODE(int [] a){
        this.board=a.clone();
        this.score=Evalstate_w(board);
        output=MaxMove(board, 3,-10000,10000);
    }


}
