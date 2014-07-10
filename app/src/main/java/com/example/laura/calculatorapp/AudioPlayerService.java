package com.example.laura.calculatorapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class AudioPlayerService extends Service {

  MediaPlayer mediaPlayer;
  File sdCard, song;
  public int codigo = 0;
  private final IBinder binder = new MyBinder();

  class MyBinder extends Binder {
    AudioPlayerService getService() {
      return AudioPlayerService.this;
    }
  }

  @Override
  public IBinder onBind(Intent arg0) {
    Toast.makeText(this, "Service binded", Toast.LENGTH_SHORT).show();
    return binder;
  }

  public AudioPlayerService() {
  }


  @Override
  public void onCreate() {
    super.onCreate();
    mediaPlayer = new MediaPlayer();
    sdCard = Environment.getExternalStorageDirectory();
    song = new File(sdCard.getAbsolutePath() + "/Leiva-Miedo.mp3");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    try {
      mediaPlayer.setDataSource(song.getAbsolutePath());
      mediaPlayer.prepare();
      /*mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

        }
      });*/

    } catch (IOException e) {
      e.printStackTrace();
    }
    mediaPlayer.start();
    return START_STICKY;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

}
