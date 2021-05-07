package codes.wesley_dev.remasteredlogger.example;

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

    public MainClass() {
        this.loggerFactory = new LoggerFactory(new File("test"));


        final ILogger logger1 = this.loggerFactory.getLogger("Logger1");
        final Logger logger1_ = (Logger) logger1;
        final Logger mainClassLogger = (Logger) this.loggerFactory.getLogger(this.getClass());
        final ILogger mainClassLogger_ = mainClassLogger;
        final Logger _mainClassLogger = (Logger) this.loggerFactory.getLogger(MainClass.class);
        final ILogger _mainClassLogger_ = _mainClassLogger;

        logger1.isDebugging();
        logger1.activateDebugging();
        logger1.deactivateDebugging();

        logger1.isInforming();
        logger1.activateInforming();
        logger1.deactivateInforming();

        logger1.isErroring();
        logger1.activateErroring();
        logger1.deactivateErroring();

        logger1.isTracing();
        logger1.activateTracing();
        logger1.deactivateTracing();

        logger1.isWarning();
        logger1.activateWarning();
        logger1.deactivateWarning();

        logger1.debug("message");
        logger1.info("message", new Throwable("with errors"));
        //logger1.error("{MessageFormater}", new Object());
        //logger1.trace("{MessageFormater}", new Object(), new Object());
        //logger1.warn("{MessageFormater}", new Object(), new Object(), ...);

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

    public LoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }
}
