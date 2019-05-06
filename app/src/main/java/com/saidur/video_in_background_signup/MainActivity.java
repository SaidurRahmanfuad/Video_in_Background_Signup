package com.saidur.video_in_background_signup;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
private VideoView srVideoview;
    MediaPlayer srMediaPlayer;
    int srCurrentVideoPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srVideoview=findViewById(R.id.sr_VideoView);

        Uri uri = Uri.parse("android.resource://com.saidur.video_in_background_signup/"+ R.raw.sun);
        srVideoview.setVideoURI(uri);
        srVideoview.start();

        srVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                srMediaPlayer =mp;
                srMediaPlayer.setLooping(true);

                if(srCurrentVideoPosition !=0)
                 {
                     srMediaPlayer.seekTo(srCurrentVideoPosition);
                     srMediaPlayer.start();
                 }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Capture the current video position and pause the video.
        srCurrentVideoPosition = srMediaPlayer.getCurrentPosition();
        srVideoview.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart the video when resuming the Activity
        srVideoview.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        srMediaPlayer.release();
        srMediaPlayer = null;
    }
}
