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
 * <strong>EN:</strong> ILogger consists of adding processing to allow transmission and storage messages following events.<br>
 * <strong>FR:</strong> ILogger consiste à ajouter un traitement pour permettre la transmission et le stockage des messages suite à des événements.
 *
 * @author Levasseur Wesley
 * @version 1.1.0
 * @see org.slf4j.Logger
 */
public interface ILogger extends Logger {

    /**
     * <strong>EN:</strong> The function is used to generate a name from a {@link Class}, the latter usable for retrieval and the user of a Logger.<br>
     * <strong>FR:</strong> La fonction permet de générer un nom depuis une {@link Class}, ce dernier utilisable pour la récupération et l'utilisateur d'un Logger.
     *
     * @param _class <br><strong>EN:</strong>The parameter can generate the coming class or the Logger can be used or will be used. <br><strong>FR:</strong> Le paramètre peut engendrer la classe venant ou le Logger peut être utiliser ou va être utiliser.
     * @return <br><strong>EN:</strong> A name from a {@link Class}, the latter usable for retrieval and the user of a Logger.<br><strong>FR:</strong> Un nom depuis une {@link Class}, ce dernier utilisable pour la récupération et l'utilisateur d'un Logger.
     */
    static String performName(Class<?> _class) {
        return _class.getPackageName() + _class.getName();
    }

    /**
     * @return <br><strong>EN:</strong> The name of the Logger.<br><strong>FR:</strong> Le nom du Logger.
     */
    String getName();

    /**
     * @return <br><strong>EN:</strong> The shortened name of the Logger.<br><strong>FR:</strong> Le nom raccourci du Logger.
     */
    private String getShortName() {
        return this.getName().substring(this.getName().lastIndexOf(".") + 1);
    }

    /**
     * @return <br><strong>EN:</strong> The Factory where the Logger is even located.<br><strong>FR:</strong> Le Factory ou se situe même le Logger.
     */
    ILoggerFactory getLoggerFactory();


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
     * <strong>EN:</strong> The latter can be modified during a recreation of the Logger during the reimplementation.<br>
     * It will still keep the 4 definitions which are:<br>
     * - {@code {DATE}} for displaying the date. {@link Date}<br>
     * - {@code {LEVELS}} for the level of the Logger. {@link Levels}<br>
     * - {@code {NAME}} for the name of the Logger.<br>
     * - {@code {MESSAGE}} for the Logger message.<br>
     * <strong>FR:</strong> Cette dernière pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.<br>
     * Il gardera quand-même les 4 définitions qui sont:<br>
     * - {@code {DATE}} pour l'affichage de la date. {@link Date}<br>
     * - {@code {LEVELS}} pour le niveau du Logger. {@link Levels}<br>
     * - {@code {NAME}} pour le nom du Logger.<br>
     * - {@code {MESSAGE}} pour le message du Logger.<br>
     *
     * @return <br><strong>EN:</strong> The format of the line.<br><strong>FR:</strong> Le format de la ligne.
     */
    default String getLineFormat() {
        return "[{DATE}] [{LEVELS}-{NAME}] {MESSAGE}";
    }

    /**
     * <strong>EN:</strong> <br> The latter by default will return {@code true}, but can be modified during a re-creation of the Logger during the reimplementation.<br>
     * <strong>FR:</strong> Cette dernière par défaut renverra {@code true}, mais pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.
     *
     * @return <br><strong>EN:</strong> If the Logger is active in {@link Levels#DEBUG}.<br><strong>FR:</strong> Si le Logger est actif en {@link Levels#DEBUG}.
     */
    default boolean isDebugging() {
        return true;
    }

    /**
     * <strong>EN:</strong> This enables the {@link Levels#DEBUG} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet d'activer le niveau {@link Levels#DEBUG} dans le Logger.
     */
    void activateDebugging();

    /**
     * <strong>EN:</strong> This disables the {@link Levels#DEBUG} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet de désactiver le niveau {@link Levels#DEBUG} dans le Logger.
     */
    void deactivateDebugging();

    /**
     * <strong>EN:</strong> <br> The latter by default will return {@code true}, but can be modified during a re-creation of the Logger during the reimplementation.<br>
     * <strong>FR:</strong> Cette dernière par défaut renverra {@code true}, mais pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.
     *
     * @return <br><strong>EN:</strong> If the Logger is active in {@link Levels#TRACE}.<br><strong>FR:</strong> Si le Logger est actif en {@link Levels#TRACE}.
     */
    default boolean isTracing() {
        return true;
    }

    /**
     * <strong>EN:</strong> This enables the {@link Levels#TRACE} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet d'activer le niveau {@link Levels#TRACE} dans le Logger.
     */
    void activateTracing();

    /**
     * <strong>EN:</strong> This disables the {@link Levels#TRACE} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet de désactiver le niveau {@link Levels#TRACE} dans le Logger.
     */
    void deactivateTracing();

    /**
     * <strong>EN:</strong> <br> The latter by default will return {@code true}, but can be modified during a re-creation of the Logger during the reimplementation.<br>
     * <strong>FR:</strong> Cette dernière par défaut renverra {@code true}, mais pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.
     *
     * @return <br><strong>EN:</strong> If the Logger is active in {@link Levels#INFO}.<br><strong>FR:</strong> Si le Logger est actif en {@link Levels#INFO}.
     */
    default boolean isInforming() {
        return true;
    }

    /**
     * <strong>EN:</strong> This enables the {@link Levels#INFO} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet d'activer le niveau {@link Levels#INFO} dans le Logger.
     */
    void activateInforming();

    /**
     * <strong>EN:</strong> This disables the {@link Levels#INFO} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet de désactiver le niveau {@link Levels#INFO} dans le Logger.
     */
    void deactivateInforming();

