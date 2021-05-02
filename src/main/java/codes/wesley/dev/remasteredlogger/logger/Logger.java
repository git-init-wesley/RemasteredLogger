/*
 * Conçu avec ♡ par Levasseur Wesley pour Etsuko.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 15:58:22.
 */

package codes.wesley.dev.remasteredlogger.logger;


import codes.wesley.dev.remasteredlogger.logger.interfaces.ILogger;
import codes.wesley.dev.remasteredlogger.logger.interfaces.ILoggerFactory;

public class Logger implements ILogger {

    private final String name;
    private final ILoggerFactory loggerFactory;
    private boolean debug = true, trace = true, info = true, warn = true, error = true;

    public Logger(String name, ILoggerFactory loggerFactory) {
        this.name = name;
        this.loggerFactory = loggerFactory;
    }

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
