package com.example.fujiwarakota.octcatrun;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;

public class MainActivity extends ActionBarActivity {
    int octX;
    int octY;
    int octVelocityY = 0;
    int enemyX;

    static int octSpeed = 15;
    static int enemySpeed = 15;

    int enemyVelocityX = -enemySpeed;

    Timer mTimer = null;
    Handler mHandler = new Handler();
    private Thread mLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainView view = new MainView(this);
        setContentView(view);
    }


    class MainView extends SurfaceView {

        private SurfaceHolder mHolder = null;
        private MainViewHolderCallBack mCallBack;
        public MainView(Context context) {
            super(context);
            mHolder = getHolder();
            mCallBack = new MainViewHolderCallBack(getContext(), this);
            mHolder.addCallback(mCallBack);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            mCallBack.onTouchEvent(event);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
