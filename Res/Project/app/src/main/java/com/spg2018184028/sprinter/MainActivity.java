package com.spg2018184028.sprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.spg2018184028.sprinter.framework.Background;
import com.spg2018184028.sprinter.framework.BaseScene;
import com.spg2018184028.sprinter.framework.IGameObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private GameView gameView;
    private ImageButton leftButton, rightButton, jumpButton, pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        new MainScene().pushScene();

        setContentView(R.layout.activity_main);

        leftButton = findViewById(R.id.ButtonLeft);
        rightButton = findViewById(R.id.ButtonRight);
        jumpButton = findViewById(R.id.ButtonJump);
        pauseButton = findViewById(R.id.ButtonPause);
    }

    public void onClickLeftButton(View view)
    {
        if(!MainScene.isGamePause)
        {
            MainScene.player.ChangeMoveDir(-1);
        }
        else
        {
            if(MainScene.isLevelUpPause)
            {

            }
        }
    }

    public void onClickRightButton(View view)
    {
        if(!MainScene.isGamePause)
        {
            MainScene.player.ChangeMoveDir(1);
        }
        else
        {
            if(MainScene.isLevelUpPause)
            {

            }
        }
    }

    public void onClickJumpButton(View view)
    {
        if(!MainScene.isGamePause)
        {
            MainScene.player.Jump();
        }
        else
        {
            if(MainScene.isLevelUpPause)
            {
                MainScene.isGamePause = false;
                MainScene.isLevelUpPause = false;
            }
        }
    }

    public void onClickPauseButton(View view) {
        //Log.d(TAG, "pause button clicked!!");
        if(!MainScene.isLevelUpPause)
        {
            if(MainScene.isGamePause)
            {
                MainScene.isGamePause = false;
            }
            else
            {
                MainScene.isGamePause = true;
            }

        }
    }

    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
}