package com.example.phuon.intro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.VideoView;

public class Intro extends AppCompatActivity {

    VideoView videoStart;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        toolbar = (Toolbar) findViewById(R.id.toolbarIntro);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        videoStart = (VideoView) findViewById(R.id.videoWelcome);
        videoStart.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo));
        videoStart.start();
        videoStart.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startActivity(new Intent(Intro.this, Comfirm.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }
}
