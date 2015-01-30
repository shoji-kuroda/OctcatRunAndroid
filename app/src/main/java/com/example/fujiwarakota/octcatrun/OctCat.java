package com.example.fujiwarakota.octcatrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by kuroda02 on 15/01/30.
 */
public class OctCat {

    private static final int CAT_HEIGHT = 150;
    private static final int CAT_WIDTH = 150;
    private final Context mContext;

    private Bitmap mCatImage;

    private int x_base = 50;
    private int x = x_base;
    private float y_base = 50;
    private float y = y_base;
    private float vy = 10;
    private float g = -0.2F;
    private boolean jumpFlag = false;
    private View mParentView;
    
    

    public OctCat(Context context, View parent) {
        mContext = context;
        mParentView = parent;
        mCatImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.octcat);
    }

    public void view(Canvas canvas) {

        int w = mCatImage.getWidth();
        int h = mCatImage.getHeight();

        int width = mParentView.getWidth();
        int height = mParentView.getHeight();

        // 描画元の矩形イメージ
        Rect src = new Rect(0, 0, w, h);
        // 描画先の矩形イメージ
        Rect dst = new Rect(x, height - (int)y - CAT_HEIGHT, x + CAT_WIDTH, height - (int)y);
        canvas.drawBitmap(mCatImage, src, dst, null);
        if (jumpFlag) {
            y += vy;
            vy += g;
            if (y <= y_base) {
                y = y_base;
                vy = 10;
                jumpFlag = false;
            }
        }
    }

    public void jump() {
        jumpFlag = true;
    }
}