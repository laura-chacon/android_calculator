package com.example.laura.calculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MemoryGameActivity extends Activity {

  public MemoryGameActivity() {
  }

  private class Card {
    int cardId;
    int colorResorceId;
    boolean isPartnerFound;

    public Card(){}
  };

  private static final int[] COLORS = new int[] {R.color.black, R.color.blue,
      R.color.fucsia, R.color.green, R.color.orange, R.color.purple, R.color.red,
      R.color.yellow};
  private final static int UNDEFINED_COLOR = -1;
  private final static int CARDS_COUNT = 16;
  private int[] mNumberOfColorAppearances = new int[8];
  private Card[] mCards;
  private boolean mFirstClickOnPair;
  private Card mFirstCardOnPair;
  private Random r;
  Runnable runnable = new Runnable() {
   @Override
   public void run(int idResource) {
     findViewById(idResource).setBackgroundColor(getResources().getColor(R.color.black));
   }
  };
  private final Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_memory_game);

    mFirstClickOnPair = true;
    mFirstCardOnPair = null;
    mCards = new Card[16];
    r = new Random();
    initializeNumberOfColorAppearances();
    initializeCards();
    setupButtons();
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

  private void initializeNumberOfColorAppearances() {
    for (int i = 0; i < 8; i++) {
      mNumberOfColorAppearances[i] = 0;
    }
  }

  private void initializeCards() {
    for(int i = 0; i < CARDS_COUNT; i++) {
      Card c = new Card();
      c.cardId = i;
      c.colorResorceId = -1;
      c.isPartnerFound = false;
      mCards[i] = c;
    }
    int randomNumber;
    for(int i = 0; i < CARDS_COUNT; i++) {
      boolean has_card_been_assigned_a_color = false;
      while(!has_card_been_assigned_a_color) {
        randomNumber = r.nextInt(COLORS.length);
        if(mNumberOfColorAppearances[randomNumber] < 2) {
          mNumberOfColorAppearances[randomNumber]++;
          mCards[i].colorResorceId = COLORS[randomNumber];
          has_card_been_assigned_a_color = true;
        }
      }
    }
  }

  private void initializeCardsToBlackBackground() {
    setupImageButtonToBlack(R.id.imageButton1);
    setupImageButtonToBlack(R.id.imageButton2);
    setupImageButtonToBlack(R.id.imageButton3);
    setupImageButtonToBlack(R.id.imageButton4);
    setupImageButtonToBlack(R.id.imageButton5);
    setupImageButtonToBlack(R.id.imageButton6);
    setupImageButtonToBlack(R.id.imageButton7);
    setupImageButtonToBlack(R.id.imageButton8);
    setupImageButtonToBlack(R.id.imageButton9);
    setupImageButtonToBlack(R.id.imageButton10);
    setupImageButtonToBlack(R.id.imageButton11);
    setupImageButtonToBlack(R.id.imageButton12);
    setupImageButtonToBlack(R.id.imageButton13);
    setupImageButtonToBlack(R.id.imageButton14);
    setupImageButtonToBlack(R.id.imageButton15);
    setupImageButtonToBlack(R.id.imageButton16);
  }

  private void setupImageButtonToBlack(int cardId) {
    findViewById(cardId).setBackgroundColor(getResources()
        .getColor(R.color.black));
  }

  private void setupButtons() {
    Log.e("","set up buttons");
    List<ImageButton> imageButtons = getImageButtons();
    for(int i = 0; i < imageButtons.size(); i++) {
      setupImageButton(imageButtons.get(i).getId(), i);
    }
    setupResetButton(R.id.buttonReset);
  }

  private List<ImageButton> getImageButtons() {
    ViewGroup container = (ViewGroup) findViewById(R.id.image_buttons_container);
    List<ImageButton> imageButtons = new ArrayList<ImageButton>();
    for(int i = 0; i < container.getChildCount(); i++) {
      ViewGroup subcontainer = (ViewGroup) container.getChildAt(i);
      for(int j = 0; j < subcontainer.getChildCount(); j++) {
        imageButtons.add( (ImageButton) subcontainer.getChildAt(j));
      }
    }
    return imageButtons;
  }

  private void setupResetButton(int id) {
    findViewById(id).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mFirstClickOnPair = true;
        mFirstCardOnPair = null;
        mCards = new Card[CARDS_COUNT];
        r = new Random();
        initializeCardsToBlackBackground();
        initializeNumberOfColorAppearances();
        initializeCards();
        setupButtons();
      }
    });
  }

  private void setupImageButton(final int idResource, final int cardId) {
    findViewById(idResource).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!mCards[cardId].isPartnerFound) {
          if (mFirstClickOnPair) {
            findViewById(idResource).setBackgroundColor(getResources().getColor
                (mCards[cardId].colorResorceId));
            mFirstCardOnPair = mCards[cardId];
            mFirstClickOnPair = false;

            findViewById(idResource).setBackgroundColor(getResources().getColor(R.color
                .black));
          }
          else {
            findViewById(idResource).setBackgroundColor(getResources().getColor
                (mCards[cardId].colorResorceId));
            //SystemClock.sleep(2000);
            if (mFirstCardOnPair.colorResorceId != mCards[cardId].colorResorceId) {
              Log.e("","ID CARD: " + String.valueOf(mFirstCardOnPair.cardId));
              findViewById(mFirstCardOnPair.cardId).setBackgroundColor(getResources()
                  .getColor(R.color.black));
              findViewById(idResource).setBackgroundColor(getResources()
                  .getColor(R.color.black));
            } else {
              mFirstCardOnPair.isPartnerFound = true;
              mCards[cardId].isPartnerFound = true;
            }
            mFirstClickOnPair = true;
          }
        }
      }
    });
  }
}