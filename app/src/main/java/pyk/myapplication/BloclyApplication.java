package pyk.myapplication;

import android.app.Application;

import pyk.myapplication.api.DataSource;

public class BloclyApplication extends Application {
  
  // #1
  public static BloclyApplication getSharedInstance() {
    return sharedInstance;
  }
  
  // #2
  public static DataSource getSharedDataSource() {
    return BloclyApplication.getSharedInstance().getDataSource();
  }
  
  private static BloclyApplication sharedInstance;
  private        DataSource        dataSource;
  
  // #3
  @Override
  public void onCreate() {
    super.onCreate();
    sharedInstance = this;
    dataSource = new DataSource();
  }
  
  public DataSource getDataSource() {
    return dataSource;
  }
}