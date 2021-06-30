package com.game.dilemma;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.GestureOverlayView;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

public class Riverside_main extends AppCompatActivity {
    //객체 선언
    View view1, view2;
    ScrollView scrollView1;
    TextView textView1;
    GestureDetector detector; //무슨 제스쳐를 했는지 감지
    private View     decorView;
    private int    uiOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riverside_main);
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility( uiOption );

        final VideoView videoView=(VideoView)findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://com.game.dilemma/raw/a0_video");
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 준비 완료되면 비디오 재생
                mp.start();
                mp.setLooping(true);
            }
        });
    }
        //객체 초기화
//        view1 = findViewById(R.id.view1);
//        view2 = findViewById(R.id.view2);
//        scrollView1 = findViewById(R.id.scrollView1);
//        textView1 = findViewById(R.id.textView1);

        //터치를 했을때 작동하는 메서드
//        view1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                float curX = event.getX();  //눌린 곳의 X좌표
//                float curY = event.getY();  //눌린 곳의 Y좌표
//
//                if(action == event.ACTION_DOWN) {   //처음 눌렸을 때
//                    printString("손가락 눌림 : " + curX + ", " + curY);
//                } else if(action == event.ACTION_MOVE) {    //누르고 움직였을 때
//                    printString("손가락 움직임 : " + curX + ", " + curY);
//                } else if(action == event.ACTION_UP) {    //누른걸 뗐을 때
//                    printString("손가락 뗌 : " + curX + ", " + curY);
//                }
//                return true;
//            }
//        });

//        view2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                detector.onTouchEvent(event);
//                return true;
//            }
//        });
//        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
//            //화면이 눌렸을 때
//            @Override
//            public boolean onDown(MotionEvent e) {
//                printString("onDown() 호출됨");
//                return true;
//            }
//
//            //화면이 눌렸다 떼어지는 경우
//            @Override
//            public void onShowPress(MotionEvent e) {
//                printString("onShowPress() 호출됨");
//            }
//
//            //화면이 한 손가락으로 눌렸다 떼어지는 경우
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                printString("onSingleTapUp() 호출됨");
//                return true;
//            }
//
//            //화면이 눌린채 일정한 속도와 방향으로 움직였다 떼어지는 경우
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                printString("onScroll() 호출됨 => " + distanceX + ", " + distanceY);
//                return false;
//            }
//
//            //화면을 손가락으로 오랫동안 눌렀을 경우
//            @Override
//            public void onLongPress(MotionEvent e) {
//                printString("onLongPress() 호출됨");
//            }
//
//            //화면이 눌린채 손가락이 가속해서 움직였다 떼어지는 경우
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                printString("onFling() 호출됨 => " + velocityX + ", " + velocityY);
//                return true;
//            }
//        });
//    }
//
//    private void printString(String s) {
//        //좌표 출력
//        textView1.append(s + "\n"); //한 줄씩 추가
//
//        //자동으로 마지막 줄로 스크롤 내림
//        scrollView1.fullScroll(View.FOCUS_DOWN);
//    }
//
//    //키가 눌렸을때 작동
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            printString("시스템에서 [Back] 버튼이 눌림");
//            return true;
//        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//            printString("시스템에서 [VOLUME UP] 버튼이 눌림");
//            return true;
//        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            printString("시스템에서 [VOLUME DOWN] 버튼이 눌림");
//            return true;
//        } else if(keyCode == KeyEvent.KEYCODE_ENTER) {
//            printString("시스템에서 [Enter] 키가 눌림");
//            return true;
//        }
//        return false;
//    }
}