package com.example.vatsc.audio_demo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp3;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp3 = MediaPlayer.create(this,R.raw.labra);

        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volCon =(SeekBar)findViewById(R.id.seekBar);

        volCon.setMax(maxVol);
        volCon.setProgress(currVol);
        volCon.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override


            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



         final SeekBar scrubber = (SeekBar)findViewById(R.id.scrubber);
        scrubber.setMax(mp3.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mp3.getCurrentPosition());
            }
        },0,100);


        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("scrubber value",String.valueOf(i));
                mp3.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        }
        public void onPause(View view)
        {
            super.onPause();
            try{
                if (mp3.isPlaying()) {
                    mp3.pause();
                }

            }catch(Exception we){
                we.printStackTrace();
            }
        }
        public void play(View view)
        {

            mp3.start();
        }

}
