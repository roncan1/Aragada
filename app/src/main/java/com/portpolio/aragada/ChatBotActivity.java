package com.portpolio.aragada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ChatBotActivity extends AppCompatActivity {

    DrawCanvas drawCanvas;
    ImageView btn_send;
    LinearLayout btn_stt, btn_ocr, ll_canvas;
    TextView tv_chat;
    Dialog ocrDialog;
    final int PERMISSION = 1;
    Intent intent;
    boolean drawing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        init();
        setBtn_stt();
        setDialog();
        popUpOcrDialog();
        setCanvas();
    }

    void checkPermission() {
        if ( Build.VERSION.SDK_INT >= 23 ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO}, PERMISSION);
        }
    }

    void setBtn_stt() {
        btn_stt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecord();
            }
        });
    }

    void setDialog() {
        ocrDialog = new Dialog(ChatBotActivity.this);
        ocrDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ocrDialog.setContentView(R.layout.ocr_dialog);

        ocrDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                drawing = false;
            }
        });
    }

    void popUpOcrDialog() {
        btn_ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    void setCanvas() {
//        Bitmap bitmap = Bitmap.createBitmap(280,200, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawColor(Color.LTGRAY);
//        Paint p = new Paint();
//        p.setColor(Color.DKGRAY);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(840, 600);
        drawCanvas.setLayoutParams(lp);
        ll_canvas = (LinearLayout) ocrDialog.findViewById(R.id.ll_canvas);
        ll_canvas.addView(drawCanvas);
    }

    void showDialog() {
        ocrDialog.show();


        AppCompatImageButton btn_dialog_finish = ocrDialog.findViewById(R.id.btn_dialog_finish);
        btn_dialog_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawing = false;
                ocrDialog.dismiss();
            }
        });

    }

    void startRecord() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(setAndGetListener());
        speechRecognizer.startListening(intent);
    }



    void init() {
        checkPermission();
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja-JP");
        btn_send = (ImageView) findViewById(R.id.btn_send);
        btn_ocr = (LinearLayout) findViewById(R.id.btn_ocr);
        btn_stt = (LinearLayout) findViewById(R.id.btn_stt);
        tv_chat = (TextView) findViewById(R.id.tv_chat);
        drawCanvas = new DrawCanvas(this);

    }

    RecognitionListener setAndGetListener() {
        RecognitionListener listener = new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Toast.makeText(ChatBotActivity.this, "음성인식을 시작합니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int error) {
                String message;

                switch (error) {
                    case SpeechRecognizer.ERROR_AUDIO:
                        message = "오디오 에러";
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        message = "클라이언트 에러";
                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        message = "퍼미션 없음";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                        message = "네트워크 에러";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                        message = "네트웍 타임아웃";
                        break;
                    case SpeechRecognizer.ERROR_NO_MATCH:
                        message = "찾을 수 없음";
                        break;
                    case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                        message = "RECOGNIZER가 바쁨";
                        break;
                    case SpeechRecognizer.ERROR_SERVER:
                        message = "서버가 이상함";
                        break;
                    case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                        message = "말하는 시간초과";
                        break;
                    default:
                        message = "알 수 없는 오류임";
                        break;
                }

                Toast.makeText(ChatBotActivity.this, "에러가 발생하였습니다. : " + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                for(int i = 0; i < matches.size() ; i++){
                    tv_chat.setText(matches.get(i));
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        };
        return listener;
    }

    private class DrawCanvas extends View {
        public static final int MODE_PEN = 1;                     //모드 (펜)
        final int PEN_SIZE = 7;                                   //펜 사이즈
        ArrayList<Pen> drawCommandList;                           //그리기 경로가 기록된 리스트
        Paint paint;                                              //펜
        Bitmap loadDrawImage;                                     //호출된 이전 그림
        int color;                                                //현재 펜 색상
        int size;                                                 //현재 펜 크기

        public DrawCanvas(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            drawCommandList = new ArrayList<>();
            loadDrawImage = null;
            color = Color.DKGRAY;
            size = PEN_SIZE;

        }

        /**
         * jhChoi - 201124
         * 현재까지 그린 그림을 Bitmap으로 반환합니다.
         */
        public Bitmap getCurrentCanvas() {
            Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            this.draw(canvas);
            return bitmap;
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);

            if (drawing == false) {
                drawCommandList.clear();
                invalidate();
                drawing = true;
            }

            if (loadDrawImage != null) {
                canvas.drawBitmap(loadDrawImage, 0, 0, null);
            }

            for (int i = 0; i < drawCommandList.size(); i++) {
                Pen p = drawCommandList.get(i);
                paint.setColor(p.color);
                paint.setStrokeWidth(p.size);

                if (p.isMove()) {
                    Pen prevP = drawCommandList.get(i - 1);
                    canvas.drawLine(prevP.x, prevP.y, p.x, p.y, paint);
                }
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            int action = e.getAction();
            int state = action == MotionEvent.ACTION_DOWN ? Pen.STATE_START : Pen.STATE_MOVE;
            drawCommandList.add(new Pen(e.getX(), e.getY(), state, color, size));
            invalidate();
            return true;
        }
    }
}