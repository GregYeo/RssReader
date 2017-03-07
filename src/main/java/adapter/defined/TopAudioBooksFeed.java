package adapter.defined;

import adapter.Feed;

/**
 * Created by greg on 3/7/17.
 */
public class TopAudioBooksFeed extends Feed {

    String id, title, updated;

    public TopAudioBooksFeed(String id, String title, String updated) {
        this.id = id;
        this.title = title;
        this.updated = updated;
    }

    @Override
    public String printingFormat() {
        return "id : " + id + "\n" +
                "title : " + title + "\n" +
                "updated : " + updated;
    }
}
