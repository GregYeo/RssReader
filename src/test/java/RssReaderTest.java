import adapter.defined.TopAudioBooksAdapter;
import adapter.defined.TopAudioBooksFeed;
import adapter.defined.UzabaseFeed;
import adapter.defined.UzabaseAdapter;
import logger.RssLogger;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by greg on 3/7/17.
 */
public class RssReaderTest  {

    String UzBaseRssURL = "http://tech.uzabase.com/rss";
    String UzBaseFeedPath =  "/rss/channel/item";
    String UzBaseFilteringText = "NewsPicks";

    String TopAudioBooksRssURL = "https://itunes.apple.com/us/rss/topaudiobooks/limit=10/xml";
    String TopAudioBooksFeedPath = "/feed/entry";

    @Test
    public void printFeedsForUzabaseWithFilter(){

        UzabaseAdapter adapter = new UzabaseAdapter(UzBaseRssURL, UzBaseFeedPath);

        adapter.setFiltering(true);
        adapter.setFilteringText(UzBaseFilteringText);

        RssReader<UzabaseFeed> rssReader = new RssReader(adapter);
        List<UzabaseFeed> feeds = rssReader.readFeeds();
        rssReader.printFeeds(feeds);

        System.out.println("printFeedsForUzabaseWithFilter :: feeds.size :: " + feeds.size());

        Boolean isSizeOverZero = feeds.size() > 0;
        Boolean isFilteredTheText = feeds.stream().filter( feed ->
                feed.description.indexOf(UzBaseFilteringText) >= 0 ||
                feed.guid.indexOf(UzBaseFilteringText) >= 0 ||
                feed.enclosure.indexOf(UzBaseFilteringText) >= 0 ||
                feed.title.indexOf(UzBaseFilteringText) >= 0 ||
                feed.link.indexOf(UzBaseFilteringText) >= 0
        ).count() <= 0;

        assertTrue(isSizeOverZero && isFilteredTheText);
    }

    @Test
    public void printFeedsForUzabaseWithOutFilter(){
        UzabaseAdapter adapter = new UzabaseAdapter(UzBaseRssURL, UzBaseFeedPath);

        adapter.setFiltering(false);
        adapter.setFilteringText(UzBaseFilteringText);

        RssReader<UzabaseFeed> rssReader = new RssReader(adapter);
        List<UzabaseFeed> feeds = rssReader.readFeeds();
        rssReader.printFeeds(feeds);

        System.out.println("printFeedsForUzabaseWithOutFilter :: feeds.size :: " + feeds.size());

        Boolean isSizeOverZero = feeds.size() > 0;
        Boolean isFilteredTheText = feeds.stream().filter( feed ->
                feed.description.indexOf(UzBaseFilteringText) >= 0 ||
                feed.guid.indexOf(UzBaseFilteringText) >= 0 ||
                feed.enclosure.indexOf(UzBaseFilteringText) >= 0 ||
                feed.title.indexOf(UzBaseFilteringText) >= 0 ||
                feed.link.indexOf(UzBaseFilteringText) >= 0
        ).count() >= 0;

        assertTrue(isSizeOverZero && isFilteredTheText);
    }

    @Test
    public void printFeedsForTopAudioBooks(){

        RssReader<TopAudioBooksFeed> rssReader = new RssReader(new TopAudioBooksAdapter(TopAudioBooksRssURL, TopAudioBooksFeedPath));
        List<TopAudioBooksFeed> feeds = rssReader.readFeeds();
        rssReader.printFeeds(feeds);

        System.out.println("printFeedsForTopAudioBooks :: feeds.size :: " + feeds.size());
        assertTrue(feeds.size() > 0);
    }


    @Test
    public void printErrorMessageWithWrongURL(){
        Boolean isCaughtException = false;
        try{
            UzabaseAdapter adapter = new UzabaseAdapter(UzBaseRssURL + "/INVALID_URL_STRING", UzBaseFeedPath);

            RssReader<UzabaseFeed> rssReader = new RssReader(adapter);
            List<UzabaseFeed> feeds = rssReader.readFeeds();

            rssReader.printFeeds(feeds);

        } catch (Exception ex) {
            isCaughtException = true;
        }

        assertTrue(isCaughtException);
    }

    @Test
    public void isPrintingToFile(){
        File loggingFile = new File("./TestingLogFile.log");
        RssLogger rssLogger = new RssLogger(loggingFile);
        rssLogger.setDefaultLogger(rssLogger);
        rssLogger.setIsLoggingToFile(true);
        rssLogger.setIsLoggingToStdout(false);

        UzabaseAdapter adapter = new UzabaseAdapter(UzBaseRssURL, UzBaseFeedPath);

        RssReader<UzabaseFeed> rssReader = new RssReader(adapter);
        List<UzabaseFeed> feeds = rssReader.readFeeds();

        rssReader.printFeeds(feeds);

        Boolean isFileValid = loggingFile.exists() && loggingFile.length() > 0;
        loggingFile.delete();

        System.out.println("loggingFile.length :: " + loggingFile.length());

        assertTrue(isFileValid);
    }

}
