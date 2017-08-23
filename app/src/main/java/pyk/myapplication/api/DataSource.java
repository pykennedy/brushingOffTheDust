package pyk.myapplication.api;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pyk.myapplication.BloclyApplication;
import pyk.myapplication.BuildConfig;
import pyk.myapplication.R;
import pyk.myapplication.api.model.RssFeed;
import pyk.myapplication.api.model.RssItem;
import pyk.myapplication.api.model.database.DatabaseOpenHelper;
import pyk.myapplication.api.model.database.table.RssFeedTable;
import pyk.myapplication.api.model.database.table.RssItemTable;
import pyk.myapplication.api.network.GetFeedsNetworkRequest;

public class DataSource {
  
  private DatabaseOpenHelper databaseOpenHelper;
  private RssFeedTable       rssFeedTable;
  private RssItemTable       rssItemTable;
  private List<RssFeed>      feeds;
  private List<RssItem>      items;
  
  public DataSource() {
    rssFeedTable = new RssFeedTable();
    rssItemTable = new RssItemTable();
    
    databaseOpenHelper = new DatabaseOpenHelper(BloclyApplication.getSharedInstance(), rssFeedTable,
                                                rssItemTable);
    
    feeds = new ArrayList<RssFeed>();
    items = new ArrayList<RssItem>();
    createFakeData();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        if (BuildConfig.DEBUG && false) {
          BloclyApplication.getSharedInstance().deleteDatabase("blocly_db");
        }
        SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
        new GetFeedsNetworkRequest("http://feeds.feedburner.com/androidcentral?format=xml")
            .performRequest();
      }
    }).start();
  }
  
  public List<RssFeed> getFeeds() {
    return feeds;
  }
  
  public List<RssItem> getItems() {
    return items;
  }
  
  void createFakeData() {
    feeds.add(new RssFeed("My Favorite Feed",
                          "This feed is just incredible, I can't even begin to tell you…",
                          "http://favoritefeed.net",
                          "http://feeds.feedburner.com/favorite_feed?format=xml"));
    for (int i = 0; i < 10; i++) {
      items.add(new RssItem(String.valueOf(i),
                            BloclyApplication.getSharedInstance()
                                             .getString(R.string.placeholder_headline) + " " + i,
                            BloclyApplication.getSharedInstance()
                                             .getString(R.string.placeholder_content),
                            "http://favoritefeed.net?story_id=an-incredible-news-story",
                            "https://bloc-global-assets.s3.amazonaws.com/images-android/foundation/silly-dog.jpg",
                            0, System.currentTimeMillis(), false, false));
    }
  }
}