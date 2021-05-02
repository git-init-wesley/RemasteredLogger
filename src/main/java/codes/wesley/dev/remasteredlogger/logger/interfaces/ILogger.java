/*
 * Conçu avec ♡ par Levasseur Wesley pour Etsuko.
 * © Copyright 2021. Tous droits réservés.
 *
 * Création datant du 25/4/2021 à 16:41:28.
 */

package codes.wesley.dev.remasteredlogger.logger.interfaces;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ILogger consiste à ajouter des traitements pour permettre l'émission et le stockage
 * de messages suite à des événements.
 *
 * @author Levasseur Wesley
 * @version 1.1.0
 */
public interface ILogger extends Logger {

    /**
     * Cette fonction permet de crée le nom du logger depuis directement l'objet {@link Class}.
     *
     * @param _class La classe suivie est celle ou se situe l'exécution du logger.
     * @return Le nom du logger.
     */
    static String performName(Class<?> _class) {
        return _class.getPackageName() + _class.getName();
    }

    /**
     * @return Le nom du Logger.
     */
    String getName();

    /**
     * @return Le nom raccourci du Logger.
     */
    private String getShortName() {
        return this.getName().substring(this.getName().lastIndexOf(".") + 1);
    }

    /**
     * @return Le Factory du logger.
     */
    ILoggerFactory getLoggerFactory();


