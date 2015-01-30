package com.example.fujiwarakota.octcatrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

/**
 * Created by kuroda02 on 15/01/30.
 */
public class Killer {

    private static final int KILLER_HEIGHT = 150;
    private static final int KILLER_WIDTH = 150;

    private final Context mContext;
    private final View mParentView;
    private final Bitmap mKillerImage;

    private int x_base = 50;
    private int x;
    private int y_base = 50;
    private int y = y_base;
    private int speed = 2;
    private boolean mStart;


    public Killer(Context context, View parent) {
        mContext = context;
        mParentView = parent;
        mKillerImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.octcat);
        x = mParentView.getWidth() + KILLER_WIDTH;
        speed = new Random().nextInt(10) + 5;
    }

    public void view(Canvas canvas) {

        if(!mStart)
            return;

        int w = mKillerImage.getWidth();
        int h = mKillerImage.getHeight();

        int width = mParentView.getWidth();
        int height = mParentView.getHeight();

        // 描画元の矩形イメージ
        Rect src = new Rect(0, 0, w, h);
        // 描画先の矩形イメージ
        Rect dst = new Rect(x, height - y - KILLER_HEIGHT, x + KILLER_WIDTH, height - y);
        canvas.drawBitmap(mKillerImage, src, dst, null);

        x -= speed;
        if(x < -KILLER_WIDTH) {
            x = mParentView.getWidth() + KILLER_WIDTH;
            speed = new Random().nextInt(10) + 5;
        }
    }

    public void start() {
        mStart = true;
    }
}
