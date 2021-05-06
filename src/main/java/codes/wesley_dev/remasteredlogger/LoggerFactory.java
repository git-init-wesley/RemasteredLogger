/*
 * Conçu avec ♡ par Levasseur Wesley.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 16:10:17.
 */

package codes.wesley_dev.remasteredlogger;

import codes.wesley_dev.remasteredlogger.interfaces.ILogger;
import codes.wesley_dev.remasteredlogger.interfaces.ILoggerFactory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <strong>EN:</strong> LoggerFactory consists of managing all {@link ILogger} and then managing the backup.<br>
 * This class is a default LoggerFactory if the user does not want to create one himself by implementing {@link ILoggerFactory}.<br>
 * <strong>FR:</strong> LoggerFactory consiste à gérer tous les {@link ILogger} puis de gérer la sauvegarder.
 * Cette classe est un LoggerFactory de défaut si l'utilisateur ne souhaite pas en crée lui-même en implémentant {@link ILoggerFactory}.
 *
 * @author Levasseur Wesley
 * @version 1.1.4
 * @see ILogger
 */
public class LoggerFactory implements ILoggerFactory {

    private final List<String> logs = new ArrayList<>();
    private final File logsDir;
    private final ConcurrentMap<String, ILogger> loggers = new ConcurrentHashMap<>();

    /**
     * <strong>EN:</strong> This constructor allows you to create a LoggerFactory.<br>
     * <strong>FR:</strong> Ce constructeur permet de créer un LoggerFactory.
     *
     * @param dir <br><strong>EN:</strong> The folder where the message recording files will be saved.<br><strong>FR:</strong> Le dossier où les fichiers d'enregistrement des messages seront enregistrés.
     */
    public LoggerFactory(@NotNull final File dir) throws InternalError {
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