    /**
     * Cette dernière peut-être modifiable selon l'utilisateur.
     *
     * @return Le formatage de la date.
     */
    default SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("MMMM dd',' yyyy '-' hh:mm:ss aaa '('zZ')'");
    }

    /**
     * Cette dernière peut-être modifiable selon l'utilisateur.
     * Il gardera quand-même les 4 définitions qui sont:
     * - {DATE} pour l'affichage de la date formaté.
     * - {LEVELS} pour le niveau du Logger.
     * - {NAME} pour le nom raccourci du Logger.
     * - {MESSAGE} pour le message du Logger.
     *
     * @return Le formatage de la ligne.
     */
    default String getLineFormat() {
        return "[{DATE}] [{LEVELS}-{NAME}] {MESSAGE}";
    }

    /**
     * @return Si le Logger est actif en niveau {@link Levels} "Debug".
     */
    default boolean isDebugging() {
        return true;
    }

    /**
     * Cette fonction permet d'activer niveau {@link Levels} "Debug".
     */
    void activateDebugging();

    /**
     * Cette fonction permet de désactiver niveau {@link Levels} "Debug".
     */
    void deactivateDebugging();

    /**
     * @return Si le Logger est actif en niveau {@link Levels} "Trace".
     */
    default boolean isTracing() {
        return true;
    }

    /**
     * Cette fonction permet d'activer niveau {@link Levels} "Trace".
     */
    void activateTracing();

    /**
     * Cette fonction permet de désactiver niveau {@link Levels} "Trace".
     */
    void deactivateTracing();

    /**
     * @return Si le Logger est actif en niveau {@link Levels} "Info".
     */
    default boolean isInforming() {
        return true;
    }

    /**
     * Cette fonction permet d'activer niveau {@link Levels} "Info".
     */
    void activateInforming();

    /**
     * Cette fonction permet de désactiver niveau {@link Levels} "Info".
     */
    void deactivateInforming();

    /**
     * @return Si le Logger est actif en niveau {@link Levels} "Warn".
     */
    default boolean isWarning() {
        return true;
    }

    /**
     * Cette fonction permet d'activer niveau {@link Levels} "Warn".
     */
    void activateWarning();

    /**
     * Cette fonction permet de désactiver niveau {@link Levels} "Warn".
     */
    void deactivateWarning();

    /**
     * @return Si le Logger est actif en niveau {@link Levels} "Error".
     */
    default boolean isErroring() {
        return true;
    }

    /**
     * Cette fonction permet d'activer niveau {@link Levels} "Error".
     */
    void activateErroring();

    /**
     * Cette fonction permet de désactiver niveau {@link Levels} "Error".
     */
    void deactivateErroring();

    /**
     * Cette fonction permet vérifier si le niveau {@link Levels} du Logger en paramètre est actif.
     *
     * @param level Le niveau du Logger
     * @return Si le niveau {@link Levels} du Logger en paramètre est actif.
     */
    private boolean isLevelsActive(Levels level) {
        return Levels.TRACE == level ? this.isTracing() : (Levels.DEBUG == level ? this.isDebugging() : (Levels.INFO == level ? this.isInforming() : (Levels.WARN == level ? this.isWarning() : Levels.ERROR != level || this.isErroring())));
    }

    /**
     * @return La ligne formatée.
     */
    private String getFormattedLine(String date, String levels, String message) {
        return this.getLineFormat().replace("{DATE}", date).replace("{LEVELS}", levels).replace("{NAME}", this.getShortName()).replace("{MESSAGE}", message);
    }

    /**
     * @return La date formatée.
     */
    private String getFormattedDate() {
        return this.getDateFormat().format(new Date());
    }

    /**
     * Cette fonction permet de "log" et d'envoyer en console l'erreur
     * puis de l'enregistrer dans le factory pour sauvegarder tous cela dans un fichier.
     *
     * @param levels    Le niveau du Logger.
     * @param message   Le message a inscrire dans le Logger.
     * @param throwable Une erreur @Nullable.
     */
    private void log(Levels levels, String message, @Nullable Throwable throwable) {
        if (this.isLevelsActive(levels)) {
            final String date = this.getFormattedDate();
            final String line = this.getFormattedLine(date, levels.toString(), message);
            String color = levels.getColors().getColor();
            System.out.println(color + line);
            this.getLoggerFactory().addLog(line);
            if (throwable != null) {
                StringBuilder throwMessage = new StringBuilder(this.getFormattedLine(date, levels.toString(), throwable.toString()));
                Arrays.stream(throwable.getStackTrace()).forEach(traceElement -> throwMessage.append("\n").append(this.getFormattedLine(date, levels.toString(), "\t- " + traceElement.toString())));
                System.out.println(color + throwMessage);
                this.getLoggerFactory().addLog(throwMessage.toString());
            }
            System.out.flush();
        }
    }

    private void formatAndLog(Levels levels, String format, Object a, Object a2) {
        if (this.isLevelsActive(levels)) {
            FormattingTuple fT = MessageFormatter.format(format, a, a2);
            this.log(levels, fT.getMessage(), fT.getThrowable());
        }
    }

    private void formatAndLog(Levels levels, String format, Object... a) {
        if (this.isLevelsActive(levels)) {
            FormattingTuple fT = MessageFormatter.arrayFormat(format, a);
            this.log(levels, fT.getMessage(), fT.getThrowable());
        }
    }

    @Override
    default boolean isTraceEnabled() {
        return this.isTracing();
    }

    @Override
    default void trace(String msg) {
        this.log(Levels.TRACE, msg, null);
    }

    @Override
    default void trace(String format, Object arg) {
        this.formatAndLog(Levels.TRACE, format, arg, null);
    }

    @Override
    default void trace(String format, Object arg1, Object arg2) {
        this.formatAndLog(Levels.TRACE, format, arg1, arg2);
    }

    @Override
    default void trace(String format, Object... arguments) {
        this.formatAndLog(Levels.TRACE, format, arguments);
    }

    @Override
    default void trace(String msg, Throwable t) {
        this.log(Levels.TRACE, msg, t);
    }

    @Override
    default boolean isTraceEnabled(Marker ignore) {
        return this.isTraceEnabled();
    }

    @Override
    default void trace(Marker ignore, String msg) {
        this.trace(msg);
    }

    @Override
    default void trace(Marker ignore, String format, Object arg) {
        this.trace(format, arg);
    }

    @Override
    default void trace(Marker ignore, String format, Object arg1, Object arg2) {
        this.trace(format, arg1, arg2);
    }

    @Override
    default void trace(Marker ignore, String format, Object... argArray) {
        this.trace(format, argArray);
    }

    @Override
    default void trace(Marker ignore, String msg, Throwable t) {
        this.trace(msg, t);
    }

    @Override
    default boolean isDebugEnabled() {
        return this.isDebugging();
    }

    @Override
    default void debug(String msg) {
        this.log(Levels.DEBUG, msg, null);
    }

    @Override
    default void debug(String format, Object arg) {
        this.formatAndLog(Levels.DEBUG, format, arg, null);
    }

    @Override
    default void debug(String format, Object arg1, Object arg2) {
        this.formatAndLog(Levels.DEBUG, format, arg1, arg2);
    }

    @Override
    default void debug(String format, Object... arguments) {
        this.formatAndLog(Levels.DEBUG, format, arguments);
    }

    @Override
    default void debug(String msg, Throwable t) {
        this.log(Levels.DEBUG, msg, t);
    }

    @Override
    default boolean isDebugEnabled(Marker ignore) {
        return this.isDebugEnabled();
    }

    @Override
    default void debug(Marker ignore, String msg) {
        this.debug(msg);
        this.isDebugEnabled(ignore);
    }

    @Override
    default void debug(Marker ignore, String format, Object arg) {
        this.debug(format, arg);
    }

    @Override
    default void debug(Marker ignore, String format, Object arg1, Object arg2) {
        this.debug(format, arg1, arg2);
    }

    @Override
    default void debug(Marker ignore, String format, Object... arguments) {
        this.debug(format, arguments);
    }

    @Override
    default void debug(Marker ignore, String msg, Throwable t) {
        this.debug(msg, t);
    }

    @Override
    default boolean isInfoEnabled() {
        return this.isInforming();
    }

    @Override
    default void info(String msg) {
        this.log(Levels.INFO, msg, null);
    }

    @Override
    default void info(String format, Object arg) {
        this.formatAndLog(Levels.INFO, format, arg, null);
    }

    @Override
    default void info(String format, Object arg1, Object arg2) {
        this.formatAndLog(Levels.INFO, format, arg1, arg2);
    }

    @Override
    default void info(String format, Object... arguments) {
        this.formatAndLog(Levels.INFO, format, arguments);
    }

    @Override
    default void info(String msg, Throwable t) {
        this.log(Levels.INFO, msg, t);
    }

    @Override
    default boolean isInfoEnabled(Marker ignore) {
        return this.isInfoEnabled();
    }

    @Override
    default void info(Marker ignore, String msg) {
        this.info(msg);
    }

    @Override
    default void info(Marker ignore, String format, Object arg) {
        this.info(format, arg);
    }

    @Override
    default void info(Marker ignore, String format, Object arg1, Object arg2) {
        this.info(format, arg1, arg2);
    }

    @Override
    default void info(Marker ignore, String format, Object... arguments) {
        this.info(format, arguments);
    }

    @Override
    default void info(Marker ignore, String msg, Throwable t) {
        this.info(msg, t);
    }

    @Override
    default boolean isWarnEnabled() {
        return this.isWarning();
    }

    @Override
    default void warn(String msg) {
        this.log(Levels.WARN, msg, null);
    }

    @Override
    default void warn(String format, Object arg) {
        this.formatAndLog(Levels.WARN, format, arg, null);
    }

    @Override
    default void warn(String format, Object... arguments) {
        this.formatAndLog(Levels.WARN, format, arguments);
    }

    @Override
    default void warn(String format, Object arg1, Object arg2) {
        this.formatAndLog(Levels.WARN, format, arg1, arg2);
    }

    @Override
    default void warn(String msg, Throwable t) {
        this.log(Levels.WARN, msg, t);
    }

    @Override
    default boolean isWarnEnabled(Marker ignore) {
        return this.isWarnEnabled();
    }

    @Override
    default void warn(Marker ignore, String msg) {
        this.warn(msg);
    }

    @Override
    default void warn(Marker ignore, String format, Object arg) {
        this.warn(format, arg);
    }

    @Override
    default void warn(Marker ignore, String format, Object arg1, Object arg2) {
        this.warn(format, arg1, arg2);
    }

    @Override
    default void warn(Marker ignore, String format, Object... arguments) {
        this.warn(format, arguments);
    }

    @Override
    default void warn(Marker ignore, String msg, Throwable t) {
        this.warn(msg, t);
    }

    @Override
    default boolean isErrorEnabled() {
        return this.isErroring();
    }

    @Override
    default void error(String msg) {
        this.log(Levels.ERROR, msg, null);
    }

    @Override
    default void error(String format, Object arg) {
        this.formatAndLog(Levels.ERROR, format, arg, null);
    }

    @Override
    default void error(String format, Object arg1, Object arg2) {
        this.formatAndLog(Levels.ERROR, format, arg1, arg2);
    }

    @Override
    default void error(String format, Object... arguments) {
        this.formatAndLog(Levels.ERROR, format, arguments);
    }

    @Override
    default void error(String msg, Throwable t) {
        this.log(Levels.ERROR, msg, t);
    }

    @Override
    default boolean isErrorEnabled(Marker ignore) {
        return this.isErrorEnabled();
    }

    @Override
    default void error(Marker ignore, String msg) {
        this.error(msg);
    }

    @Override
    default void error(Marker ignore, String format, Object arg) {
        this.error(format, arg);
    }

    @Override
    default void error(Marker ignore, String format, Object arg1, Object arg2) {
        this.error(format, arg1, arg2);
    }

    @Override
    default void error(Marker ignore, String format, Object... arguments) {
        this.error(format, arguments);
    }

    @Override
    default void error(Marker ignore, String msg, Throwable t) {
        this.error(msg, t);
    }

    enum Levels {
        TRACE(Colors.CYAN),
        DEBUG(Colors.PURPLE),
        INFO(Colors.BLUE),
        WARN(Colors.YELLOW),
        ERROR(Colors.RED),
        CONSOLE(Colors.GREEN);

        private final Colors colors;

        Levels(Colors colors) {
            this.colors = colors;
        }

        public Colors getColors() {
            return this.colors;
        }
    }

    enum Colors {
        RESET("\u001b[0m"),
        BLACK("\u001b[30m"),
        RED("\u001b[31m"),
        GREEN("\u001b[32m"),
        YELLOW("\u001b[33m"),
        BLUE("\u001b[34m"),
        PURPLE("\u001b[35m"),
        CYAN("\u001b[36m"),
        WHITE("\u001b[37m");

        private final String color;

        Colors(String color) {
            this.color = color;
        }

        public String getColor() {
            return this.color;
        }
    }

}
