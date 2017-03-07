package adapter.defined;

import adapter.RssAdapter;
import org.w3c.dom.Element;

/**
 * Created by greg on 3/7/17.
 */
public class UzabaseAdapter extends RssAdapter<UzabaseFeed>{

    private String filteringText = "NewsPicks";
    private Boolean isFiltering = true;

    public UzabaseAdapter(String rssURL, String feedRootPath) {
        super(rssURL, feedRootPath);
    }

    public String maskString(String maskingTarget, String input){
        if(isFiltering){
            return input.replaceAll(maskingTarget, "");
        }else{
            return input;
        }

    }

    public void setFilteringText(String filteringText) {
        this.filteringText = filteringText;
    }

    public void setFiltering(Boolean filtering) {
        isFiltering = filtering;
    }

    @Override
    public UzabaseFeed parseIntoFeed(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent();
        String link = element.getElementsByTagName("link").item(0).getTextContent();
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        String guid = element.getElementsByTagName("guid").item(0).getTextContent();
        String enclosure = element.getElementsByTagName("enclosure").item(0).getTextContent();

        return new UzabaseFeed(
                maskString(this.filteringText, title),
                maskString(this.filteringText, link),
                maskString(this.filteringText, description),
                maskString(this.filteringText, guid),
                maskString(this.filteringText, enclosure)
        );
    }
}
