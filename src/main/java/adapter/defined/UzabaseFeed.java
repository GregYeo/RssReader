package adapter.defined;

import adapter.Feed;

/**
 * Created by greg on 3/7/17.
 */
public class UzabaseFeed extends Feed {

    public String title, link, description, guid, enclosure;

    public UzabaseFeed(String title, String link, String description, String guid, String enclosure) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.guid = guid;
        this.enclosure = enclosure;
    }

    @Override
    public String printingFormat() {
        return "title : " + title + "\n" +
                "link : " + link + "\n" +
                "description : " + description + "\n" +
                "guid : " + guid + "\n" +
                "enclosure : " + enclosure;
    }
}
