package com.xunce.electrombile.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.xunce.electrombile.R;

import java.util.Random;

/**
 * Created by heyukun on 2015/4/3.
 */
public class AlarmActivity extends Activity{
    ToggleButton btnWarmComfirm = null;
    AudioManager aManager = null;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //播放警铃
        mPlayer= MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        mPlayer.setLooping(true);
        mPlayer.start();

        btnWarmComfirm = (ToggleButton) findViewById(R.id.btn_warning_confirm);
        btnWarmComfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    //stop alarm
                    mPlayer.stop();
                    AlarmActivity.this.finish();
                }
            }
        });
//            @Override
//            public void onClick(View view) {
//                //Intent intent = new Intent(AlarmActivity.this, FragmentActivity.class);
//                //startActivity(intent);
//        //stop alarm
//        mPlayer.stop();
//        AlarmActivity.this.finish();
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPlayer.stop();
        AlarmActivity.this.finish();
    }
}
