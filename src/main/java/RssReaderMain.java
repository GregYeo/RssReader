import adapter.defined.UzabaseAdapter;

/**
 * Created by greg on 3/7/17.
 */
public class RssReaderMain {

    public static void main(String[] args){

        String rssURL = "http://tech.uzabase.com/rss";
        String feedPath = "/rss/channel/item";

        UzabaseAdapter uzabaseAdapter = new UzabaseAdapter(rssURL, feedPath);
        uzabaseAdapter.setFiltering(true);
        uzabaseAdapter.setFilteringText("NewsPicks");

        RssReader bizRssReader = new RssReader(uzabaseAdapter);

        bizRssReader.printFeeds();
    }
}
