package adapter.defined;

import adapter.Feed;
import adapter.RssAdapter;
import org.w3c.dom.Element;

/**
 * Created by greg on 3/7/17.
 */
public class TopAudioBooksAdapter extends RssAdapter<TopAudioBooksFeed>{

    public TopAudioBooksAdapter(String rssURL, String feedRootPath) {
        super(rssURL, feedRootPath);
    }

    @Override
    public TopAudioBooksFeed parseIntoFeed(Element element) {

        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String title = element.getElementsByTagName("title").item(0).getTextContent();
        String updated = element.getElementsByTagName("updated").item(0).getTextContent();

        return new TopAudioBooksFeed(id, title, updated);
    }
}