    /**
     * <strong>EN:</strong> <br> The latter by default will return {@code true}, but can be modified during a re-creation of the Logger during the reimplementation.<br>
     * <strong>FR:</strong> Cette dernière par défaut renverra {@code true}, mais pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.
     *
     * @return <br><strong>EN:</strong> If the Logger is active in {@link Levels#WARN}.<br><strong>FR:</strong> Si le Logger est actif en {@link Levels#WARN}.
     */
    default boolean isWarning() {
        return true;
    }

    /**
     * <strong>EN:</strong> This enables the {@link Levels#WARN} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet d'activer le niveau {@link Levels#WARN} dans le Logger.
     */
    void activateWarning();

    /**
     * <strong>EN:</strong> This disables the {@link Levels#WARN} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet de désactiver le niveau {@link Levels#WARN} dans le Logger.
     */
    void deactivateWarning();

    /**
     * <strong>EN:</strong> <br> The latter by default will return {@code true}, but can be modified during a re-creation of the Logger during the reimplementation.<br>
     * <strong>FR:</strong> Cette dernière par défaut renverra {@code true}, mais pouvant être modifier lors d'une recréation du Logger pendant la réimplementation.
     *
     * @return <br><strong>EN:</strong> If the Logger is active in {@link Levels#ERROR}.<br><strong>FR:</strong> Si le Logger est actif en {@link Levels#ERROR}.
     */
    default boolean isErroring() {
        return true;
    }

    /**
     * <strong>EN:</strong> This enables the {@link Levels#ERROR} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet d'activer le niveau {@link Levels#ERROR} dans le Logger.
     */
    void activateErroring();

    /**
     * <strong>EN:</strong> This disables the {@link Levels#ERROR} level to be activated in the Logger.<br>
     * <strong>FR:</strong> Cette dernière permet de désactiver le niveau {@link Levels#ERROR} dans le Logger.
     */
    void deactivateErroring();

    /**
     * <strong>EN:</strong> This function is used to check whether the level {@link Levels} of the Logger in parameter is active.<br>
     * <strong>FR:</strong> Cette fonction permet vérifier si le niveau {@link Levels} du Logger en paramètre est actif.
     *
     * @param level <br><strong>EN:</strong> The {@link Levels} level of the Logger.<br><strong>FR:</strong> Le niveau {@link Levels} du Logger.
     * @return <br><strong>EN:</strong> If the level {@link Levels} of the Logger in parameter is active.<br><strong>FR:</strong> Si le niveau {@link Levels} du Logger en paramètre est actif.
     */
    private boolean isLevelsActive(Levels level) {
        return Levels.TRACE == level ? this.isTracing() : (Levels.DEBUG == level ? this.isDebugging() : (Levels.INFO == level ? this.isInforming() : (Levels.WARN == level ? this.isWarning() : Levels.ERROR != level || this.isErroring())));
    }

    /**
     * @return <br><strong>EN:</strong> The formatted line.<br><strong>FR:</strong> La ligne formaté.
     */
    private String getFormattedLine(String date, String levels, String message) {
        return this.getLineFormat().replace("{DATE}", date).replace("{LEVELS}", levels).replace("{NAME}", this.getShortName()).replace("{MESSAGE}", message);
    }

    /**
     * @return <br><strong>EN:</strong> The formatted date.<br><strong>FR:</strong> La date formaté.
     */
    private String getFormattedDate() {
        return this.getDateFormat().format(new Date());
    }

    /**
     * <strong>EN:</strong> This function is used to send a message in the console and to save it in the factory in order to be saved later in a file.<br>
     * <strong>FR:</strong> Cette fonction permet d'émettre un message en console et de se sauvegarder dans le factory afin d'être sauvegardé ultérieurement dans un fichier.
     *
     * @param levels    <br><strong>EN:</strong> The {@link Levels} level of the Logger.<br><strong>FR:</strong> Le niveau {@link Levels} du Logger.
     * @param message   <br><strong>EN:</strong> The message.<br><strong>FR:</strong> Le message.
     * @param throwable <br><strong>EN:</strong> An {@link Nullable} error.<br><strong>FR:</strong> Une erreur {@link Nullable}.
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

    /**
     * <strong>EN:</strong> Levels is a {@link java.util.Enumeration} listing all possible level types in the Logger.<br>
     * This enumeration behaves in such a way as to have a {@link Colors} allowing better visibility in console when retrieving the latter.<br>
     * <strong>FR:</strong> Levels est une {@link java.util.Enumeration} listant tous les types de niveau possible dans le Logger.<br>
     * Cette énumération se comporte de façon à avoir une {@link Colors} permettant une meilleure visibilité en console lors de la récupération de cette dernière.
     */
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

        /**
         * @return <br><strong>EN:</strong> The color. {@link Colors}<br><strong>FR:</strong> La couleur. {@link Colors}
         */
        public Colors getColors() {
            return this.colors;
        }
    }

    /**
     * <strong>EN:</strong> Colors is a {@link java.util.Enumeration} listing all possible level types in the Logger.<br>
     * This enumeration behaves in such a way as to have {@link String} of the color allowing a better visibility in console during the retrieval of the latter.<br>
     * <strong>FR:</strong> Colors est une {@link java.util.Enumeration} listant tous les types de couleur utilisable pour le Logger.<br>
     * Cette énumération se comporte de façon à avoir {@link String} de la couleur permettant une meilleur visibilité en console lors de la récupération de cette dernière.
     */
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

        /**
         * @return <br><strong>EN:</strong> The color. {@link String}<br><strong>FR:</strong> La couleur. {@link String}
         */
        public String getColor() {
            return this.color;
        }
    }

}
