package com.example.laura.calculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Calculator extends Activity {

  private EditText mEditText;
  private int mFirstOpperand;
  private boolean mIsAdd;
  private boolean mIsSubstraction;
  private boolean mWasLastClickOnAnOperation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);
    mEditText = (EditText) findViewById(R.id.edit_text);
    mIsAdd = false;
    mIsSubstraction = false;
    mWasLastClickOnAnOperation = false;
    mFirstOpperand = (-1);
    setupNumberButtons();
    setupResetButton();
    setupEqualsButton();
    setupOperationButtons();
  }

  @Override
  protected void onSaveInstanceState (Bundle outstate) {
    super.onSaveInstanceState(outstate);
    outstate.putInt("first_opperand", mFirstOpperand);
    outstate.putBoolean("is_add", mIsAdd);
    outstate.putBoolean("is_substraction", mIsSubstraction);
    outstate.putBoolean("was_last_click_on_an_operation", mWasLastClickOnAnOperation);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState){
    super.onRestoreInstanceState(savedInstanceState);
    mFirstOpperand = savedInstanceState.getInt("first_opperand");
    mIsAdd = savedInstanceState.getBoolean("is_add");
    mIsSubstraction = savedInstanceState.getBoolean("is_substraction");
    mWasLastClickOnAnOperation = savedInstanceState.getBoolean
        ("was_last_click_on_an_operation");
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

  private void setupNumberButtons() {
    setupNumberButton(R.id.button1, "1");
    setupNumberButton(R.id.button2, "2");
    setupNumberButton(R.id.button3, "3");
    setupNumberButton(R.id.button4, "4");
    setupNumberButton(R.id.button5, "5");
    setupNumberButton(R.id.button6, "6");
    setupNumberButton(R.id.button7, "7");
    setupNumberButton(R.id.button8, "8");
    setupNumberButton(R.id.button9, "9");
    setupNumberButton(R.id.button0, "0");
  }

  private void setupNumberButton(final int resourceId, final String number) {
    findViewById(resourceId).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String currentText = String.valueOf(mEditText.getText());
        if(mWasLastClickOnAnOperation == true) {
          mWasLastClickOnAnOperation = false;
          mEditText.setText(number);
        }
        else {
          mEditText.setText(currentText + number);
        }
      }
    });
  }

  private void setupResetButton() {
    findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mEditText.setText("");
      }
    });
  }

  private void setupEqualsButton() {
      findViewById(R.id.equals).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(!(String.valueOf(mEditText.getText()).equals(""))) {
            int currentNumber = Integer.parseInt((mEditText.getText()).toString());
            if(mIsAdd) {
              mEditText.setText(Integer.toString(currentNumber + mFirstOpperand));
              mIsAdd = false;
            }
            else if(mIsSubstraction) {
              mEditText.setText(Integer.toString((currentNumber - mFirstOpperand)*(-1)));
              mIsSubstraction = false;
            }
          }
        }
    });
  }

  private void setupOperationButtons() {
    setupAddButton();
    setupSubstractionButton();
  }

  private void setupAddButton() {
    findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(!(String.valueOf(mEditText.getText()).equals(""))) {
          mWasLastClickOnAnOperation = true;
          mIsAdd = true;
          mFirstOpperand = Integer.parseInt(String.valueOf(mEditText.getText()));
        }
      }
    });
  }

  private void setupSubstractionButton() {
    findViewById(R.id.substract).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!(String.valueOf(mEditText.getText()).equals(""))) {
          mWasLastClickOnAnOperation = true;
          mIsSubstraction = true;
          mFirstOpperand = Integer.parseInt(String.valueOf(mEditText.getText()));
        }
      }
    });
  }
}
