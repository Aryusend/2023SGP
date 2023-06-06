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
                MainScene.levelUpIndex--;
                if(MainScene.levelUpIndex<0)
                {
                    MainScene.levelUpIndex=2;
                }
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
                MainScene.levelUpIndex++;
                if(MainScene.levelUpIndex>2)
                {
                    MainScene.levelUpIndex=0;
                }
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

                if(MainScene.levelUpSelect[MainScene.levelUpIndex]==0)
                {
                    MainScene.player.curHp = MainScene.player.maxHp;
                }
                else if(MainScene.levelUpSelect[MainScene.levelUpIndex]==1)
                {
                    MainScene.player.moveSpeed+=0.01;
                }
                if(MainScene.levelUpSelect[MainScene.levelUpIndex]==2)
                {
                    MainScene.player.maxHp+=2;
                    MainScene.player.curHp+=2;
                }
                if(MainScene.levelUpSelect[MainScene.levelUpIndex]==3)
                {
                    Weapon.widthPlus+=0.2;
                    Weapon.yOffset-=0.05;
                    Weapon.xOffset-=0.1;
                }
                if(MainScene.levelUpSelect[MainScene.levelUpIndex]==4)
                {
                    MainScene.player.expPlus+=1;
                }
                if(MainScene.levelUpSelect[MainScene.levelUpIndex]==5)
                {
                    MainScene.score+=300;
                }

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
        GameView.clear();
        super.onDestroy();
    }
}