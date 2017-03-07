package logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by greg on 3/7/17.
 */
public class RssLogger {

    private boolean isLoggingToStdout = true;
    private boolean isLoggingToFile = true;
    private PrintWriter printWriter = null;

    private static RssLogger defaultLogger = new RssLogger();

    public static RssLogger getDefaultLogger() {
        return defaultLogger;
    }

    public static void setDefaultLogger(RssLogger defaultLogger) {
        RssLogger.defaultLogger = defaultLogger;
    }

    public RssLogger() {
        this.setLoggingFile(new File("./log.log"));
    }

    public RssLogger(File loggingFile) {
        this.setLoggingFile(loggingFile);
    }


    public void setLoggingFile(File loggingFile) {
        if(this.printWriter != null){
            this.printWriter.close();
        }

        try {
            this.printWriter = new PrintWriter(loggingFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsLoggingToStdout(boolean isLoggingToStdout) {
        isLoggingToStdout = isLoggingToStdout;
    }

    public void setIsLoggingToFile(boolean isLoggingToFile) {
        isLoggingToFile = isLoggingToFile;
    }

    public void println(String log){
        if(isLoggingToStdout){
            printToStdout(log);
        }
        if(isLoggingToFile){
            printToFile(log);
        }
    }

    public void printToStdout(String log){
        System.out.println(log);
    }

    public void printToFile(String log){
        printWriter.println(log);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        printWriter.close();
    }
}
