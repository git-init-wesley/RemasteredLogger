/*
 * Conçu avec ♡ par Levasseur Wesley pour Etsuko.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 17:21:22.
 */

package codes.wesley_dev.remasteredlogger.interfaces;

import codes.wesley_dev.remasteredlogger.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <strong>EN:</strong> ILogger consists of managing all {@link ILogger} and then managing the backup.<br>
 * <strong>FR:</strong> ILogger consiste à gérer tous les {@link ILogger} puis de gérer la sauvegarder.
 *
 * @author Levasseur Wesley
 * @version 1.1.0
 */
public interface ILoggerFactory {

    /**
     * @return <br><strong>EN:</strong> The {@link List} list of all event message transmissions.<br><strong>FR:</strong> La list {@link List} de toutes les émissions de messages suite à des événements.
     */
    default List<String> getLogs() {
        return new ArrayList<>();
    }

    /**
     * <strong>EN:</strong> This function allows you to add a message from the {@link ILoggerFactory#getLogs()} list.<br>
     * <strong>FR:</strong> Cette fonction permet d'ajouter un message depuis la liste {@link ILoggerFactory#getLogs()}.
     *
     * @param log <br><strong>EN:</strong> The message.<br><strong>FR:</strong> Le message.
     */
    default void addLog(String log) {
        this.getLogs().add(log);
    }

    /**
     * @return <br><strong>EN:</strong> The folder where the message recording files will be saved.<br><strong>FR:</strong> Le dossier où les fichiers d'enregistrement des messages seront enregistrés.
     */
    File getLogsDir();

    /**
     * @return <br>
     * <strong>EN:</strong> A {@link ConcurrentMap} where all the Loggers are located and can be retrieved from their name. {@code Key, Value} for {@code Key} the name {@link String} and {@code Value} for the Logger {@link ILogger}.<br>
     * <strong>FR:</strong> Une {@link ConcurrentMap} où tous les Logger s'y trouve et pouvant être récupérer depuis leur nom. {@code Key,Value} pour {@code Key} le nom {@link String} et {@code Value} pour le Logger {@link ILogger}.
     */
    default ConcurrentMap<String, ILogger> getLoggers() {
        return new ConcurrentHashMap<>();
    }

    /**
     * <strong>EN:</strong> This function allows you to retrieve a Logger or create it if it does not exist while adding it to the list of Loggers. {@link ILoggerFactory#getLoggers()}<br>
     * <strong>FR:</strong> Cette fonction permet de récupérer un Logger ou de créer le crée si ce dernier est inexistant tout en l'ajoutant a la liste des Loggers. {@link ILoggerFactory#getLoggers()}
     *
     * @param name <br><strong>EN:</strong> The name of the Logger.<br><strong>FR:</strong> Le nom du Logger.
     * @return <br><strong>EN:</strong> The Logger {@link ILogger} retrieve or create.<br><strong>FR:</strong> Le Logger {@link ILogger} récupérer ou créer.
     */
    default ILogger getLogger(String name) {
        ILogger logger = this.getLoggers().get(name);
        if (logger != null) return logger;
        else {
            ILogger n = new Logger(name, this);
            ILogger o = this.getLoggers().putIfAbsent(name, n);
            return o == null ? n : o;
        }
    }

    /**
     * <strong>EN:</strong> This function allows you to retrieve a Logger or create it if it does not exist while adding it to the list of Loggers. {@link ILoggerFactory#getLoggers()}<br>
     * <strong>FR:</strong> Cette fonction permet de récupérer un Logger ou de créer le crée si ce dernier est inexistant tout en l'ajoutant a la liste des Loggers. {@link ILoggerFactory#getLoggers()}
     *
     * @param _class <br><strong>EN:</strong> The class where the Logger is generally performed.<br><strong>FR:</strong> Le class où le Logger s'effectue en général.
     * @return <br><strong>EN:</strong> The Logger {@link ILogger} retrieve or create.<br><strong>FR:</strong> Le Logger {@link ILogger} récupérer ou créer.
     */
    default ILogger getLogger(Class<?> _class) {
        ILogger logger = this.getLoggers().get(ILogger.performName(_class));
        if (logger != null) return logger;
        else {
            ILogger n = new Logger(_class, this);
            ILogger o = this.getLoggers().putIfAbsent(ILogger.performName(_class), n);
            return o == null ? n : o;
        }
    }

    /**
     * <strong>EN:</strong> The latter can be modified during a recreation of the Logger during the reimplementation.<br>
     * <strong>FR:</strong> Cette dernière pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.
     *
     * @return <br><strong>EN:</strong> Simple date format.<br><strong>FR:</strong> Le format de la date.
     */
    default SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("MMMM dd',' yyyy '-' hh:mm:ss aaa '('zZ')'");
    }

    /**
     * <strong>EN:</strong> The latter allows you to save the messages in a file.<br>
     * <strong>FR:</strong> Cette dernière permet de sauvegarder les messages dans un fichier.
     *
     * @param allLogs <br><strong>EN:</strong> The messages that need to be saved.<br><strong>FR:</strong> Les messages qu'ils faut sauvegarder.
     * @throws IOException <br><strong>EN:</strong> An error may occur while saving the file.<br><strong>FR:</strong> Une erreur peut survenir pendant l'enregistrement du fichier.
     */
    default void save(List<String>[] allLogs) throws IOException {
        List<String> logs = new ArrayList<>();
        Arrays.asList(allLogs).forEach(logs::addAll);
        this.save(logs);
    }


    /**
     * <strong>EN:</strong> The latter allows you to save the messages in a file.<br>
     * <strong>FR:</strong> Cette dernière permet de sauvegarder les messages dans un fichier.
     *
     * @param logs <br><strong>EN:</strong> The messages that need to be saved.<br><strong>FR:</strong> Les messages qu'ils faut sauvegarder.
     * @throws IOException <br><strong>EN:</strong> An error may occur while saving the file.<br><strong>FR:</strong> Une erreur peut survenir pendant l'enregistrement du fichier.
     */
    default void save(List<String> logs) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.getLogsDir().getPath() + "/" + this.getDateFormat().format(new Date()).replace(":", "-").replace(" ", "_") + ".log"), StandardCharsets.UTF_8));
        for (String line : logs) {
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    /**
     * <strong>EN:</strong> The latter allows you to save the messages in a file.<br>
     * <strong>FR:</strong> Cette dernière permet de sauvegarder les messages dans un fichier.
     *
     * @throws IOException <br><strong>EN:</strong> An error may occur while saving the file.<br><strong>FR:</strong> Une erreur peut survenir pendant l'enregistrement du fichier.
     */
    default void save() throws IOException {
        this.save(this.getLogs());
    }

}
