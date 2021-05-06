package codes.wesley_dev.remasteredlogger.example_one;

import codes.wesley_dev.remasteredlogger.Logger;
import codes.wesley_dev.remasteredlogger.LoggerFactory;
import codes.wesley_dev.remasteredlogger.interfaces.ILogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnnecessaryLocalVariable, unchecked")
public class MainClass {

    private final LoggerFactory loggerFactory;

    public LoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }


    public MainClass() {
        this.loggerFactory = new LoggerFactory(new File("test"));


        final ILogger logger1 = this.loggerFactory.getLogger("Logger1");
        final Logger logger1_ = (Logger) logger1;
        final Logger mainClassLogger = (Logger) this.loggerFactory.getLogger(this.getClass());
        final ILogger mainClassLogger_ = mainClassLogger;

        try {
            this.loggerFactory.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> othersLogs = new ArrayList<>();
        try {
            this.loggerFactory.save(othersLogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> othersLogs2 = new ArrayList<>();
        List<String> othersLogs3 = new ArrayList<>();
        List<String> othersLogs4 = new ArrayList<>();
        try {
            this.loggerFactory.save((List<String>[]) Arrays.asList(othersLogs2, othersLogs3, othersLogs4).toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
