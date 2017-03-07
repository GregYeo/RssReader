package adapter;


import logger.RssLogger;

/**
 * Created by greg on 3/7/17.
 */
public abstract class Feed {

    public abstract String printingFormat();

    public void printFeed(){
        RssLogger.getDefaultLogger().println(this.printingFormat());
    };

}
