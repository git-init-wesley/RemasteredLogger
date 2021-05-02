/*
 * Conçu avec ♡ par Levasseur Wesley pour Etsuko.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 16:10:17.
 */

package codes.wesley.dev.remasteredlogger.logger;

import com.etsuko.api.logger.interfaces.ILogger;
import com.etsuko.api.logger.interfaces.ILoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoggerFactory implements ILoggerFactory {

    private final List<String> logs = new ArrayList<>();
    private final File logsDir;
    private final ConcurrentMap<String, ILogger> loggers = new ConcurrentHashMap<>();

    public LoggerFactory(File dir) throws InternalError {
        if (!dir.exists())
            if (!dir.mkdir())
                throw new InternalError("The Logger folder couldn't be created.\nThis could be due to a lack of permission or inaccurate path.");
        this.logsDir = dir;
    }

    @Override
    public List<String> getLogs() {
        return this.logs;
    }

    @Override
    public File getLogsDir() {
        return this.logsDir;
    }

    @Override
    public ConcurrentMap<String, ILogger> getLoggers() {
        return this.loggers;
    }
}