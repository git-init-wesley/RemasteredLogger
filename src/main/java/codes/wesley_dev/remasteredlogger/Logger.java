/*
 * Conçu avec ♡ par Levasseur Wesley pour Etsuko.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 15:58:22.
 */

package codes.wesley_dev.remasteredlogger;


import codes.wesley_dev.remasteredlogger.interfaces.ILogger;
import codes.wesley_dev.remasteredlogger.interfaces.ILoggerFactory;

/**
 * <strong>EN:</strong> Logger consists of adding processing to allow transmission and storage messages following events.<br>
 * This class is a default Logger if the user does not want to create one himself by implementing {@link ILogger}, by default all {@link Levels} are enabled.<br>
 * <strong>FR:</strong> Logger consiste à ajouter un traitement pour permettre la transmission et le stockage des messages suite à des événements.<br>
 * Cette classe est un Logger de défaut si l'utilisateur ne souhaite pas en crée lui-même en implémentant {@link ILogger}, par défaut tous les {@link Levels} sont activé.
 *
 * @author Levasseur Wesley
 * @version 1.1.0
 * @see ILogger
 */
public class Logger implements ILogger {

    private final String name;
    private final ILoggerFactory loggerFactory;
    private boolean debug = true, trace = true, info = true, warn = true, error = true;

    /**
     * <strong>EN:</strong> This constructor allows you to create a Logger. It is usually created from the Factory itself.<br>
     * <strong>FR:</strong> Ce constructeur permet de créer un Logger. Il est généralement créer depuis le Factory lui-même.
     *
     * @param name          <br><strong>EN:</strong> The name of the Logger.<br><strong>FR:</strong> Le nom du Logger.
     * @param loggerFactory <br><strong>EN:</strong> The Logger Factory.<br><strong>FR:</strong> Le Factory du Logger.
     */
    public Logger(String name, ILoggerFactory loggerFactory) {
        this.name = name;
        this.loggerFactory = loggerFactory;
    }

    /**
     * <strong>EN:</strong> This constructor allows you to create a Logger. It is usually created from the Factory itself.<br>
     * <strong>FR:</strong> Ce constructeur permet de créer un Logger. Il est généralement créer depuis le Factory lui-même.
     *
     * @param _class        <br><strong>EN:</strong>The parameter can generate the coming class where the Logger can be used or will be used. <br><strong>FR:</strong> Le paramètre peut engendrer la classe venant où le Logger peut être utiliser ou va être utiliser.
     * @param loggerFactory <br><strong>EN:</strong> The Logger Factory.<br><strong>FR:</strong> Le Factory du Logger.
     */
    public Logger(Class<?> _class, ILoggerFactory loggerFactory) {
        this(ILogger.performName(_class), loggerFactory);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    @Override
    public boolean isDebugging() {
        return this.debug;
    }

    @Override
    public void activateDebugging() {
        this.debug = true;
    }

    @Override
    public void deactivateDebugging() {
        this.debug = false;
    }

    @Override
    public boolean isTracing() {
        return this.trace;
    }

    @Override
    public void activateTracing() {
        this.trace = true;
    }

    @Override
    public void deactivateTracing() {
        this.trace = false;
    }

    @Override
    public boolean isInforming() {
        return this.info;
    }

    @Override
    public void activateInforming() {
        this.info = true;
    }

    @Override
    public void deactivateInforming() {
        this.info = false;
    }

    @Override
    public boolean isWarning() {
        return this.warn;
    }

    @Override
    public void activateWarning() {
        this.warn = true;
    }

    @Override
    public void deactivateWarning() {
        this.warn = false;
    }

    @Override
    public boolean isErroring() {
        return this.error;
    }

    @Override
    public void activateErroring() {
        this.error = true;
    }

    @Override
    public void deactivateErroring() {
        this.error = false;
    }
}
