package pyk.myapplication.api;

import java.util.ArrayList;
import java.util.List;

import pyk.myapplication.BloclyApplication;
import pyk.myapplication.R;
import pyk.myapplication.api.model.RssFeed;
import pyk.myapplication.api.model.RssItem;

public class DataSource {
  
  private List<RssFeed> feeds;
  private List<RssItem> items;
  
  public DataSource() {
    feeds = new ArrayList<RssFeed>();
    items = new ArrayList<RssItem>();
    createFakeData();
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
                            BloclyApplication.getSharedInstance().getString(R.string.placeholder_headline) + " " + i,
                            BloclyApplication.getSharedInstance().getString(R.string.placeholder_content),
                            "http://favoritefeed.net?story_id=an-incredible-news-story",
                            "https://bloc-global-assets.s3.amazonaws.com/images-android/foundation/silly-dog.jpg",
                            0, System.currentTimeMillis(), false, false));
    }
  }
}