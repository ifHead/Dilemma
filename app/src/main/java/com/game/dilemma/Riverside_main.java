package com.game.dilemma;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.GestureOverlayView;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.transition.Fade;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.EventListener;

import static java.lang.Thread.sleep;import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;import android.media.AudioManager;import android.media.SoundPool;

public class Riverside_main extends AppCompatActivity {
    //객체 선언
    boolean k = true;
    View touchArea;
    ScrollView scrollView1;
    TextView textView1;
    ImageView firstThought;
    SoundPool a1_sound;
    int a1_ID;
    SoundPool a2_sound;
    int a2_ID;
    SoundPool a3_sound;
    int a3_ID;
    SoundPool a6_sound;
    int a4_ID;
    AudioManager am;
    GestureDetector detector; //무슨 제스쳐를 했는지 감지
    Button ji_letter_btn;
    Button jung_letter_btn;
    boolean is_ji_prssd = false;
    boolean is_jung_prssd = false;

    private View decorView;
    private int uiOption;
    float raindrop_start;
    boolean isStraight = false;

    boolean get_isStraight() {
        return isStraight;
    }

    void set_isStraight(boolean val) {
        isStraight = val;
    }

    boolean videoStart = false;
    boolean is_first_video_start_request = true;
    boolean is_first_a1_audio_request = true;
    boolean is_first_a2_audio_request = true;
    boolean is_first_a3_audio_request = true;
    boolean is_first_a6_audio_request = true;

    int touch_cnt = 0;

