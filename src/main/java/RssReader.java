import adapter.Feed;
import adapter.RssAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 3/7/17.
 */
public class RssReader<FeedType extends Feed> {

    private RssAdapter<FeedType> rssAdapter;
    private DocumentBuilder documentBuilder;

    public RssReader(RssAdapter rssAdapter) {
        this.rssAdapter = rssAdapter;

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new InvalidParameterException("Something wrong during opening url :: " + rssAdapter.toString());
        }
    }

    public void printFeeds(String feedPath){
        ArrayList<FeedType> feeds = this.readFeeds(feedPath);
        this.printFeeds(feeds);
    }
    public void printFeeds(){
        ArrayList<FeedType> feeds = this.readFeeds();
        this.printFeeds(feeds);
    }

    public void printFeeds(List<FeedType> feeds){
        for(FeedType feed : feeds){
            feed.printFeed();
        }
//        feeds.forEach( feed -> feed.printFeed());
    }

    public ArrayList<FeedType> readFeeds() {
        return this.readFeeds(rssAdapter.getFeedRootPath());
    }

    public ArrayList<FeedType> readFeeds(String feedPath) {

        Document doc = null;

        try {
            doc = documentBuilder.parse(rssAdapter.getRssURL());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = null;

        try {
            nodeList = (NodeList) xPath.compile(feedPath).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        ArrayList<FeedType> feeds = new ArrayList<>();

        for (int nodeIndicator = 0; nodeIndicator < nodeList.getLength(); nodeIndicator++){
            Node node = nodeList.item(nodeIndicator);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                FeedType feed = rssAdapter.parseIntoFeed(element);
                feeds.add(feed);
            }
        }

        return feeds;
    }

}
