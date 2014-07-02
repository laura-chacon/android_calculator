package com.example.laura.calculatorapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {

  private EditText mEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);
    mEditText = (EditText) findViewById(R.id.edit_text);
    setupNumberButtons();
    setupResetButton();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
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

  private void setupNumberButton(int resourceId, final String number) {
    findViewById(resourceId).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String currentText = String.valueOf(mEditText.getText());
        mEditText.setText(currentText + number);
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
}
