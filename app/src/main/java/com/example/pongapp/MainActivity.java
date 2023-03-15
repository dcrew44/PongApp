package com.example.pongapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFrameLayout;
    private Ball mBall;
    private Thread mThread;
    private ImageView ballView;
    private int top, bottom, left, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrameLayout = findViewById(R.id.framelayout);
        top = mFrameLayout.getTop();
        bottom = mFrameLayout.getBottom();
        left = mFrameLayout.getLeft();
        right = mFrameLayout.getRight();
        buildBall();

        mThread = new Thread(calculateAction);
        mThread.start();


    }

    private void buildBall() {
        mBall = new Ball(100, 100, 10, 10);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ballView = (ImageView) layoutInflater.inflate(R.layout.ball_view, null);

        ballView.setScaleX(.3f);
        ballView.setScaleY(.3f);
        ballView.setX((float) mBall.mX);
        ballView.setY((float) mBall.mY);
        mBall.setRadius(ballView.getWidth()/2);

        mBall.setCollisionBoundaries(displayMetrics.widthPixels, displayMetrics.heightPixels);
        mFrameLayout.addView(ballView, 0);
        mBall.radius = ballView.getWidth()/2;
    }
private Runnable calculateAction = new Runnable() {
    @Override
    public void run() {
        while (true) {
            mBall.move();
            ballView.setX((float) mBall.mX);
            ballView.setY((float) mBall.mY);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
};
    public Handler threadHandler = new Handler() {
        public void handleMessage(Message msg) {
            //UPDATE BALL LOCATION
            ballView.setX((float) mBall.mX);
            ballView.setY((float) mBall.mY);
        }
    };
}

