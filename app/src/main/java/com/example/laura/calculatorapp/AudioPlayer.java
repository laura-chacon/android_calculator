package com.example.laura.calculatorapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AudioPlayer extends Activity {

  //private BoundService bService;
  private boolean bound = false;


  private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      AudioPlayerService.MyBinder binder = (AudioPlayerService.MyBinder) service;
      /*bService = binder.getService();*/
      bound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio_player);
    Button b = (Button) findViewById(R.id.buttonPlayMediaPlayer);
    b.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), AudioPlayerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Intent intent;
    switch(item.getItemId()) {
      case R.id.calculator:
        intent = new Intent(this, Calculator.class);
        startActivityForResult(intent, 0);
        return true;
      case R.id.audioPlayer:
        intent = new Intent(this, AudioPlayer.class);
        startActivityForResult(intent, 0);
        return true;
      case R.id.memoryGame:
        intent = new Intent(this, MemoryGameActivity.class);
        startActivityForResult(intent, 0);
        return true;
      case R.id.profile:
        intent = new Intent(this, Profile.class);
        startActivityForResult(intent, 0);
        return true;
      case R.id.ranking:
        intent = new Intent(this, Register.class);
        startActivityForResult(intent, 0);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
/*
  @Override
  public void onServiceDisconnected(ComponentName name) {

  }
*/
}
