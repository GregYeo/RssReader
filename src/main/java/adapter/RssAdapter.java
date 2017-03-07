package adapter;

import org.w3c.dom.Element;

/**
 * Created by greg on 3/7/17.
 */
public abstract class RssAdapter<FeedType extends Feed> {

    private String rssURL;
    private String feedRootPath;

    public RssAdapter(String rssURL, String feedRootPath) {
        this.rssURL = rssURL;
        this.feedRootPath = feedRootPath;
    }

    public String getRssURL() {
        return rssURL;
    }

    public String getFeedRootPath() {
        return feedRootPath;
    }

    public abstract FeedType parseIntoFeed(Element element);

}
