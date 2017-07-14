package pyk.myapplication.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.myapplication.BloclyApplication;
import pyk.myapplication.R;
import pyk.myapplication.api.DataSource;
import pyk.myapplication.api.model.RssFeed;
import pyk.myapplication.api.model.RssItem;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder> {
  
  @Override
  public ItemAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
    View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rss_item, viewGroup,
                                                                       false);
    return new ItemAdapterViewHolder(inflate);
  }
  
  @Override
  public void onBindViewHolder(ItemAdapterViewHolder itemAdapterViewHolder, int index) {
    DataSource sharedDataSource = BloclyApplication.getSharedDataSource();
    itemAdapterViewHolder.update(sharedDataSource.getFeeds().get(0),
                                 sharedDataSource.getItems().get(index));
  }
  
  @Override
  public int getItemCount() {
    return BloclyApplication.getSharedDataSource().getItems().size();
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView feed;
    TextView content;
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.tv_rss_item_title);
      feed = (TextView) itemView.findViewById(R.id.tv_rss_item_feed_title);
      content = (TextView) itemView.findViewById(R.id.tv_rss_item_content);
    }
    
    void update(RssFeed rssFeed, RssItem rssItem) {
      feed.setText(rssFeed.getTitle());
      title.setText(rssItem.getTitle());
      content.setText(rssItem.getDescription());
    }
  }
}
