/*
 * Conçu avec ♡ par Levasseur Wesley pour Etsuko.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 17:21:22.
 */

package codes.wesley.dev.remasteredlogger.logger.interfaces;

import codes.wesley.dev.remasteredlogger.logger.Logger;

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
 * ILoggerFactory consiste à gérer tous les {@link ILogger} et de sauvegarder par la suite dans un fichier
 * toutes les émission de messages suite à des événements.
 *
 * @version 1.1.0
 */
public interface ILoggerFactory {

    /**
     * @return La liste de tous les messages suite à des événements.
     */
    default List<String> getLogs() {
        return new ArrayList<>();
    }

    /**
     * Cette fonction permet d'ajouter un message dans la liste de messages.
     *
     * @param log Le message
     */
    default void addLog(String log) {
        this.getLogs().add(log);
    }

    /**
     * @return Le dossier où les fichiers d'enregistrement des messages seront enregistrés.
     */
    File getLogsDir();

    /**
     * @return Les loggers présents dans le Factory.
     */
    default ConcurrentMap<String, ILogger> getLoggers() {
        return new ConcurrentHashMap<>();
    }

    /**
     * Cette fonction permet de récupérer un logger ou de crée un directement
     * si cette dernière est inexistante et l'ajoutera automatiquement au Factory.
     *
     * @param name Le nom du Logger.
     * @return Le Logger récupérer ou crée.
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
     * Cette fonction permet de récupérer un logger ou de crée un directement
     * si cette dernière est inexistante et l'ajoutera automatiquement au Factory.
     *
     * @param _class La class ou le Logger effectue son action, son nom sera crée automatiquement.
     * @return Le Logger récupérer ou crée.
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
     * Cette dernière peut-être modifiable selon l'utilisateur.
     *
     * @return Le formatage de la date.
     */
    default SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("MMMM dd',' yyyy '-' hh:mm:ss aaa '('zZ')'");
    }

    /**
     * Cette fonction permet de sauvegarder les messages dans un fichier.
     *
     * @param allLogs Les messages qu'il faut sauvegardé.
     * @throws IOException Une erreur peut subvenir pendant l'enregistrement du fichier.
     */
    default void save(List<String>[] allLogs) throws IOException {
        List<String> logs = new ArrayList<>();
        Arrays.asList(allLogs).forEach(logs::addAll);
        this.save(logs);
    }


    /**
     * Cette fonction permet de sauvegarder les messages dans un fichier.
     *
     * @param logs Les messages qu'il faut sauvegardé.
     * @throws IOException Une erreur peut subvenir pendant l'enregistrement du fichier.
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
     * Cette fonction permet de sauvegarder les messages dans un fichier.
     *
     * @throws IOException Une erreur peut subvenir pendant l'enregistrement du fichier.
     */
    default void save() throws IOException {
        this.save(this.getLogs());
    }

}