    boolean[] narr_end = {false, false, false};
    int narr_1_alpha = 0;
    boolean narr_1_flag = true;
    int narr_1_time = 0;
    void narr_1_fadeInOut() {
        ImageView firstThought = (ImageView) findViewById(R.id.firstThought);
        Handler handler_fading = new Handler();
        if(narr_1_time==0){
            firstThought.setImageAlpha(0);
            MySoundPlayer.play(MySoundPlayer.A1_SOUND);
        }
        firstThought.setVisibility(View.VISIBLE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (narr_1_flag) {
                    try {
                        handler_fading.post(new Runnable() {
                            @Override
                            public void run() {
                                narr_1_time++;
                                if (narr_1_time > 300) {
                                    narr_1_alpha -= 2;
                                    if (narr_1_alpha < 0) {
                                        narr_1_alpha = 0;
                                        narr_1_flag = false;
                                        narr_end[0] = true;
                                    }
                                } else {
                                    narr_1_alpha += 2;
                                    if (narr_1_alpha > 255) {
                                        narr_1_alpha = 255;
                                    }
                                }
                                firstThought.setImageAlpha(narr_1_alpha);
                            }
                        });
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    int narr_2_alpha = 0;
    boolean narr_2_flag = true;
    int narr_2_time = 0;
    void narr_2_fadeInOut() {
        ImageView secondThought = (ImageView) findViewById(R.id.secondThought);
        Handler handler_fading2 = new Handler();
        if(narr_2_time==0){
            secondThought.setImageAlpha(0);
            MySoundPlayer.play(MySoundPlayer.A2_SOUND);
        }
        secondThought.setVisibility(View.VISIBLE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (narr_2_flag) {
                    try {
                        handler_fading2.post(new Runnable() {
                            @Override
                            public void run() {
                                narr_2_time++;
                                if (narr_2_time > 300) {
                                    narr_2_alpha -= 2;
                                    if (narr_2_alpha < 0) {
                                        narr_2_alpha = 0;
                                        narr_2_flag = false;
                                        narr_end[1] = true;
                                    }
                                } else {
                                    narr_2_alpha += 2;
                                    if (narr_2_alpha > 255) {
                                        narr_2_alpha = 255;
                                    }
                                }
                                secondThought.setImageAlpha(narr_2_alpha);
                            }
                        });
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    int narr_3_alpha = 0;
    boolean narr_3_flag = true;
    int narr_3_time = 0;
    //여기에 A4 '중' '지' 동시터치 체크함
    void narr_3_fadeInOut() {
        ImageView thirdThought = (ImageView) findViewById(R.id.thirdThought);
        Handler handler_fading3 = new Handler();
        if(narr_3_time==0){
            thirdThought.setImageAlpha(0);
            MySoundPlayer.play(MySoundPlayer.A3_SOUND);
        }
        thirdThought.setVisibility(View.VISIBLE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (narr_3_flag) {
                    try {
                        handler_fading3.post(new Runnable() {
                            @Override
                            public void run() {
                                narr_3_time++;

                                //A4 '중' '지' 동시터치 체크
                                if(is_ji_prssd && is_jung_prssd && k){
                                    MySoundPlayer.play(MySoundPlayer.A3_SOUND);
                                    k = false;
                                }
                                //동시터치가 아니면 각 버튼과 연결된 변수에 false 할당
                                is_jung_prssd = false;
                                is_ji_prssd = false;

                                if (narr_3_time > 300) {
//                                    narr_3_alpha -= 2;
//                                    if (narr_3_alpha < 0) {
//                                        narr_3_alpha = 0;
//                                        narr_3_flag = false;
//                                        narr_end[2] = true;
//                                    }
                                } else {
                                    narr_3_alpha += 2;
                                    if (narr_3_alpha > 255) {
                                        narr_3_alpha = 255;
                                    }
                                }
                                thirdThought.setImageAlpha(narr_3_alpha);
                            }
                        });
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riverside_main);

        MySoundPlayer.initSounds(getApplicationContext());

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOption);


        //A4 장면의 '중', '지' 각 롱클릭 버튼
        ji_letter_btn = (Button)findViewById(R.id.ji_letter_btn);
        jung_letter_btn = (Button)findViewById(R.id.jung_letter_btn);
        ji_letter_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                Log.d("ji_btn", "true");
                if(narr_end[1]) {
                    return is_ji_prssd = true;
                }
                return true;
            }
        });
        jung_letter_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                Log.d("jung_btn", "true");
                if(narr_end[1]) {
                    return is_jung_prssd = true;
                }
                return true;
            }
        });

        //터치리스너 역할 :
        //1. 빗방울 제스처 처리
        touchArea = findViewById(R.id.touchArea);
        touchArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //제스처 디텍터에 전달할 이벤트
                detector.onTouchEvent(event);

                //빗방울 제스처
                int action = event.getAction();
                float curX = event.getX();  //눌린 곳의 X좌표
                float curY = event.getY();  //눌린 곳의 Y좌표
                if (action == event.ACTION_DOWN) {   //처음 눌렸을 때
                    if (curX > 390 && curX < 605) {
                        raindrop_start = curY;
//                        Log.d("Prsd_CurY : ", Float.toString(curY));
//                        Log.d("Prsd_CurX : ", Float.toString(curX));
//                        Log.d("Correct Area", "");
                        isStraight = true;
                    } else {
                        raindrop_start = 9999;
                    }
                    //printString("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == event.ACTION_MOVE) {    //누르고 움직였을 때
                    //printString("손가락 움직임 : " + curX + ", " + curY);
                    if (curX < 390 || curX > 505) {
                        isStraight = false;
                    }
                } else if (action == event.ACTION_UP) {    //누른걸 뗐을 때
                    //printString("손가락 뗌 : " + curX + ", " + curY);
                    boolean isLong = curY - raindrop_start > 200;
                    if (isLong && isStraight) {
//                        Log.d("curY - raindrop_start", Float.toString(curY - raindrop_start));
                    } else {
                        if (raindrop_start == 9999) {
//                            Log.d("not Area", ".");
                        } else {
                            if (!isLong) {
//                                Log.d("not Long", ".");
                            }
//                            Log.d("not Straight", Boolean.toString(isStraight));
                        }
                        isStraight = true;
                        raindrop_start = 9999;
                    }
                }
                return true;
            }
        });

        //제스처 디텍터 역할 :
        //1. 한 번 클릭에 하나씩 나오는 자막 구현
        //2. 초기화면 텍스트 지우기
        //3. 반복재생 비디오 시작하기
        //4. 오디오 실행하기
        //5. A4 '중', '지' 더블터치 인식
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            //화면이 눌렸을 때
            @Override
            public boolean onDown(MotionEvent e) {
                //초기화면 텍스트 지우기
                TextView initializeText = (TextView) findViewById(R.id.InitializeText);
                initializeText.setVisibility(View.INVISIBLE);

                //반복재생 비디오 시작하기(flag. 첫 번째 요청에만 실행됨)
                if (is_first_video_start_request) {
                    final VideoView videoView = (VideoView) findViewById(R.id.videoView);
                    videoView.setVisibility(View.VISIBLE);
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
                    is_first_video_start_request = false;
                }

                //나레이션 자막
                touch_cnt++;
                if (touch_cnt > 1) {
                    narr_1_fadeInOut();
                    if (narr_end[0]) {
                        narr_2_fadeInOut();
                    }
                    if (narr_end[1]) {
                        narr_3_fadeInOut();
                    }
                }

                return true;
            }

            //화면이 눌렸다 떼어지는 경우
            @Override
            public void onShowPress(MotionEvent e) {
            }

            //화면이 한 손가락으로 눌렸다 떼어지는 경우
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            //화면이 눌린채 일정한 속도와 방향으로 움직였다 떼어지는 경우
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            //화면을 손가락으로 오랫동안 눌렀을 경우
            @Override
            public void onLongPress(MotionEvent e) {
            }

            //화면이 눌린채 손가락이 가속해서 움직였다 떼어지는 경우
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return true;
            }
        });
    }
}