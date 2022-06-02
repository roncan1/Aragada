package com.portpolio.aragada;

public class Pen {
    public static final int STATE_START = 0;        //펜의 상태(움직임 시작)
    public static final int STATE_MOVE = 1;         //펜의 상태(움직이는 중)
    float x, y;                                     //펜의 좌표
    int moveStatus;                                 //현재 움직임 여부
    int color;                                      //펜 색
    int size;                                       //펜 두께

    public Pen(float x, float y, int moveStatus, int color, int size) {
        this.x = x;
        this.y = y;
        this.moveStatus = moveStatus;
        this.color = color;
        this.size = size;
    }

    public boolean isMove() {
        return moveStatus == STATE_MOVE;
    }
}
