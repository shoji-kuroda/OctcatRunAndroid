package com.example.fujiwarakota.octcatrun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Created by kuroda02 on 15/01/30.
 */
public class MainViewHolderCallBack implements SurfaceHolder.Callback, Runnable {

    private final Context mContext;
    private final Killer mKiller;
    private SurfaceHolder mHolder = null;
    private Thread mThread = null;
    private OctCat m0ctCat;
    private boolean mGameOver = true;

    public MainViewHolderCallBack(Context context, View view) {
        mContext = context;
        m0ctCat = new OctCat(mContext, view);
        mKiller = new Killer(mContext, view);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread = null;
    }

    public void onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if(mGameOver) {
                    mGameOver = false;
                } else {
                    m0ctCat.jump();
                    mKiller.start();
                }
                break;
        }
    }

    @Override
    public void run() {
        Canvas canvas;
        while (mThread != null) {
            canvas = mHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            m0ctCat.view(canvas);
            mKiller.view(canvas);
            mHolder.unlockCanvasAndPost(canvas);
        }
    }
}
