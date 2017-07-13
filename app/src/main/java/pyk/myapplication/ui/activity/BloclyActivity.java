package pyk.myapplication.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import pyk.myapplication.BloclyApplication;
import pyk.myapplication.R;

public class BloclyActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_blocly);
    Toast.makeText(this,
                   BloclyApplication.getSharedDataSource().getFeeds().get(0).getTitle(),
                   Toast.LENGTH_LONG).show();
  }
  
}
