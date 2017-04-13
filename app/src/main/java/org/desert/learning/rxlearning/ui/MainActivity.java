package org.desert.learning.rxlearning.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import org.desert.learning.rxlearning.R;
import org.desert.learning.rxlearning.ui.operators.OperatorsActivity;
import org.desert.learning.rxlearning.ui.rxbus.RxBusActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void startOperatorsActivity(View view) {
    Intent startActivity = new Intent(this, OperatorsActivity.class);
    startActivity(startActivity);
  }

  public void startNetworkingActivity(View view) {
  }

  public void startRxBusActivity(View view) {
    startActivity(new Intent(this, RxBusActivity.class));
  }

  public void startPaginationActivity(View view) {
  }
}
